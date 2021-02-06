package org.zs.c2c.reward.service;

import org.zs.c2c.reward.api.RewardService;
import org.apache.dubbo.config.annotation.Service;

@Service(
        version = "1.0.0",
        interfaceClass = RewardService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class RewardServiceImpl implements RewardService {

}
