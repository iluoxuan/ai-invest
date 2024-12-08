package com.swak.ai.invest.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.swak.ai.inverst.common.entity.stock.StockSearchReq;
import com.swak.ai.invest.dao.domain.StockDo;
import com.swak.lib.dao.mybatis.plus.ext.LimitSql;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    default List<StockDo> selectAll() {
        return this.selectList(null);
    }

    default StockDo getByTsCode(String tsCode) {
        return selectOne(Wrappers.lambdaQuery(StockDo.class)
                .eq(StockDo::getTsCode, tsCode)
        );
    }

    default List<StockDo> search(StockSearchReq req) {
        return selectList(Wrappers.lambdaQuery(StockDo.class)
                .like(StockDo::getTsCode, req.getKeyWord())
                .or()
                .like(StockDo::getCnSpell, req.getKeyWord())
                .last(LimitSql.of(req.getLimit()).toStr())
        );
    }
}
