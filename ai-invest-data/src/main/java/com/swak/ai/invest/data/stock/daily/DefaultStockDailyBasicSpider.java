package com.swak.ai.invest.data.stock.daily;

import cn.hutool.core.date.DateTime;
import com.swak.ai.invest.data.context.SpiderContext;
import com.swak.ai.invest.data.xueqiu.XueQiuStockQuote;
import com.swak.ai.invest.data.xueqiu.XueQuiStockManager;
import com.swak.lib.common.tools.AssertTools;
import com.swak.lib.common.tools.BeanTools;
import com.swak.lib.common.tools.DateTools;
import com.swak.tushar.entity.trade.StockDailyBasic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author: ljq
 * @date: 2024/12/8
 */
@RequiredArgsConstructor
@Service
public class DefaultStockDailyBasicSpider implements StockDailyBasicSpider {

    private final XueQuiStockManager xueQuiStockManager;

    @Override
    public StockDailyBasic spider(String tsCode) throws Exception {

        XueQiuStockQuote quote = xueQuiStockManager.getStockQuote(tsCode);
        AssertTools.notNull(quote, "获取当日股票信息为空");
        SpiderContext.getInstance().setXueQiuStockQuote(quote);

        StockDailyBasic dailyBasic = BeanTools.copy(quote, StockDailyBasic.class);
        dailyBasic.setTsCode(tsCode);
        dailyBasic.setPe(quote.getPeTtm());
        dailyBasic.setPs(quote.getEps());
        dailyBasic.setTotalMv(quote.getMarketCapital());
        dailyBasic.setOpen(dailyBasic.getOpen());

        // 时间戳转日期
        DateTime dateTime = DateTools.date(quote.getTimestamp());
        dailyBasic.setTradeDate(dateTime.toDateStr());
        dailyBasic.setClose(quote.getCurrent());
        return dailyBasic;
    }
}
