package com.vtest.it.springcloudtoolingservice.service.dataparse;

import com.vtest.it.springcloudtoolingservice.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

public interface TesterData {
    public int insertFailDieToBinInfo(@Param("customerCode") String customerCode, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId, @Param("failDieList") ArrayList<FailDieBean> failDieArray);
    public int insertSiteInforToBinInfoSummary(@Param("customerCode") String customerCode, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId, @Param("siteMap") HashMap<Integer, HashMap<Integer, Integer>> siteMap, @Param("testType") String testType, @Param("passBins") ArrayList<Integer> passBins);
    public int deleteSiteInforToBinInfoSummary(@Param("customerCode") String customerCode, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId);
    public int insertEquipmentInforToeqCardSummary(EquipmentBean equipmentBean);
    public int insertWaferInforToBinWaferSummary(BinWaferInforBean binWaferInforBean);
    public ArrayList<waferYieldBean> getWaferBinSummary(@Param("customerCode") String customerCode, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId);
    public ArrayList<waferYieldBean> getWaferBinSummaryByType(@Param("customerCode") String customerCode, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId, @Param("type") String type);
    public ArrayList<waferIdInforBean> getOthersParam(@Param("customerCode") String customerCode, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId);
    public ArrayList<CustomerAndDevicesBean> getCustomerAndDevices();
    public ArrayList<LotAndCpsBean> getLotAndCp(@Param("custom") String custom, @Param("device") String device);
    public ArrayList<WaferIdBean> getWaferIds(@Param("custom") String custom, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp);
    public ArrayList<GetWaferInforBean> getQureyInfors(@Param("custom") String custom, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId);
    public ArrayList<LotSummaryWaferBean> getwaferInfor(@Param("custom") String custom, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp);
    public ArrayList<waferYieldBean> getWaferBinSummaryUnifiedEntrance(@Param("customerCode") String customerCode, @Param("device") String device, @Param("lot") String lot, @Param("cp") String cp, @Param("waferId") String waferId, @Param("type") String type);
    public ArrayList<PrimaryTestYieldBean> getPrimaryTestYield(@Param("lot") String lot, @Param("cp") String cpStep);
}
