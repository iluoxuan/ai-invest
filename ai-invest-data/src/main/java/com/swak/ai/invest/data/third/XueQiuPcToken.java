package com.swak.ai.invest.data.third;

import cn.hutool.core.map.MapUtil;
import com.swak.lib.common.tools.StringTools;
import lombok.Data;

import java.util.Map;

/**
 * @author: ljq
 * @date: 2024/11/24
 */
@Data
public class XueQiuPcToken {

    private String md5Flag;

    private String token;

    Map<String, String> cookies;

    public boolean isEmpty() {
        return StringTools.isBlank(md5Flag) || MapUtil.isEmpty(cookies);
    }
}
