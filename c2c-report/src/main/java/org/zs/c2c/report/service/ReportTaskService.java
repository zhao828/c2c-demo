package org.zs.c2c.report.service;

import org.zs.c2c.report.domain.ReportTask;

/**
 * 举报任务Service接口
 */
public interface ReportTaskService {

    /**
     * 增加举报任务
     * @param reportTask 举报任务
     */
    void add(ReportTask reportTask);

    /**
     * 根据id查询举报任务
     * @param id 举报任务id
     * @return 举报任务
     */
    ReportTask queryById(Long id);

}
