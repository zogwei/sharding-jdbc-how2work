/*
 * 文 件 名:  MyOrderSingleKeyTableShardingAlgorithm.java
 * 版    权:   . Copyright 2008-2015,  All rights reserved Hiaward Information Technology Co.,Ltd.
 * 描    述:  <描述>
 * 修 改 人:  chen.simon
 * 修改时间:  2016-2-9
 */
package com.dangdang.ddframe.rdb.example;

import java.util.Collection;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Chenxiaguang
 * @version  [版本号, 2016-2-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MyOrderSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer>
{

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue)
    {
        System.out.println(availableTargetNames);
        return null;
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue)
    {
        // TODO Auto-generated method stub
        System.out.println(availableTargetNames);
        return null;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
        ShardingValue<Integer> shardingValue)
    {
        // TODO Auto-generated method stub
        System.out.println(availableTargetNames);
        return null;
    }
    
}
