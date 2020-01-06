package com.vtest.it.springcloudprobercardservice.service.probercard.impl;


import com.vtest.it.springcloudprobercardservice.dao.ProberCardInformationDao;
import com.vtest.it.springcloudprobercardservice.domain.*;
import com.vtest.it.springcloudprobercardservice.service.probercard.ProberCardInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class ProberCardInformationImpl implements ProberCardInformation {

    @Autowired
    private ProberCardInformationDao informationDao;

    @Override
    @Cacheable(value = "ProberCardCache", key = "#root.methodName")
    public List<ProberCardEntityBean> getAllList() {
        return informationDao.getAllList();
    }

    @Override
    @Cacheable(value = "ProberCardCache", key = "#root.methodName+'&'+#cardId")
    public ProberCardEntityBean getCard(String cardId) {
        return informationDao.getCard(cardId);
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public String getProberCardStatus(String proberCardId) {
        return informationDao.getProberCardStatus(proberCardId);
    }

    @Override
    @Cacheable(value = "ProberCardCache", key = "#root.methodName+'&'+#proberCardId", unless = "#result==null")
    public ReleaseProberCardBean getReleaseCardInfo(String proberCardId) {
        return informationDao.getReleaseCardInfo(proberCardId);
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<ProberCardStatusBean> getAllProberCardStatus() {
        return informationDao.getAllProberCardStatus();
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public boolean getProberCardReleaseFlag(String proberCardId) {
        try {
            return informationDao.getProberCardReleaseFlag(proberCardId);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<IqcRecordBean> getAllIQCRecord() {
        return informationDao.getAllIQCRecord();
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<ProberCardMaintainBean> getAllMaintainRecord() {
        return informationDao.getAllMaintainRecord();
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public Integer getInfoRebuildCount(String proberCardId) {
        return informationDao.getInfoRebuildCount(proberCardId);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<ProberCardTDBean> getTd() {
        return informationDao.getTd();
    }

    @Override
    public ArrayList<OutProberCardBean> getOutProberCard(String[] proberCardIdArrays) {
        return informationDao.getOutProberCard(proberCardIdArrays);
    }

    @Override
    public ArrayList<BackProberCardBean> getBackProberCard(String[] proberCardIdArrays) {
        return informationDao.getBackProberCard(proberCardIdArrays);
    }

    @Override
    public ArrayList<ProberCardEntityBean> getInfoProberCard(String[] proberCardIdArrays) {
        return informationDao.getInfoProberCard(proberCardIdArrays);
    }

    @Override
    public ArrayList<IqcRecordBean> getIQCProberCard(String[] proberCardIdArrays) {
        return informationDao.getIQCProberCard(proberCardIdArrays);
    }

    @Override
    public ArrayList<ProberCardMaintainBean> getMaintainProberCard(String[] proberCardIdArrays) {
        return informationDao.getMaintainProberCard(proberCardIdArrays);
    }

    @Override
    public ArrayList<ReleaseProberCardBean> getReleaseProberCard(String[] proberCardIdArrays) {
        return informationDao.getReleaseProberCard(proberCardIdArrays);
    }

    @Override
    public ArrayList<ProberCardEntityBean> getProberCardId(String[] custNameArrays) {
        return informationDao.getProberCardId(custNameArrays);
    }

    @Override
    public ArrayList<ProberCardExtensionBean> getEXRecord(String[] proberCardIdArrays) {
        return informationDao.getEXRecord(proberCardIdArrays);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<String> getProberCardEX() {
        return informationDao.getProberCardEX();
    }

    @Override
    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public ArrayList<ProberCardExtensionBean> getEXInfoSingle(String proberCardId) {
        return informationDao.getEXInfoSingle(proberCardId);
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<IqcRecordBean> getAllIQCRecordByMinTime() {
        return informationDao.getAllIQCRecordByMinTime();
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<IqcRecordBean> getAllIQCRecordByMaxTime() {
        return informationDao.getAllIQCRecordByMaxTime();
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName")
    public ArrayList<IqcRecordBean> getAllIQCRecordByRebuild(){
        return informationDao.getAllIQCRecordByRebuild();
    }

    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public Integer getExistFlag(String proberCardId){
        return  informationDao.getExistFlag(proberCardId);
    }
    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public ArrayList<ProberCardEntityBean> getSpec(String proberCardId){
        return  informationDao.getSpec(proberCardId);
    }
    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public double getPinMinByMaxTime(String proberCardId){
        return  informationDao.getPinMinByMaxTime(proberCardId);
    }
    @Override
    @Cacheable(value = "ProberCardCache",key = "#root.methodName+'&'+#proberCardId")
    public double getAfterPinByMaxTime(String proberCardId){
        return  informationDao.getAfterPinByMaxTime(proberCardId);
    }
}
