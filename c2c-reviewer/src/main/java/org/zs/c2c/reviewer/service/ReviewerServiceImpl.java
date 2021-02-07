package org.zs.c2c.reviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.zs.c2c.reviewer.api.ReviewerService;
import org.apache.dubbo.config.annotation.Service;
import org.zs.c2c.reviewer.dao.ReviewerTaskStatusDAO;
import org.zs.c2c.reviewer.domain.ReviewerTaskStatus;

import java.util.ArrayList;
import java.util.List;

@Service(
        version = "1.0.0",
        interfaceClass = ReviewerService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class ReviewerServiceImpl implements ReviewerService {


    @Autowired
    private ReviewerTaskStatusDAO reviewerTaskStatusDAO;

    @Override
    public List<Long> selectReviewers(Long reportTaskId) {
        System.out.println("模拟算法选择评审员");
        List<Long>  reviewerIds = new ArrayList<>();
        reviewerIds.add(1L);
        reviewerIds.add(2L);
        reviewerIds.add(3L);
        reviewerIds.add(4L);
        reviewerIds.add(5L);
        for(Long reviewerId : reviewerIds) {
            ReviewerTaskStatus reviewerTaskStatus = new ReviewerTaskStatus();
            reviewerTaskStatus.setReviewerId(reviewerId);
            reviewerTaskStatus.setReportTaskId(reportTaskId);
            reviewerTaskStatus.setStatus(ReviewerTaskStatus.PROCESSING);
            reviewerTaskStatusDAO.add(reviewerTaskStatus);
        }
        return reviewerIds;
    }

    /**
     * 完成投票
     * @param reviewerId 评审员id
     * @param reportTaskId 举报任务id
     */
    public void finishVote(Long reviewerId, Long reportTaskId) {
        ReviewerTaskStatus reviewerTaskStatus = new ReviewerTaskStatus();
        reviewerTaskStatus.setReviewerId(reviewerId);
        reviewerTaskStatus.setReportTaskId(reportTaskId);
        reviewerTaskStatus.setStatus(ReviewerTaskStatus.FINISHED);
        reviewerTaskStatusDAO.update(reviewerTaskStatus);
    }

}
