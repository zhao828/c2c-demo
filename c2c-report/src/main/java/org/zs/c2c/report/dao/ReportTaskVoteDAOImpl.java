package org.zs.c2c.report.dao;

import org.zs.c2c.report.domain.ReportTaskVote;
import org.zs.c2c.report.mapper.ReportTaskVoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 举报任务投票DAO组件
 */
@Repository
public class ReportTaskVoteDAOImpl implements ReportTaskVoteDAO {

    /**
     * 举报任务投票Mapper组件
     */
    @Autowired
    private ReportTaskVoteMapper reportTaskVoteMapper;

    /**
     * 增加举报任务投票
     * @param reportTaskVote 举报任务投票
     */
    public void add(ReportTaskVote reportTaskVote) {
        reportTaskVoteMapper.insert(reportTaskVote);
    }

    /**
     * 更新举报任务投票
     * @param reportTaskVote 举报任务投票
     */
    public void update(ReportTaskVote reportTaskVote) {
        reportTaskVoteMapper.update(reportTaskVote);
    }

    /**
     * 查询举报任务的所有投票
     * @param reportTaskId 举报任务id
     * @return 举报任务投票
     */
    public List<ReportTaskVote> queryByReportTaskId(Long reportTaskId) {
        return reportTaskVoteMapper.selectByReportTaskId(reportTaskId);
    }

}
