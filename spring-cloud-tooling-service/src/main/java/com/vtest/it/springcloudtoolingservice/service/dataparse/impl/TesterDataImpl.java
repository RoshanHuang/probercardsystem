package com.vtest.it.springcloudtoolingservice.service.dataparse.impl;

import com.vtest.it.springcloudtoolingservice.dao.tester.TesterDataDao;
import com.vtest.it.springcloudtoolingservice.domain.*;
import com.vtest.it.springcloudtoolingservice.service.dataparse.TesterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class TesterDataImpl implements TesterData {
    @Autowired
    private TesterDataDao testerDataDao;
    @Override
    public int insertFailDieToBinInfo(String customerCode, String device, String lot, String cp, String waferId, ArrayList<FailDieBean> failDieArray) {
        return testerDataDao.insertFailDieToBinInfo(customerCode,device,lot,cp,waferId,failDieArray);
    }

    @Override
    public int insertSiteInforToBinInfoSummary(String customerCode, String device, String lot, String cp, String waferId, HashMap<Integer, HashMap<Integer, Integer>> siteMap, String testType, ArrayList<Integer> passBins) {
        return testerDataDao.insertSiteInforToBinInfoSummary(customerCode,device,lot,cp,waferId,siteMap,testType,passBins);
    }

    @Override
    public int deleteSiteInforToBinInfoSummary(String customerCode, String device, String lot, String cp, String waferId) {
        return testerDataDao.deleteSiteInforToBinInfoSummary(customerCode,device,lot,cp,waferId);
    }

    @Override
    public int insertEquipmentInforToeqCardSummary(EquipmentBean equipmentBean) {
        return testerDataDao.insertEquipmentInforToeqCardSummary(equipmentBean);
    }

    @Override
    public int insertWaferInforToBinWaferSummary(BinWaferInforBean binWaferInforBean) {
        return testerDataDao.insertWaferInforToBinWaferSummary(binWaferInforBean);
    }

    @Override
    public ArrayList<waferYieldBean> getWaferBinSummary(String customerCode, String device, String lot, String cp, String waferId) {
        return testerDataDao.getWaferBinSummary(customerCode,device,lot,cp,waferId);
    }

    @Override
    public ArrayList<waferYieldBean> getWaferBinSummaryByType(String customerCode, String device, String lot, String cp, String waferId, String type) {
        return testerDataDao.getWaferBinSummaryByType(customerCode,device,lot,cp,waferId,type);
    }

    @Override
    public ArrayList<waferIdInforBean> getOthersParam(String customerCode, String device, String lot, String cp, String waferId) {
        return testerDataDao.getOthersParam(customerCode,device,lot,cp,waferId);
    }

    @Override
    public ArrayList<CustomerAndDevicesBean> getCustomerAndDevices() {
        return testerDataDao.getCustomerAndDevices();
    }

    @Override
    public ArrayList<LotAndCpsBean> getLotAndCp(String custom, String device) {
        return testerDataDao.getLotAndCp(custom,device);
    }

    @Override
    public ArrayList<WaferIdBean> getWaferIds(String custom, String device, String lot, String cp) {
        return testerDataDao.getWaferIds(custom,device,lot,cp);
    }

    @Override
    public ArrayList<GetWaferInforBean> getQureyInfors(String custom, String device, String lot, String cp, String waferId) {
        return testerDataDao.getQureyInfors(custom, device, lot, cp, waferId);
    }

    @Override
    public ArrayList<LotSummaryWaferBean> getwaferInfor(String custom, String device, String lot, String cp) {
        return testerDataDao.getwaferInfor(custom, device, lot, cp);
    }

    @Override
    public ArrayList<waferYieldBean> getWaferBinSummaryUnifiedEntrance(String customerCode, String device, String lot, String cp, String waferId, String type) {
        return testerDataDao.getWaferBinSummaryUnifiedEntrance(customerCode, device, lot, cp, waferId, type);
    }

    @Override
    public ArrayList<PrimaryTestYieldBean> getPrimaryTestYield(String lot, String cpStep) {
        return testerDataDao.getPrimaryTestYield(lot, cpStep);
    }
}
