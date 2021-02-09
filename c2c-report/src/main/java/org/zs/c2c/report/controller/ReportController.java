package org.zs.c2c.report.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zs.c2c.report.domain.ReportTask;
import org.zs.c2c.report.domain.ReportTaskVote;
import org.zs.c2c.report.service.ReportTaskService;
import org.zs.c2c.report.service.ReportTaskVoteService;
import org.zs.c2c.reviewer.api.ReviewerService;
import org.zs.c2c.reward.api.RewardService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {
    //静态代码块，初始化之前就load，只执行一次
    static {
        //为sentinel定制规则

        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("评审员服务资源");
        // set limit qps to 20 限流最大qps到20
        rule.setCount(20);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

    }
    /**
     * 举报人物 service组件
     */
    @Autowired
    private ReportTaskService reportTaskService;

    /**
     * 举报任务投票Service组件
     */
    @Autowired
    private ReportTaskVoteService reportTaskVoteService;

    @Reference(version = "1.0.0",
            interfaceClass = ReviewerService.class,
            cluster = "failfast")
    private ReviewerService reviewerService;

    @Reference(version = "1.0.0",
            interfaceClass = RewardService.class,
            cluster = "failfast")
    private RewardService rewardService;


    @GetMapping("/report")
    public String report(ReportTask reportTask){
        reportTaskService.add(reportTask);
        List<Long> reviewerIds;
        //定义资源 使用sentinel
        try (Entry entry = SphU.entry("评审员服务资源")) {
            // Your business logic here.
            //选择评审员
            reviewerIds = reviewerService.selectReviewers(
                    reportTask.getId());
        } catch (BlockException e) {
            // Handle rejected request.
            System.out.println("系统熔断，无法正常运行");
            //e.printStackTrace();
            return "fail";
        }
        if(reviewerIds!=null){
            // 在本地数据库初始化这批评审员对举报任务的投票状态
            reportTaskVoteService.initVotes(reviewerIds, reportTask.getId());
        }


        // 模拟发送push消息给评审员
        System.out.println("模拟发送push消息给评审员.....");
        return "success";

    }


    /**
     * 查询举报任务 pathervariavle 是把大括号的东西取出来放到变量id里
     * @param id
     * @return
     */
    @GetMapping("/report/query/{id}")
    public ReportTask queryReportTaskId(
            @PathVariable("id") Long id) {
        return reportTaskService.queryById(id);
    }

    /**
     * 对举报任务进行投票
     * @param reviewerId 评审员id
     * @param reportTaskId 举报任务id
     * @param voteResult 投票结果
     * @return
     */
    @GetMapping("/report/vote")
    public String vote(
            Long reviewerId,
            Long reportTaskId,
            Integer voteResult) {
        // 本地数据库记录投票
        reportTaskVoteService.vote(reviewerId, reportTaskId, voteResult);
        // 调用评审员服务，标记本次投票结束
        reviewerService.finishVote(reviewerId, reportTaskId);

        // 对举报任务进行归票
        Boolean hasFinishedVote = reportTaskVoteService
                .calculateVotes(reportTaskId);

        // 如果举报任务得到归票结果
        if(hasFinishedVote) {
            // 发放奖励
            List<ReportTaskVote> reportTaskVotes = reportTaskVoteService
                    .queryByReportTaskId(reportTaskId);
            List<Long> reviewerIds = new ArrayList<Long>();

            for(ReportTaskVote reportTaskVote : reportTaskVotes) {
                reviewerIds.add(reportTaskVote.getReviewerId());
            }

            rewardService.giveReward(reviewerIds);

            // 推送消息到MQ，告知其他系统，本次评审结果
            System.out.println("推送消息到MQ，告知其他系统，本次评审结果");
        }

        return "success";
    }
}
