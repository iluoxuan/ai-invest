package com.swak.ai.invest.data.stock.xueqiu;

import lombok.Data;

import java.util.Map;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Data
public class XueQiuPcToken {

    private String md5Flag;

    Map<String, String> cookies;
}
