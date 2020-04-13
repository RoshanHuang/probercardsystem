package common.domain;

import java.io.Serializable;

public class ToolingTDBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String toolingId;
    private String type;
    private String tdTotal;
    private String tdSpec;

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

    public String getTdTotal() {
        return tdTotal;
    }

    public void setTdTotal(String tdTotal) {
        this.tdTotal = tdTotal;
    }

    public String getTdSpec() {
        return tdSpec;
    }

    public void setTdSpec(String tdSpec) {
        this.tdSpec = tdSpec;
    }
}
