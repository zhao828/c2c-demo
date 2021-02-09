package org.zs.c2c.reviewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zs.c2c.reviewer.domain.ReviewerTaskStatus;
import org.zs.c2c.reviewer.mapper.ReviewerTaskStatusMapper;

@Repository
public class ReviewerTaskStatusDAOImpl implements ReviewerTaskStatusDAO {

    /**
     * 评审员处理任务Mapper组件
     */
    @Autowired
    private ReviewerTaskStatusMapper reviewerTaskStatusMapper;

    /**
     * 增加评审处理任务状态
     * @param reviewerTaskStatus 评审处理任务状态
     */
    public void add(ReviewerTaskStatus reviewerTaskStatus) {
        reviewerTaskStatusMapper.insert(reviewerTaskStatus);
    }

    /**
     * 更新评审处理任务状态
     * @param reviewerTaskStatus 评审处理任务状态
     */
    public void update(ReviewerTaskStatus reviewerTaskStatus) {
        reviewerTaskStatusMapper.update(reviewerTaskStatus);
    }



}

