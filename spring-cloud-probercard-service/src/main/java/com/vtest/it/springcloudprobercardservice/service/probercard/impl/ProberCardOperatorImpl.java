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
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
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
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#bean.proberCardId")
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
            @CacheEvict(value = "ProberCardCache",key = "'getExistFlag&'+#bean.proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public void addRBIqcRecord(IqcRecordBean  bean){
        Integer count=proberCardInformation.getExistFlag(bean.getProberCardId());
        if(count>0){
          if(bean.getLastProcess().equals("ReBuild_Back")){
              proberCardOperator.addRBIqcRecord(bean);
          }else {
              proberCardOperator.addNewIqcRecord(bean);
          }
        }else {
            proberCardOperator.addNewIqcRecord(bean);
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
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#bean.proberCardId")
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
            @CacheEvict(value = "ProberCardCache",key = "'getCard&'+#bean.proberCardId")
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
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardReleaseFlag&'+#proberCardId")
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
            @CacheEvict(value = "ProberCardCache",key = "'getInfoRebuildCount&'+#proberCardId")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateProberCardItem(String proberCardId, Integer rebuildCount) {
        return proberCardOperator.updateProberCardItem(proberCardId,rebuildCount);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllMaintainRecord'")
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
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByRebuild'")
    })
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean updateIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
        return proberCardOperator.updateIQCItem(proberCardId,pinMinlen,pinMaxdiam,pinLevel);
    }

    @Override
    @CacheEvict(value = "ProberCardCache",key = "'getTd'")
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
    @CacheEvict(value = "ProberCardCache",key = "'getTd'")
    @Transactional(value = "transactionManager",propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor =Exception.class)
    public boolean cleanTD(String cardid,String ownerid){
      return proberCardOperator.cleanTD(cardid,ownerid);
    }
}
