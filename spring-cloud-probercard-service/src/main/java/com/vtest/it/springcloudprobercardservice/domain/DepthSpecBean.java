package com.vtest.it.springcloudprobercardservice.domain;

import java.io.Serializable;

public class DepthSpecBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer lowerspec;
    private Integer upperspec;

    public Integer getLowerspec() {
        return lowerspec;
    }

    public void setLowerspec(Integer lowerspec) {
        this.lowerspec = lowerspec;
    }

    public Integer getUpperspec() {
        return upperspec;
    }

    public void setUpperspec(Integer upperspec) {
        this.upperspec = upperspec;
    }
}
