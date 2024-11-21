package com.swak.tushar.execute;

import com.swak.tushar.api.BasicDataApi;
import com.swak.tushar.entity.api.ApiNameEnum;
import com.swak.tushar.entity.basic.Stock;
import com.swak.tushar.entity.basic.StockCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
@RequiredArgsConstructor
@Service
public class BasicDataApiExecute implements BasicDataApi {

    private final DefaultApiExecute defaultApiExecute;

    @Override
    public List<Stock> stockBasic() {
        return defaultApiExecute.execute(ApiNameEnum.stock_basic, null, Stock.class);
    }

    @Override
    public List<StockCompany> stockCompany() {
        return defaultApiExecute.execute(ApiNameEnum.stock_company, null, StockCompany.class);
    }
}
