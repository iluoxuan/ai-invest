package com.swak.ai.invest.dao;

import com.swak.lib.dao.mybatis.plus.gen.GenInfo;
import com.swak.lib.dao.mybatis.plus.gen.MybatisPlusBaseGen;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ljq
 * @date: 2024/10/26
 */
@Slf4j
public class MybatisGen extends MybatisPlusBaseGen {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ai-invest";
        GenInfo genInfo = new GenInfo();
        genInfo.setAuthor("ljq");
        genInfo.setUserName("root");
        genInfo.setPassword("123456");
        genInfo.setJdbcUrl(url);
        genInfo.setModelName("ai-invest-dao");
        genInfo.setPackageName("com.swak.ai.invest.dao");
        genInfo.setGenTableName("stock_daily_basic");
        new MybatisGen().gen(genInfo);

    }
}
