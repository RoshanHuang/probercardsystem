package common.domain;

import java.io.Serializable;

public class FTTouchInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String eqid;
    private String tdtotal;
    private String tdspec;

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public String getTdtotal() {
        return tdtotal;
    }

    public void setTdtotal(String tdtotal) {
        this.tdtotal = tdtotal;
    }

    public String getTdspec() {
        return tdspec;
    }

    public void setTdspec(String tdspec) {
        this.tdspec = tdspec;
    }
}
