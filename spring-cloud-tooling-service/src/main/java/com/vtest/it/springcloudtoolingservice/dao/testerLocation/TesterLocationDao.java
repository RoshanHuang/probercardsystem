package com.vtest.it.springcloudtoolingservice.dao.testerLocation;

import com.vtest.it.springcloudtoolingservice.domain.testerLocationBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TesterLocationDao {
    public ArrayList<testerLocationBean> getTesterLocation();
    public String getPassBin(@Param("waferId")String waferId);
    public void sendWaferMail(@Param("productId") String productId, @Param("lotId")String lotId, @Param("waferNo")String waferNo, @Param("testerId")String testerId);
}
