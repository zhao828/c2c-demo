package org.zs.c2c.reward.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.zs.c2c.reward.api.RewardService;
import org.apache.dubbo.config.annotation.Service;
import org.zs.c2c.reward.dao.RewardCoinDAO;
import org.zs.c2c.reward.domain.RewardCoin;

import java.util.List;

@Service(
        version = "1.0.0",
        interfaceClass = RewardService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class RewardServiceImpl implements RewardService {

    /**
     * 奖励金币DAO组件
     */
    @Autowired
    private RewardCoinDAO rewardCoinDAO;

    /**
     * 发放奖励
     * @param reviewerIds 评审员id
     */
    public void giveReward(List<Long> reviewerIds) {
        for(Long reviewerId : reviewerIds) {
            RewardCoin rewardCoin = new RewardCoin();
            rewardCoin.setReviewerId(reviewerId);
            rewardCoin.setCoins(10L);
            rewardCoinDAO.add(rewardCoin);
        }
    }
}
