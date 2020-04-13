package common.domain;


import java.io.Serializable;
import java.util.Date;

public class ToolingBackBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String toolingId;
    private String type;
    private String useEquipment;
    private String backuseEquipment;
    private String customer;
    private String location;
    private boolean isActive;
    private String failStatus;
    private String backOperator;
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

    public String getBackuseEquipment() {
        return backuseEquipment;
    }

    public void setBackuseEquipment(String backuseEquipment) {
        this.backuseEquipment = backuseEquipment;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getFailStatus() {
        return failStatus;
    }

    public void setFailStatus(String failStatus) {
        this.failStatus = failStatus;
    }

    public String getBackOperator() {
        return backOperator;
    }

    public void setBackOperator(String backOperator) {
        this.backOperator = backOperator;
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
