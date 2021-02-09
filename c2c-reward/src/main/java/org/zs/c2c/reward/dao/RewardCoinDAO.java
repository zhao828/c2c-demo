package org.zs.c2c.reward.dao;

import org.zs.c2c.reward.domain.RewardCoin;

/**
 * 奖励金币DAO接口
 */
public interface RewardCoinDAO {

    /**
     * 增加奖励金币记录
     * @param rewardCoin 奖励金币记录
     */
    void add(RewardCoin rewardCoin);

}
