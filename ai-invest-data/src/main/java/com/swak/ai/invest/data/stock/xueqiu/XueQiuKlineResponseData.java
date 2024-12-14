package com.swak.ai.invest.data.stock.xueqiu;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
@Slf4j
@Data
public class XueQiuKlineResponseData {

    private List<String> column;

    private List<List<Object>> item;

    public <T> List<T> create(Class<T> tClass) {

        List<T> result = new ArrayList<>();

        for (List<Object> values : item) {
            T object = BeanUtils.instantiateClass(tClass);

            for (int i = 0; i < values.size(); i++) {
                String fieldStr = column.get(i);
                Object value = values.get(i);

                if (Objects.isNull(value)) {
                    continue;
                }

                try {

                    Field field = ReflectionUtils.findField(object.getClass(), StrUtil.toCamelCase(fieldStr));
                    ReflectionUtils.makeAccessible(field);
                    if (value instanceof Number && field.getType().equals(BigDecimal.class)) {
                        ReflectionUtils.setField(field, object, NumberUtil.toBigDecimal((Number) value));
                    } else {
                        ReflectionUtils.setField(field, object, value);
                    }
                } catch (Exception e) {
                    log.error("create fieldStr={} value={} error", fieldStr, value, e);
                }
            }
            result.add(object);

        }

        return result;

    }
}
