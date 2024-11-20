package com.swak.tushar.entity.api;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljq
 * @date: 2024/11/20
 */
@Slf4j
@Data
public class ApiResponseData {

    private List<String> fields;

    private List<List<Object>> items;

    public <T> List<T> create(Class<T> tClass) {

        List<T> result = new ArrayList<>();

        for (List<Object> values : items) {
            T object = BeanUtils.instantiateClass(tClass);

            for (int i = 0; i < values.size(); i++) {
                String fieldStr = fields.get(i);
                Object value = values.get(i);

                try {

                    Field field = ReflectionUtils.findField(object.getClass(), StrUtil.toCamelCase(fieldStr));
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, object, value);
                } catch (Exception e) {
                    log.error("create fieldStr={} value={} error", fieldStr, value, e);
                }
            }
            result.add(object);

        }

        return result;

    }
}
