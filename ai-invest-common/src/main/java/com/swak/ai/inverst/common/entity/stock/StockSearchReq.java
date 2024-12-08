package com.swak.ai.inverst.common.entity.stock;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: ljq
 * @date: 2024/12/8
 */
@Data
public class StockSearchReq {

    /**
     * 拼音简称 or 代码
     */
    @NotBlank
    private String keyWord;

    private int limit = 5;

}
