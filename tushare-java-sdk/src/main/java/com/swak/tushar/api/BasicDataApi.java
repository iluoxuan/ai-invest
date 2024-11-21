package com.swak.tushar.api;

import com.swak.tushar.entity.basic.Stock;
import com.swak.tushar.entity.basic.StockCompany;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
public interface BasicDataApi {


    /**
     * 股票基本信息列表
     *
     * @return
     */
    List<Stock> stockBasic();

    /**
     * 股票公司基本信息列表
     *
     * @return
     */
    List<StockCompany> stockCompany();
}
