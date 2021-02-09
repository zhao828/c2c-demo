package org.zs.c2c.reward.mapper;

import org.springframework.stereotype.Component;
import org.zs.c2c.reward.domain.RewardCoin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 奖励金币记录Mapper组件
 */
@Mapper
@Component
public interface RewardCoinMapper {

    /**
     * 插入奖励金币记录
     * @param rewardCoin 奖励金币记录
     */
    @Insert("INSERT INTO reward_coin(reviewer_id,coins) " +
            "VALUES(#{reviewerId},#{coins})")
    void insert(RewardCoin rewardCoin);

}
