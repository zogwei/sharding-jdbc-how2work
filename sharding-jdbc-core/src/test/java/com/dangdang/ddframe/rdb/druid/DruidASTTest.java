/*
 * 文 件 名:  DruidASTTest.java
 * 版    权:   . Copyright 2008-2015,  All rights reserved Hiaward Information Technology Co.,Ltd.
 * 描    述:  <描述>
 * 修 改 人:  chen.simon
 * 修改时间:  2016-2-12
 */
package com.dangdang.ddframe.rdb.druid;

import org.junit.Test;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Chenxiaguang
 * @version  [版本号, 2016-2-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DruidASTTest
{
    
    @Test
    public void testSQLVariantRefExpr001()
    {
        /**
         * sql中的问号占位符，:xxx这种命名参数，@max_order_id:= 这种
         */
        String sql = "select @max_order_id:=max(order_id) from order where id=? and user_id=:userId";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLExprTableSource001()
    {
        /**
         * from后面的order就是SQLExprTableSource
         */
        String sql = "select * from order where id=? and user_id=:userId";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLExprTableSource002()
    {
        /**
         * from后面的order就是SQLExprTableSource 与testSQLExprTableSource001相同
         */
        String sql = "select * from order o where o.id=1";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLExprTableSource003()
    {
        /**
         * from后面的order是SQLExprTableSource，但order_alias不是，<br/>
         * order_alias 是SQLSubqueryTableSource <br/>
         * SQLSubqueryTableSource与SQLExprTableSource都是SQLTableSourceImpl的子类 <br/>
         */
        String sql = "select * from (select * from order o where o.id=1) order_alias";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLPropertyExpr001()
    {
        /**
         * 此处的name和id都是SQLPropertyExpr<br/>
         * 接在表别名点号后面的是SQLPropertyExpr
         */
        String sql = "select o.name from order o where o.id=1";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLPropertyExpr002()
    {
        /**
         * 此处没有SQLPropertyExpr
         */
        String sql = "select id from order where id=1";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLBinaryOpExpr001()
    {
        /**
         * 此处没有SQLPropertyExpr
         */
        String sql = "select 1+2";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLInListExpr001()
    {
        /**
         * targetList是in后面的列表 expr是in前面的表达式
         */
        String sql = "select * from user u where u.id in (1,3,4,5)";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
    
    @Test
    public void testSQLBetweenExpr001()
    {
        /**
         * targetList是in后面的列表 expr是in前面的表达式
         */
        String sql = "select * from t_order_0 where order_id between 1004 and 1008";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement= parser.parseStatement();
        System.out.println(sqlStatement);
    }
}
