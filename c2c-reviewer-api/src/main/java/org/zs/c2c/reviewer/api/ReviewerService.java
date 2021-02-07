package org.zs.c2c.reviewer.api;


import java.util.List;

public interface ReviewerService {

    /**
     * 选择评审员
     * @param reportTaskId
     * @return
     */
    List<Long> selectReviewers(Long reportTaskId);

    /**
     * 完成投票
     * @param reviewerId 评审员id
     * @param reportTaskId 举报任务id
     */
    void finishVote(Long reviewerId, Long reportTaskId);

}
