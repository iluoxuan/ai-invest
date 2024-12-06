package com.swak.ai.invest.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.swak.ai.invest.dao.domain.StockDailyBasicDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 股票每日指标 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Mapper
public interface StockDailyBasicMapper extends BaseMapper<StockDailyBasicDo> {

    default StockDailyBasicDo getByTsCode(String tsCode) {
        return selectOne(Wrappers.lambdaQuery(StockDailyBasicDo.class)
                .eq(StockDailyBasicDo::getTsCode, tsCode)
        );
    }
}
