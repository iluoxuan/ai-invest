package com.swak.ai.invest.dao.mapper;

import com.swak.ai.invest.dao.domain.StockDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 股票信息表 Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2024-11-21
 */
@Mapper
public interface StockMapper extends BaseMapper<StockDo> {

}
