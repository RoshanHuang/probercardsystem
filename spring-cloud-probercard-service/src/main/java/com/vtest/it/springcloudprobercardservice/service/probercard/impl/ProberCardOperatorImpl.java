package com.vtest.it.springcloudprobercardservice.service.probercard.impl;

import com.vtest.it.springcloudprobercardservice.dao.ProberCardOperatorDao;
import com.vtest.it.springcloudprobercardservice.domain.*;
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

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    public void addProberCardInfo(ProberCardEntityBean bean) {
            proberCardOperator.addProberCardInfo(bean);
            proberCardOperator.proberCardCreateState(bean.getProberCardId(), "New_Prod", "IQC", "V149");
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMinTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMaxTime'")
    })
    public void addNewIqcRecord(IqcRecordBean bean) {
           proberCardOperator.addNewIqcRecord(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    public void addNewBackRecord(BackProberCardBean bean) {
         proberCardOperator.addNewBackRecord(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    public void outProberCard(OutProberCardBean bean) {
        proberCardOperator.outProberCard(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache", key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache", key = "'getReleaseCardInfo&'+#bean.proberCardId")
    })
    public void addnewReleaseProberCard(ReleaseProberCardBean bean) {
        proberCardOperator.addnewReleaseProberCard(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllMaintainRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    public void addNewMaintainRecord(ProberCardMaintainBean bean) {
        proberCardOperator.addNewMaintainRecord(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardEX'")
    })
    public void addProberCardEX(ProberCardExtensionBean bean) {
        proberCardOperator.addProberCardEX(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    public void deleteProberCardInfo(String cardId) {
        proberCardOperator.deleteProberCardInfo(cardId);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'")
    })
    public void proberCardCreateState(String proberCardId, String lastProcess, String currentProcess, String operator) {
        proberCardOperator.proberCardCreateState(proberCardId,lastProcess,currentProcess,operator);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#proberCardId")
    })
    public void updateProberCardState(String proberCardId, String newStatus, String oldStatus, String operator) {
        proberCardOperator.updateProberCardState(proberCardId,newStatus,oldStatus,operator);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getCard&'+#bean.proberCardId")
    })
    public void updateProberCard(ProberCardEntityBean bean) {
        proberCardOperator.updateProberCard(bean);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardReleaseFlag&'+#proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getReleaseCardInfo&'+#proberCardId")

    })
    public boolean updateProberCardReleaseFlag(String proberCardId, boolean releaseFlag) {
        return  proberCardOperator.updateProberCardReleaseFlag(proberCardId,releaseFlag);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardReleaseFlag&'+#proberCardId")
    })
    public boolean updateProberCardInfoReleaseFlag(String proberCardId, boolean releaseFlag) {
        return proberCardOperator.updateProberCardInfoReleaseFlag(proberCardId,releaseFlag);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache",key = "'getProberCardStatus&'+#proberCardId")
    })
    public boolean updateSingleState(String proberCardId, String currentProcess) {
        return proberCardOperator.updateSingleState(proberCardId,currentProcess);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllList'"),
            @CacheEvict(value = "ProberCardCache",key = "'getCard&'+#proberCardId"),
            @CacheEvict(value = "ProberCardCache",key = "'getInfoRebuildCount&'+#proberCardId")
    })
    public boolean updateProberCardItem(String proberCardId, String pinlenSpec, String pindiamSpec, String pinlevelSpec, Integer rebuildCount) {
        return proberCardOperator.updateProberCardItem(proberCardId,pinlenSpec,pindiamSpec,pinlevelSpec,rebuildCount);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllMaintainRecord'")
    })
    public boolean updateMaintainItem(String proberCardId, double afterPinlen, double afterPindiam, double afterPinlevel) {
        return proberCardOperator.updateMaintainItem(proberCardId,afterPinlen,afterPindiam,afterPinlevel);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecord'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMinTime'"),
            @CacheEvict(value = "ProberCardCache",key = "'getAllIQCRecordByMaxTime'")
    })
    public boolean updateIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
        return proberCardOperator.updateIQCItem(proberCardId,pinMinlen,pinMaxdiam,pinLevel);
    }

    @Override
    @CacheEvict(value = "ProberCardCache",key = "'getTd'")
    public boolean cleanPM(String cardid, String ownerid) {
        return proberCardOperator.cleanPM(cardid,ownerid);
    }
    @Override
    @Caching(evict = {
            @CacheEvict(value = "ProberCardCache", key = "'getAllProberCardStatus'"),
            @CacheEvict(value = "ProberCardCache", key = "'getReleaseCardInfo&'+#bean.proberCardId")
    })
    public void addCheckProberCard(ReleaseProberCardBean bean) {
         proberCardOperator.addCheckProberCard(bean);
    }
}
