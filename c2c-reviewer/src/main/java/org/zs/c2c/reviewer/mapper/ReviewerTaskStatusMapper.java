package org.zs.c2c.reviewer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.zs.c2c.reviewer.domain.ReviewerTaskStatus;

@Mapper
@Component
public interface ReviewerTaskStatusMapper {

    @Insert("INSERT INTO reviewer_task_status(reviewer_id,report_task_id,status) " +
            "VALUES(#{reviewerId},#{reportTaskId},#{status})")
    void insert(ReviewerTaskStatus reviewerTaskStatus);

    /**
     * 更新评审员处理举报任务的状态
     * @param reviewerTaskStatus 评审员处理举报任务的状态
     */
    @Update("UPDATE reviewer_task_status " +
            "SET status=#{status} " +
            "WHERE reviewer_id=#{reviewerId} " +
            "AND report_task_id=#{reportTaskId}")
    void update(ReviewerTaskStatus reviewerTaskStatus);

}
