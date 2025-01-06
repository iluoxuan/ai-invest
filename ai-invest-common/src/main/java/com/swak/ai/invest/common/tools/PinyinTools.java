package com.swak.ai.invest.common.tools;

import cn.hutool.core.util.StrUtil;
import com.github.promeg.pinyinhelper.Pinyin;

/**
 * @author: ljq
 * @date: 2025/1/6
 */
public class PinyinTools {

    public static String toCnSpell(String str) {
        if (StrUtil.isBlank(str)) {
            return StrUtil.EMPTY;
        }

        StringBuilder builder = new StringBuilder();

        for (char cn : str.toCharArray()) {
            if (Pinyin.isChinese(cn)) {
                builder.append(Pinyin.toPinyin(cn).charAt(0));
            }
        }
        return builder.toString().toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(toCnSpell("快手-w"));
    }
}
