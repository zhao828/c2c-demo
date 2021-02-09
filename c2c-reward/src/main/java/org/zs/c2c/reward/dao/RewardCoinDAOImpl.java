package org.zs.c2c.reward.dao;

import org.zs.c2c.reward.domain.RewardCoin;
import org.zs.c2c.reward.mapper.RewardCoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 奖励金币记录DAO组件
 */
@Repository
public class RewardCoinDAOImpl implements RewardCoinDAO {

    /**
     * 奖励金币Mapper组件
     */
    @Autowired
    private RewardCoinMapper rewardCoinMapper;

    /**
     * 增加奖励金币记录
     * @param rewardCoin 奖励金币记录
     */
    public void add(RewardCoin rewardCoin) {
        rewardCoinMapper.insert(rewardCoin);
    }

}
