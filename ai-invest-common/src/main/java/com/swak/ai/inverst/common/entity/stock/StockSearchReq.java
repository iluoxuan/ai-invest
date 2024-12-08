package com.swak.ai.inverst.common.entity.stock;

import lombok.Data;

/**
 * @author: ljq
 * @date: 2024/12/8
 */
@Data
public class StockSearchReq {

    /**
     * 拼音简称
     */
    private String cnSpell;

    private String tsCode;

    private int limit = 5;

}
