package com.vtest.it.springcloudprobercardservice.domain;

import java.io.Serializable;

public class ProberCardStatusBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String proberCardId;
    private String lastProcess;
    private String currentProcess;
    private String updateOperator;

    public String getProberCardId() {
        return proberCardId;
    }

    public void setProberCardId(String proberCardId) {
        this.proberCardId = proberCardId;
    }

    public String getLastProcess() {
        return lastProcess;
    }

    public void setLastProcess(String lastProcess) {
        this.lastProcess = lastProcess;
    }

    public String getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(String currentProcess) {
        this.currentProcess = currentProcess;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator;
    }
}
