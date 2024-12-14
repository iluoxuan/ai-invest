package com.swak.ai.invest.common.entity;

/**
 * @author: ljq
 * @date: 2024/12/14
 */
public enum MarketEnum {


    SH,

    HK,

    ;

    public boolean isMe(String market){
        return this.name().equalsIgnoreCase(market);
    }
}
