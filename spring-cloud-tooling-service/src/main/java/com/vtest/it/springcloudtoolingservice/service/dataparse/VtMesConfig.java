package com.vtest.it.springcloudtoolingservice.service.dataparse;

import com.vtest.it.springcloudtoolingservice.domain.MesConfigBean;
import org.apache.ibatis.annotations.Param;

public interface VtMesConfig {
    public MesConfigBean getBean(@Param("waferId") String waferId, @Param("cpProcess") String cpProcess);

    public String getBinDescription(@Param("waferId") String waferId, @Param("cpProcess") String cpProcess);

    public String currentStepCheck(@Param("waferId") String waferId);

    public String getLastStep(@Param("waferId") String waferId);
}
