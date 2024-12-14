package com.swak.ai.invest.data.stock.xueqiu;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONPath;
import com.swak.ai.invest.common.exception.SpiderDataException;
import com.swak.ai.invest.data.config.SpiderUrl;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author: ljq
 * @date: 2024/12/8
 */
@RequiredArgsConstructor
@Service
public class XueQuiStockManager {

    private final XueQiuPcTokenHandler xueQiuPcTokenHandler;

    public XueQiuStockQuote getStockQuote(String tsCode) {

        try {

            XueQiuPcToken xueQiuPcToken = xueQiuPcTokenHandler.getToken();

            String symbol = XueQiuTools.symbol(tsCode);

            // 抓取实时数据
            String url = UriComponentsBuilder.fromHttpUrl(SpiderUrl.xueQiuDomain).path(SpiderUrl.quotePath)
                    .queryParam("symbol", symbol)
                    .queryParam("extend", "detail").build().toUriString();
            String quoteJson = Jsoup.connect(url).cookies(xueQiuPcToken.getCookies())
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute().body();
            Object quote = JSONPath.eval(quoteJson, "$.data.quote");
            return JSON.parseObject(quote.toString(), XueQiuStockQuote.class);
        } catch (Exception e) {
            throw new SpiderDataException(e);
        }
    }

    public static void main(String[] args) {

        String json = "{\"data\": {\"market\": {\"status_id\": 8,\"region\": \"CN\",\"status\": \"休市\",\"time_zone\": \"Asia/Shanghai\",\"time_zone_desc\": null,\"delay_tag\": 0},\"quote\": {\"current_ext\": null,\"symbol\": \"SZ000001\",\"volume_ext\": null,\"high52w\": 13.1473,\"delayed\": 0,\"type\": 11,\"tick_size\": 0.01,\"float_shares\": 19405617528,\"limit_down\": 10.3,\"no_profit\": \"N\",\"high\": 11.7,\"float_market_capital\": 2.26269500376e11,\"timestamp_ext\": null,\"lot_size\": 100,\"lock_set\": null,\"weighted_voting_rights\": \"N\",\"chg\": 0.22,\"eps\": 2.4,\"last_close\": 11.44,\"profit_four\": 4.6549e10,\"volume\": 172626927,\"volume_ratio\": 1.81,\"profit_forecast\": 5.2972e10,\"turnover_rate\": 0.89,\"low52w\": 8.1876,\"name\": \"平安银行\",\"exchange\": \"SZ\",\"pe_forecast\": 4.272,\"total_shares\": 19405918198,\"status\": 1,\"is_vie_desc\": \"否\",\"security_status\": null,\"code\": \"000001\",\"goodwill_in_net_assets\": 1.5430129121310419,\"avg_price\": 11.61,\"percent\": 1.92,\"weighted_voting_rights_desc\": \"无差异\",\"amplitude\": 2.36,\"current\": 11.66,\"is_vie\": \"N\",\"current_year_percent\": 35.89,\"issue_date\": 670608000000,\"sub_type\": \"1\",\"low\": 11.43,\"is_registration_desc\": \"否\",\"no_profit_desc\": \"已盈利\",\"market_capital\": 2.26273006189e11,\"dividend\": 0.965,\"dividend_yield\": 8.276,\"currency\": \"CNY\",\"navps\": 21.67,\"profit\": 4.6455e10,\"timestamp\": 1733468643000,\"pe_lyr\": 4.871,\"amount\": 2.00427046579e9,\"pledge_ratio\": 0.01,\"traded_amount_ext\": null,\"is_registration\": \"N\",\"pb\": 0.538,\"limit_up\": 12.58,\"pe_ttm\": 4.861,\"time\": 1733468643000,\"open\": 11.44},\"others\": {\"pankou_ratio\": -65.33,\"cyb_switch\": true},\"tags\": [{\"description\": \"深股通\",\"value\": 3},{\"description\": \"融\",\"value\": 6},{\"description\": \"空\",\"value\": 7}]},\"error_code\": 0,\"error_description\": \"\"}";

        Object quote = JSONPath.eval(json, "$.data.quote");
        System.out.println(quote);
        XueQiuStockQuote stockQuote = JSON.parseObject(quote.toString(), XueQiuStockQuote.class);
        System.out.println(JSON.toJSON(stockQuote));
    }
}
