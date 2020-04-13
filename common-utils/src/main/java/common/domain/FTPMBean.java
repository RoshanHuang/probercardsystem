package common.domain;

import java.io.Serializable;
import java.util.Date;

public class FTPMBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String eqid;
    private Date pmtime;

    public String getEqid() {
        return eqid;
    }

    public void setEqid(String eqid) {
        this.eqid = eqid;
    }

    public Date getPmtime() {
        return pmtime;
    }

    public void setPmtime(Date pmtime) {
        this.pmtime = pmtime;
    }
}
