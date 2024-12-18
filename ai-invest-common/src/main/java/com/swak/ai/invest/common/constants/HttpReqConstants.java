package com.swak.ai.invest.common.constants;

/**
 * @author: ljq
 * @date: 2024/11/23
 */
public interface HttpReqConstants {

    interface Path {

        String xueqiuQuote = "/v5/stock/quote.json";
    }


    interface SpiderHeader {


        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0";

    }
}
