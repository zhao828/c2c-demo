package org.zs.c2c.reviewer.service;

import org.zs.c2c.reviewer.api.ReviewerService;
import org.apache.dubbo.config.annotation.Service;

@Service(
        version = "1.0.0",
        interfaceClass = ReviewerService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class ReviewerServiceImpl implements ReviewerService {

}
