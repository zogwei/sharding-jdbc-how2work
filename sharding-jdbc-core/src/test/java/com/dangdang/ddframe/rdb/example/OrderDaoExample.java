/*
 * 文 件 名:  OrderTest.java
 * 版    权:   . Copyright 2008-2015,  All rights reserved Hiaward Information Technology Co.,Ltd.
 * 描    述:  <描述>
 * 修 改 人:  chen.simon
 * 修改时间:  2016-2-9
 */
package com.dangdang.ddframe.rdb.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSource;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.google.common.collect.Maps;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Chenxiaguang
 * @version  [版本号, 2016-2-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OrderDaoExample
{
    
    /**
     * 不使用分库分表  即 最简单的最传统的jdbc查询
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testSimpleQueryWhereEquals()
    {
        DataSource db0DataSource = createDataSource("db0");
        Connection connection = null;
        try
        {
            connection = db0DataSource.getConnection();
            ResultSet  resultSet = connection.createStatement().executeQuery("select * from t_order_0 where order_id=1002");
            while (resultSet.next())
            {
                int orderId = resultSet.getInt("order_id");
                int userId = resultSet.getInt("user_id");
                System.out.println("order id is:" + orderId);
                System.out.println("user id is:" + userId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 使用分库分表  
     * 使用Statement查询
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testShardingQueryWhereEquals001()
    {
        String logicTable = "t_order";
        ShardingDataSource shardingDataSource = createShardingDataSource(logicTable);
        Connection connection = null;
        try
        {
            connection = shardingDataSource.getConnection();
            ResultSet  resultSet = connection.createStatement().executeQuery("select * from t_order where order_id=1002");
            while (resultSet.next())
            {
                int orderId = resultSet.getInt("order_id");
                int userId = resultSet.getInt("user_id");
                System.out.println("order id is:" + orderId);
                System.out.println("user id is:" + userId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 使用分库分表  
     * 使用PreparedStatement查询
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testShardingQueryWhereEquals002()
    {
        String logicTable = "t_order";
        ShardingDataSource shardingDataSource = createShardingDataSource(logicTable);
        Connection connection = null;
        try
        {
            connection = shardingDataSource.getConnection();
            String sql = "select * from t_order where order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1002);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int orderId = resultSet.getInt("order_id");
                int userId = resultSet.getInt("user_id");
                System.out.println("order id is:" + orderId);
                System.out.println("user id is:" + userId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 使用分库分表  
     * 使用Statement查询  不同于testShardingQueryWhereEquals001的地方是sql略不同
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testShardingQueryWhereEquals003()
    {
        String logicTable = "T_ORDER";
        ShardingDataSource shardingDataSource = createShardingDataSource(logicTable);
        Connection connection = null;
        try
        {
            connection = shardingDataSource.getConnection();
            ResultSet  resultSet = connection.createStatement().executeQuery("select * from T_ORDER where T_ORDER.order_id=1002");
            while (resultSet.next())
            {
                int orderId = resultSet.getInt("order_id");
                int userId = resultSet.getInt("user_id");
                System.out.println("order id is:" + orderId);
                System.out.println("user id is:" + userId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 使用分库分表  
     * 使用Statement查询平均值
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testShardingQueryAvg001()
    {
        String logicTable = "t_order";
        ShardingDataSource shardingDataSource = createShardingDataSource(logicTable);
        Connection connection = null;
        try
        {
            connection = shardingDataSource.getConnection();
            
            /**
             * !!使用逻辑表名查询时,平均数的计算是不分片的<br/>
             * 使用物理表名查询时，平均数计算是报错的，sharding不认识物理表名<br/>
             */
            ResultSet  resultSet = connection.createStatement().executeQuery("select avg(order_id) from t_order");
            while (resultSet.next())
            {
                float avgOrderId = resultSet.getFloat(1);
                System.out.println("Avg order id is:" + avgOrderId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 使用分库分表  
     * 使用Statement查询带排序的sql
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testShardingQueryByOrder001()
    {
        String logicTable = "t_order";
        ShardingDataSource shardingDataSource = createShardingDataSource(logicTable);
        Connection connection = null;
        try
        {
            connection = shardingDataSource.getConnection();
            ResultSet  resultSet = connection.createStatement().executeQuery("select * from t_order o order by o.order_id");
            while (resultSet.next())
            {
                int orderId = resultSet.getInt("order_id");
                int userId = resultSet.getInt("user_id");
                System.out.println("order id is:" + orderId);
                System.out.println("user id is:" + userId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 使用分库分表  
     * 使用Statement查询带limit的sql
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testShardingQueryLimit001()
    {
        String logicTable = "t_order";
        ShardingDataSource shardingDataSource = createShardingDataSource(logicTable);
        Connection connection = null;
        try
        {
            connection = shardingDataSource.getConnection();
            ResultSet  resultSet = connection.createStatement().executeQuery("select * from t_order o order by o.order_id limit 1,2");
            while (resultSet.next())
            {
                int orderId = resultSet.getInt("order_id");
                int userId = resultSet.getInt("user_id");
                System.out.println("order id is:" + orderId);
                System.out.println("user id is:" + userId);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private ShardingDataSource createShardingDataSource(String logicTable)
    {
        DataSource db0DataSource = createDataSource("db0");
        Map<String, DataSource> dataSourceMap = Maps.newHashMapWithExpectedSize(1);
        dataSourceMap.put("db0", db0DataSource);
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
        TableRule tableRule = new TableRule(logicTable, Arrays.asList("t_order_0","t_order_1"), dataSourceRule);
        MyOrderSingleKeyTableShardingAlgorithm algorithm = new MyOrderSingleKeyTableShardingAlgorithm();
        TableShardingStrategy tableShardingStrategy = new TableShardingStrategy("order_id", algorithm);
        ShardingRule shardingRule = new ShardingRule(dataSourceRule, Arrays.asList(tableRule), tableShardingStrategy);
        ShardingDataSource shardingDataSource = new ShardingDataSource(shardingRule);
        return shardingDataSource;
    }
    
    private static DataSource createDataSource(final String dataSourceName)
    {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("123456");
        return result;
    }

}
