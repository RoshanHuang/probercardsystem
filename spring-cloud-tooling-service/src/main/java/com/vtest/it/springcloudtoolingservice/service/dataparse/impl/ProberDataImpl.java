package com.vtest.it.springcloudtoolingservice.service.dataparse.impl;

import com.vtest.it.springcloudtoolingservice.dao.prober.ProberDataDao;
import com.vtest.it.springcloudtoolingservice.domain.*;
import com.vtest.it.springcloudtoolingservice.service.dataparse.ProberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ProberDataImpl implements ProberData {
    @Autowired
   private ProberDataDao proberDataDao;
    @Override
    public int insertFailDieToBinInfo(String customerCode, String device, String lot, String cp, String waferId, ArrayList<FailDieBean> failDieArray) {
        return proberDataDao.insertFailDieToBinInfo(customerCode,device,lot,cp,waferId,failDieArray);
    }

    @Override
    public int insertSiteInforToBinInfoSummary(String customerCode, String device, String lot, String cp, String waferId, HashMap<Integer, HashMap<Integer, Integer>> siteMap, String testType, ArrayList<Integer> passBins) {
        return proberDataDao.insertSiteInforToBinInfoSummary(customerCode,device,lot,cp,waferId,siteMap,testType,passBins);
    }

    @Override
    public int deleteSiteInforToBinInfoSummary(String customerCode, String device, String lot, String cp, String waferId) {
        return proberDataDao.deleteSiteInforToBinInfoSummary(customerCode,device,lot,cp,waferId);
    }

    @Override
    public int insertEquipmentInforToeqCardSummary(EquipmentBean equipmentBean) {
        return proberDataDao.insertEquipmentInforToeqCardSummary(equipmentBean);
    }

    @Override
    public int insertWaferInforToBinWaferSummary(BinWaferInforBean binWaferInforBean) {
        return proberDataDao.insertWaferInforToBinWaferSummary(binWaferInforBean);
    }

    @Override
    public ArrayList<waferYieldBean> getWaferBinSummary(String customerCode, String device, String lot, String cp, String waferId) {
        return proberDataDao.getWaferBinSummary(customerCode,device,lot,cp,waferId);
    }

    @Override
    public ArrayList<waferIdInforBean> getOthersParam(String customerCode, String device, String lot, String cp, String waferId) {
        return proberDataDao.getOthersParam(customerCode,device,lot,cp,waferId);
    }

    @Override
    public ArrayList<CustomerAndDevicesBean> getCustomerAndDevices() {
        return proberDataDao.getCustomerAndDevices();
    }

    @Override
    public ArrayList<LotAndCpsBean> getLotAndCp(String custom, String device) {
        return proberDataDao.getLotAndCp(custom,device);
    }

    @Override
    public ArrayList<WaferIdBean> getWaferIds(String custom, String device, String lot, String cp) {
        return proberDataDao.getWaferIds(custom,device,lot,cp);
    }

    @Override
    public ArrayList<GetWaferInforBean> getQureyInfors(String custom, String device, String lot, String cp, String waferId) {
        return proberDataDao.getQureyInfors(custom,device,lot,cp,waferId);
    }

    @Override
    public ArrayList<LotSummaryWaferBean> getwaferInfor(String custom, String device, String lot, String cp) {
        return proberDataDao.getwaferInfor(custom,device,lot,cp);
    }
}
