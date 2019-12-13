package com.vtest.it.springcloudtoolingservice.service.dataparse;

import com.vtest.it.springcloudtoolingservice.domain.testerLocationBean;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface TesterLocation {
    public ArrayList<testerLocationBean> getTesterLocation();
    public String getPassBin(@Param("waferId") String waferId);
    public void sendWaferMail(@Param("productId") String productId, @Param("lotId") String lotId, @Param("waferNo") String waferNo, @Param("testerId") String testerId);
}
