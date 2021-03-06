package com.vtest.it.springcloudprobercardservice.service.probercard.impl;

import com.vtest.it.springcloudprobercardservice.dao.ProberCardOperatorDao;
import com.vtest.it.springcloudprobercardservice.domain.*;
import com.vtest.it.springcloudprobercardservice.service.probercard.ProberCardInformation;
import com.vtest.it.springcloudprobercardservice.service.probercard.ProberCardOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
public class ProberCardOperatorImpl implements ProberCardOperator {
    @Autowired
    private ProberCardOperatorDao proberCardOperator;

    @Autowired
    private ProberCardInformation proberCardInformation;
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache", key = "'getSpec&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class )
    public void addProberCardInfo(ProberCardEntityBean bean) {
            proberCardOperator.addProberCardInfo(bean);
            proberCardOperator.proberCardCreateState(bean.getProberCardId(), "New_Prod", "IQC", bean.getCreator());
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMinTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMaxTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByRebuild'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#bean.proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getPinMinByMaxTime&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addNewIqcRecord(IqcRecordBean bean) {
           proberCardOperator.addNewIqcRecord(bean);
           proberCardOperator.updateProberCardState(bean.getProberCardId(), bean.getNextStation(),bean.getLastProcess(),bean.getUpdateOperator());
           proberCardOperator.proberStateHistory(bean.getProberCardId(),bean.getLastProcess(),bean.getNextStation(),bean.getUpdateOperator());
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMinTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMaxTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByRebuild'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#bean.proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getExistFlag&'+#bean.proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getPinMinByMaxTime&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addRBIqcRecord(IqcRecordBean  bean){
        Integer count=proberCardInformation.getExistFlag(bean.getProberCardId());
        proberCardOperator.addNewIqcRecord(bean);
        if(count>0){
          if(bean.getLastProcess().equals("ReBuild_Back")){
              proberCardOperator.updateRBIqcRecord(bean);
          }
        }else {
            proberCardOperator.addRBIqcRecord(bean);
        }
        proberCardOperator.updateProberCardState(bean.getProberCardId(), bean.getNextStation(),bean.getLastProcess(),bean.getUpdateOperator());
        proberCardOperator.proberStateHistory(bean.getProberCardId(),bean.getLastProcess(),bean.getNextStation(),bean.getUpdateOperator());
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addNewBackRecord(BackProberCardBean bean) {
         proberCardOperator.addNewBackRecord(bean);
         proberCardOperator.updateProberCardState(bean.getProberCardId(), bean.getNextStation(),proberCardInformation.getProberCardStatus(bean.getProberCardId()), bean.getCreateOperator());
         proberCardOperator.proberStateHistory(bean.getProberCardId(),proberCardInformation.getProberCardStatus(bean.getProberCardId()),bean.getNextStation(),bean.getCreateOperator());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void outProberCard(OutProberCardBean bean) {
        proberCardOperator.outProberCard(bean);
        proberCardOperator.updateProberCardState(bean.getProberCardId(), bean.getNextStation(),proberCardInformation.getProberCardStatus(bean.getProberCardId()), bean.getOutOperator());
        proberCardOperator.proberStateHistory(bean.getProberCardId(),proberCardInformation.getProberCardStatus(bean.getProberCardId()), bean.getNextStation(),bean.getOutOperator());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache", key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache", key = "'getReleaseCardInfo&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addnewReleaseProberCard(ReleaseProberCardBean bean) {
        proberCardOperator.addnewReleaseProberCard(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllMaintainRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#bean.proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getAfterPinByMaxTime&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addNewMaintainRecord(ProberCardMaintainBean bean) {
        proberCardOperator.addNewMaintainRecord(bean);
        proberCardOperator.updateProberCardState(bean.getProberCardId(),bean.getNextStation(), proberCardInformation.getProberCardStatus(bean.getProberCardId()), bean.getUpdateOperator());
        proberCardOperator.proberStateHistory(bean.getProberCardId(),proberCardInformation.getProberCardStatus(bean.getProberCardId()),bean.getNextStation(), bean.getUpdateOperator());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardEX'")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addProberCardEX(ProberCardExtensionBean bean) {
        proberCardOperator.addProberCardEX(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void deleteProberCardInfo(String cardId) {
        proberCardOperator.deleteProberCardInfo(cardId);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void proberCardCreateState(String proberCardId, String lastProcess, String currentProcess, String operator) {
        proberCardOperator.proberCardCreateState(proberCardId,lastProcess,currentProcess,operator);
    }
    @Override
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void proberStateHistory(String proberCardId,String lastProcess,String currentProcess,String operator){
        proberCardOperator.proberStateHistory(proberCardId,lastProcess,currentProcess,operator);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void updateProberCardState(String proberCardId, String newStatus, String oldStatus, String operator) {
        proberCardOperator.updateProberCardState(proberCardId,newStatus,oldStatus,operator);
        proberCardOperator.proberStateHistory(proberCardId,oldStatus,newStatus,operator);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getCard&'+#bean.proberCardId"),
            @CacheEvict(value = "ProberCardCache", key = "'getSpec&'+#bean.proberCardId"),
            @CacheEvict(value = "ProberCardCache", key = "'getDepthSpec&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void updateProberCard(ProberCardEntityBean bean) {
        proberCardOperator.updateProberCard(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardReleaseFlag&'+#proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getReleaseCardInfo&'+#proberCardId")

    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateProberCardReleaseFlag(String proberCardId, boolean releaseFlag) {
        return  proberCardOperator.updateProberCardReleaseFlag(proberCardId,releaseFlag);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardReleaseFlag&'+#proberCardId"),
            @CacheEvict(value = "ProberCardCache", key = "'getSpec&'+#proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateProberCardInfoReleaseFlag(String proberCardId, boolean releaseFlag) {
        return proberCardOperator.updateProberCardInfoReleaseFlag(proberCardId,releaseFlag);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateSingleState(String proberCardId, String currentProcess) {
        return proberCardOperator.updateSingleState(proberCardId,currentProcess);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getCard&'+#proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getInfoRebuildCount&'+#proberCardId"),
            @CacheEvict(value = "ProberCardCache", key = "'getSpec&'+#proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateProberCardItem(String proberCardId, Integer rebuildCount) {
        return proberCardOperator.updateProberCardItem(proberCardId,rebuildCount);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllMaintainRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAfterPinByMaxTime&'+#proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateMaintainItem(String proberCardId, double afterPinlen, double afterPindiam, double afterPinlevel) {
        return proberCardOperator.updateMaintainItem(proberCardId,afterPinlen,afterPindiam,afterPinlevel);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMinTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMaxTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByRebuild'"),
            @CacheEvict(value = "ProberCardCache",key = "'getPinMinByMaxTime&'+#proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
        return proberCardOperator.updateIQCItem(proberCardId,pinMinlen,pinMaxdiam,pinLevel);
    }

    @Override
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean cleanPM(String cardid, String ownerid) {
        return proberCardOperator.cleanPM(cardid,ownerid);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache", key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache", key = "'getReleaseCardInfo&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addCheckProberCard(ReleaseProberCardBean bean) {
         proberCardOperator.addCheckProberCard(bean);
    }
    @Override
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean cleanTD(String cardid,String ownerid){
      return proberCardOperator.cleanTD(cardid,ownerid);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMinTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMaxTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByRebuild'"),
            @CacheEvict(value = "ProberCardCache",key = "'getPinMinByMaxTime&'+#bean.proberCardId")
    })
    public void updateRBIqcRecord(IqcRecordBean bean){
        proberCardOperator.updateRBIqcRecord(bean);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMinTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMaxTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByRebuild'"),
            @CacheEvict(value = "ProberCardCache",key = "'getPinMinByMaxTime&'+#proberCardId")
    })
    public boolean updateRBIqcItem(String proberCardId,double pinMinlen,double pinMaxdiam,double pinLevel){
        return  proberCardOperator.updateRBIqcItem(proberCardId, pinMinlen, pinMaxdiam, pinLevel);
    }

    @Override
    public ArrayList<OutProberCardBean> getOutProberCard(String proberCardId) {
        return proberCardOperator.getOutProberCard(proberCardId);
    }

    @Override
    public ArrayList<BackProberCardBean> getBackProberCard(String proberCardId) {
        return proberCardOperator.getBackProberCard(proberCardId);
    }

    @Override
    public ArrayList<ProberCardEntityBean> getInfoProberCard(String proberCardId) {
        return proberCardOperator.getInfoProberCard(proberCardId);
    }

    @Override
    public ArrayList<IqcRecordBean> getIQCProberCard(String proberCardId) {
        return proberCardOperator.getIQCProberCard(proberCardId);
    }

    @Override
    public ArrayList<ProberCardMaintainBean> getMaintainProberCard(String proberCardId) {
        return proberCardOperator.getMaintainProberCard(proberCardId);
    }

    @Override
    public ArrayList<ReleaseProberCardBean> getReleaseProberCard(String proberCardId) {
        return proberCardOperator.getReleaseProberCard(proberCardId);
    }

    @Override
    public ArrayList<ProberCardEntityBean> getProberCardId(String custName) {
        return proberCardOperator.getProberCardId(custName);
    }

    @Override
    public ArrayList<ProberCardExtensionBean> getEXRecord(String proberCardId) {
        return proberCardOperator.getEXRecord(proberCardId);
    }
}
