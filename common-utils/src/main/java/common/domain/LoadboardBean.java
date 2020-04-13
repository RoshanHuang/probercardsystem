package common.domain;

import java.io.Serializable;
import java.util.Date;

public class LoadboardBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customer;
    private String loadBoardName;
    private String tfccNo;
    private Integer qty;
    private String location;
    private String pakage;
    private Date releaseTime;
    private String creator;
    private String remark;
    private Date loadTime;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getLoadBoardName() {
        return loadBoardName;
    }

    public void setLoadBoardName(String loadBoardName) {
        this.loadBoardName = loadBoardName;
    }

    public String getTfccNo() {
        return tfccNo;
    }

    public void setTfccNo(String tfccNo) {
        this.tfccNo = tfccNo;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPakage() {
        return pakage;
    }

    public void setPakage(String pakage) {
        this.pakage = pakage;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }
}
