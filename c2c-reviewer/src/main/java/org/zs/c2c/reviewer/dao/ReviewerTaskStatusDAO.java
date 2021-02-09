package org.zs.c2c.reviewer.dao;

import org.zs.c2c.reviewer.domain.ReviewerTaskStatus;

public interface ReviewerTaskStatusDAO {
    /**
     * 增加任务状态
     * @param reviewerTaskStatus
     */
    void add(ReviewerTaskStatus reviewerTaskStatus);

    /**
     * 更新评审处理任务状态
     * @param reviewerTaskStatus 评审处理任务状态
     */
    void update(ReviewerTaskStatus reviewerTaskStatus);
}
