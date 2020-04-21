package com.vtest.it.springcloudtoolingservice.dao.testerLocation;

import com.vtest.it.springcloudtoolingservice.domain.MesConfigBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VtMesConfigDao {
    public MesConfigBean getBean(@Param("waferId") String waferId, @Param("cpProcess") String cpProcess);

    public String getBinDescription(@Param("waferId") String waferId, @Param("cpProcess") String cpProcess);

    public String currentStepCheck(@Param("waferId") String waferId);

    public String getLastStep(@Param("waferId") String waferId);
}
