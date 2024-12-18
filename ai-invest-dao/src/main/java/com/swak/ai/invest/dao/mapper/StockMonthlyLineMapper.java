package com.swak.ai.invest.dao.mapper;

import com.swak.ai.invest.dao.domain.StockMonthlyLineDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 股票月线行情表 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Mapper
public interface StockMonthlyLineMapper extends BaseMapper<StockMonthlyLineDo> {

}
