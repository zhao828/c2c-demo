package org.zs.c2c.report.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zs.c2c.report.domain.ReportTask;
import org.zs.c2c.report.mapper.ReportTaskMapper;

/**
 * 举报DAO实现类
 */

@Repository
public class ReportTaskDAOImpl implements ReportTaskDAO{

    @Autowired
    private ReportTaskMapper reportTaskMapper;

    @Override
    public void add(ReportTask reportTask) {
        reportTaskMapper.insert(reportTask);
    }

    /**
     * 根据id查询举报任务
     * @param id 举报任务id
     * @return 举报任务
     */
    public ReportTask queryById(Long id) {
        return reportTaskMapper.selectById(id);
    }

    /**
     * 更新举报任务
     * @param reportTask 举报任务
     */
    public void update(ReportTask reportTask) {
        reportTaskMapper.update(reportTask);
    }
}
