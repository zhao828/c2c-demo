package org.zs.c2c.report;

import org.springframework.web.bind.annotation.GetMapping;
import org.zs.c2c.reviewer.api.ReviewerService;
import org.zs.c2c.reward.api.RewardService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Reference(version = "1.0.0",
            interfaceClass = ReviewerService.class,
            cluster = "failfast")
    private ReviewerService reviewerService;

    @Reference(version = "1.0.0",
            interfaceClass = RewardService.class,
            cluster = "failfast")
    private RewardService rewardService;

    /**
     * submit report
     * @param type
     * @param reportUserId
     * @param reportContent
     * @param targetId
     * @return
     */
    @GetMapping("/report")
    public String report(String type,
                         Long reportUserId,
                         String reportContent,
                         Long targetId){
        return "";
    }
}
