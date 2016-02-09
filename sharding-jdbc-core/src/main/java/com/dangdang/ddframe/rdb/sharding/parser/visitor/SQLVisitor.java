/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.parser.visitor;

import com.dangdang.ddframe.rdb.sharding.api.DatabaseType;
import com.dangdang.ddframe.rdb.sharding.parser.result.router.SQLBuilder;

/**
 * SQL解析基础访问器接口.<br/>
 * add by simon<br/>
 * 这个接口所设计的方法体现不出来Visitor的感觉，并且从目前实现这个接口的子类来看，<br/>
 * 子类的Visitor能力(或者说方法约定)是从druid的MySqlOutputVisitor过来的<br/>
 * 
 * @author zhangliang
 */
public interface SQLVisitor {
    
    /**
     * 获取数据库类型.
     * 
     * @return 数据库类型
     */
    DatabaseType getDatabaseType();
    
    /**
     * 获取解析上下文对象.
     * 
     * @return 解析上下文对象
     */
    ParseContext getParseContext();
    
    /**
     * 获取SQL构建器.
     * 
     * @return SQL构建器
     */
    SQLBuilder getSQLBuilder();
    
    /**
     * 打印替换标记.
     * 
     * @param token 替换标记
     */
    void printToken(String token);
}
