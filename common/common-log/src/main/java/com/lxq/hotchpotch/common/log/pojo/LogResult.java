package com.lxq.hotchpotch.common.log.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author luxinqiang
 */
@Getter
@Setter
public class LogResult implements Serializable {

    private int fromLineNum;
    private int toLineNum;
    private String logContent;
    private boolean isEnd;

    public LogResult(int fromLineNum, int toLineNum, String logContent, boolean isEnd) {
        this.fromLineNum = fromLineNum;
        this.toLineNum = toLineNum;
        this.logContent = logContent;
        this.isEnd = isEnd;
    }

}
