package com.swak.ai.invest.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.swak.ai.invest.dao.domain.StockDailyLineDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * <p>
 * 股票日线行情表 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Mapper
public interface StockDailyLineMapper extends BaseMapper<StockDailyLineDo> {

    default StockDailyLineDo getBy(String tsCode, Date tradeDate) {
        return this.selectOne(Wrappers.lambdaQuery(StockDailyLineDo.class)
                .eq(StockDailyLineDo::getTsCode, tsCode)
                .eq(StockDailyLineDo::getTradeDate, tradeDate));
    }
}
