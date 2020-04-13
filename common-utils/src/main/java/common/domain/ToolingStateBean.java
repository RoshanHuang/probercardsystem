package common.domain;


import java.io.Serializable;

public class ToolingStateBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String toolingId;
    private String type;
    private String lastProcess;
    private String currentProcess;
    private String updateOperator;

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
