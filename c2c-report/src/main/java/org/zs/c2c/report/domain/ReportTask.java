package org.zs.c2c.report.domain;
/**
 * 举报task 整体task的状态
 */
public class ReportTask {
    public static final Integer VOTE_RESULT_UNKNOWN = -1;
    public static final Integer VOTE_RESULT_APPROVED = 1;
    public static final Integer VOTE_RESULT_UNAPPROVED = 2;

    private Long id;
    private String type;
    private Long reportUserId;
    private String reportContent;
    private Long targetId;
    private Integer voteResult;




    public ReportTask() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Long getReportUserId() {
        return reportUserId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Integer getVoteResult() {
        return voteResult;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReportUserId(Long reportUserId) {
        this.reportUserId = reportUserId;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public void setVoteResult(Integer voteResult) {
        this.voteResult = voteResult;
    }


}
