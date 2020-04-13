package common.domain;

import java.io.Serializable;
import java.util.Date;

public class ToolingOutBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String toolingId;
    private String type;
    private String useEquipment;
    private String outUseEquipment;
    private String location;
    private String outOperator;
    private String customer;
    private String nextStation;
    private String note;
    private Date loadTime;

    public String getToolingId() {
        return toolingId;
    }

    public void setToolingId(String toolingId) {
        this.toolingId = toolingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUseEquipment() {
        return useEquipment;
    }

    public void setUseEquipment(String useEquipment) {
        this.useEquipment = useEquipment;
    }

    public String getOutUseEquipment() {
        return outUseEquipment;
    }

    public void setOutUseEquipment(String outUseEquipment) {
        this.outUseEquipment = outUseEquipment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOutOperator() {
        return outOperator;
    }

    public void setOutOperator(String outOperator) {
        this.outOperator = outOperator;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNextStation() {
        return nextStation;
    }

    public void setNextStation(String nextStation) {
        this.nextStation = nextStation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
    }
}
