package com.lxq.hotchpotch.common.core.constant;

import lombok.Getter;

/**
 * 季节
 *
 * @author luxinqiang
 */
@Getter
public enum SeasonEnum {

    /**
     *
     */
    SPRING(1, "春"), SUMMER(2, "夏"), AUTUMN(3, "秋"), WINTER(4, "冬");
    private final int seq;
    private final String desc;

    SeasonEnum(int seq, String desc) {
        this.seq = seq;
        this.desc = desc;
    }

}
