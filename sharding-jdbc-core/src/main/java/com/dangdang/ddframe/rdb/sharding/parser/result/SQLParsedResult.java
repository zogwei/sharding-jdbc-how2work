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

package com.dangdang.ddframe.rdb.sharding.parser.result;

import java.util.ArrayList;
import java.util.List;

import com.dangdang.ddframe.rdb.sharding.parser.result.merger.MergeContext;
import com.dangdang.ddframe.rdb.sharding.parser.result.router.ConditionContext;
import com.dangdang.ddframe.rdb.sharding.parser.result.router.RouteContext;

import lombok.Getter;
import lombok.ToString;

/**
 * SQL解析结果.<br/>
 * add by simon <br/>
 * 解析结果中放了一堆context...其实主要解析是有阿里巴巴的druid完成，<br/>
 * 参见 @see com.dangdang.ddframe.rdb.sharding.parser.SQLParserFactory.create(DatabaseType, String, List<Object>, Collection<String>)
 * 
 * 
 * @author gaohongtao, zhangliang
 */
@Getter
@ToString
public final class SQLParsedResult {
    
    private final RouteContext routeContext = new RouteContext();
    
    private final List<ConditionContext> conditionContexts = new ArrayList<>();
    
    private final MergeContext mergeContext = new MergeContext();
}
