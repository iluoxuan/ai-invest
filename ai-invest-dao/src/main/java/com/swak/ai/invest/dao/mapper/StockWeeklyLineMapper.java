package com.swak.ai.invest.dao.mapper;

import com.swak.ai.invest.dao.domain.StockWeeklyLineDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 股票周线行情表 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-11-22
 */
@Mapper
public interface StockWeeklyLineMapper extends BaseMapper<StockWeeklyLineDo> {

}
