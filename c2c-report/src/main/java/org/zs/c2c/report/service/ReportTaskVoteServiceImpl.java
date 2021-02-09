package org.zs.c2c.report.service;

import org.zs.c2c.report.dao.ReportTaskDAO;
import org.zs.c2c.report.dao.ReportTaskVoteDAO;
import org.zs.c2c.report.domain.ReportTask;
import org.zs.c2c.report.domain.ReportTaskVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 举报任务投票Service组件
 */
@Service
public class ReportTaskVoteServiceImpl implements ReportTaskVoteService {

    /**
     * 举报任务投票DAO组件
     */
    @Autowired
    private ReportTaskVoteDAO reportTaskVoteDAO;
    /**
     * 举报任务DAO组件
     */
    @Autowired
    private ReportTaskDAO reportTaskDAO;

    /**
     * 初始化评审员对举报任务的投票
     * @param reviewerIds 评审员id
     * @param reportTaskId 举报任务id
     */
    public void initVotes(List<Long> reviewerIds, Long reportTaskId) {
        for(Long reviewerId : reviewerIds) {
            ReportTaskVote reportTaskVote = new ReportTaskVote();
            reportTaskVote.setReviewerId(reviewerId);
            reportTaskVote.setReportTaskId(reportTaskId);
            reportTaskVote.setVoteResult(ReportTaskVote.UNKNOWN);

            reportTaskVoteDAO.add(reportTaskVote);
        }
    }

    /**
     * 对举报任务执行投票
     * @param reviewerId 评审员id
     * @param reportTaskId 举报任务id
     * @param voteResult 投票结果
     */
    public void vote(Long reviewerId, Long reportTaskId, Integer voteResult) {
        ReportTaskVote reportTaskVote = new ReportTaskVote();
        reportTaskVote.setReviewerId(reviewerId);
        reportTaskVote.setReportTaskId(reportTaskId);
        reportTaskVote.setVoteResult(voteResult);
        reportTaskVoteDAO.update(reportTaskVote);
    }

    /**
     * 对举报任务进行归票
     * @param reportTaskId 举报任务id
     */
    public Boolean calculateVotes(Long reportTaskId) {
        List<ReportTaskVote> reportTaskVotes = reportTaskVoteDAO
                .queryByReportTaskId(reportTaskId);

        Integer quorum = reportTaskVotes.size() / 2 + 1;

        Integer approvedVotes = 0;
        Integer unapprovedVotes = 0;

        for(ReportTaskVote reportTaskVote : reportTaskVotes) {
            if(reportTaskVote.getVoteResult().equals(ReportTaskVote.APPROVED)) {
                approvedVotes++;
            } else if(reportTaskVote.getVoteResult().equals(ReportTaskVote.UNAPPROVED)) {
                unapprovedVotes++;
            }
        }

        if(approvedVotes >= quorum) {
            ReportTask reportTask = new ReportTask();
            reportTask.setId(reportTaskId);
            reportTask.setVoteResult(ReportTask.VOTE_RESULT_APPROVED);
            reportTaskDAO.update(reportTask);

            return true;
        } else if(unapprovedVotes >= quorum) {
            ReportTask reportTask = new ReportTask();
            reportTask.setId(reportTaskId);
            reportTask.setVoteResult(ReportTask.VOTE_RESULT_UNAPPROVED);
            reportTaskDAO.update(reportTask);

            return true;
        }

        return false;
    }

    /**
     * 查询举报任务下的所有投票
     * @param reportTaskId 举报任务id
     * @return 投票
     */
    public List<ReportTaskVote> queryByReportTaskId(Long reportTaskId) {
        return reportTaskVoteDAO.queryByReportTaskId(reportTaskId);
    }

}
