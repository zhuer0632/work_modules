package com.comdev.db.ddl.impl;

/**
 * User: zhu
 * Date: 13-5-12
 * Time: 下午12:10
 */
public class packageinfo
{
}


/**
 *
 *  当前类是对IDbMeta的实现
 *
 *  只有sqlserver比较特殊，各种版本之间的变化比较大
 *
 *  2000-->sqlserver2000
 *  2005-->sqlserver2005和sqlserver2008
 *  2012-->sqlserver2012
 * ------------------------------------------------------------------------------------
 *
 *  SqlServer2000 --> 8.0
 *  SqlServer2005 --> 9.0
 *  SqlServer2008 --> 10.0
 *  SqlServer2010 --> 11.0
 *
 */


/**
 *
 *  字段处理原则
 *  可以添加，可以修改,可以删除
 *
 *  注意修改的时候，会先进行数据检查，如果已有的数据不能满足情况
 *
 *
 *
 */



