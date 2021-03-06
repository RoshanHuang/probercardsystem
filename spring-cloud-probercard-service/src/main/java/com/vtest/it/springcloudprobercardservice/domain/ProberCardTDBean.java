package com.vtest.it.springcloudprobercardservice.domain;

import java.io.Serializable;

public class ProberCardTDBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String probercard;
    private Integer td;
    private Integer tdTotal;
    private Integer remainTd;
    private Integer remainTotal;
    private Integer pmSpec;

    public Integer getPmSpec() {
        return pmSpec;
    }

    public void setPmSpec(Integer pmSpec) {
        this.pmSpec = pmSpec;
    }

    public String getProbercard() {
        return probercard;
    }

    public void setProbercard(String probercard) {
        this.probercard = probercard;
    }

    public Integer getTd() {
        return td;
    }

    public void setTd(Integer td) {
        this.td = td;
    }

    public Integer getTdTotal() {
        return tdTotal;
    }

    public void setTdTotal(Integer tdTotal) {
        this.tdTotal = tdTotal;
    }

    public Integer getRemainTd() {
        return remainTd;
    }

    public void setRemainTd(Integer remainTd) {
        this.remainTd = remainTd;
    }

    public Integer getRemainTotal() {
        return remainTotal;
    }

    public void setRemainTotal(Integer remainTotal) {
        this.remainTotal = remainTotal;
    }
}
