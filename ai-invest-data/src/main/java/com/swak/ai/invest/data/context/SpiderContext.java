package com.swak.ai.invest.data.context;

import com.swak.ai.invest.data.stock.xueqiu.XueQiuStockQuote;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ljq
 * @date: 2024/12/14
 */
@Getter
@Setter
public class SpiderContext {

    private static final ThreadLocal<SpiderContext> context = ThreadLocal.withInitial(SpiderContext::new);

    private XueQiuStockQuote xueQiuStockQuote;

    public static SpiderContext getInstance() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }
}
