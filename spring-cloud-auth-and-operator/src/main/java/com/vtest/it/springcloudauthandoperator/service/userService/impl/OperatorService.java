package com.vtest.it.springcloudauthandoperator.service.userService.impl;

import common.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OperatorService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProbercardInfoService.class);

    public void addProberCardInfo(ProberCardEntityBean bean){
        HttpEntity<ProberCardEntityBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardInfo",entity,Object.class);
    }
    public void addNewIqcRecord(IqcRecordBean bean){
        HttpEntity<IqcRecordBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/NewIqcRecord",entity,Object.class);
    }
    public void addRBIqcRecord(IqcRecordBean bean){
        HttpEntity<IqcRecordBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/RBIqcRecord",entity,Object.class);
    }
    public void outProberCard(OutProberCardBean bean){
        HttpEntity<OutProberCardBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/outProberCard",entity,Object.class);
    }
    public void backProberCard(BackProberCardBean bean){
        HttpEntity<BackProberCardBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/backProberCard",entity,Object.class);
    }
    public void checkProberCard(ReleaseProberCardBean bean){
        HttpEntity<ReleaseProberCardBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProbercardCheck",entity,Object.class);
    }
    public void updateProberCardState(String proberCardId, String newStatus, String oldStatus, String operator){
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("newStatus",newStatus);
        requestEntity.add("oldStatus",oldStatus);
        requestEntity.add("operator",operator);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardState",requestEntity,String.class);
    }
    public void ReleaseProberCard(ReleaseProberCardBean bean){
        HttpEntity<ReleaseProberCardBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ReleaseProberCard",entity,Object.class);
    }
    public void ProberCardMaintain(ProberCardMaintainBean bean){
        HttpEntity<ProberCardMaintainBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardMaintain",entity,Object.class);
    }
    public void addProberCardEX(ProberCardExtensionBean bean){
        HttpEntity<ProberCardExtensionBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardEX",entity,Object.class);
    }
    public void updateProberCard(ProberCardEntityBean bean) {
        HttpEntity<ProberCardEntityBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCard",entity,Object.class);
    }
    public void updateProberCardReleaseFlag(String proberCardId, boolean releaseFlag) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("releaseFlag",releaseFlag);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardReleaseFlag",requestEntity,Object.class);
    }
    public void updateProberCardInfoReleaseFlag(String proberCardId, boolean releaseFlag) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("releaseFlag",releaseFlag);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardInfoReleaseFlag",requestEntity,Object.class);
    }
    public void updateSingleState(String proberCardId, String currentProcess) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("currentProcess",currentProcess);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/SingleState",requestEntity,String.class);
    }
    public void clearProberCardItem(String proberCardId, Integer rebuildCount) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("rebuildCount",rebuildCount);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardItem",requestEntity,Object.class);
    }
    public void clearMaintainItem(String proberCardId, double afterPinlen, double afterPindiam, double afterPinlevel) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("afterPinlen",afterPinlen);
        requestEntity.add("afterPindiam",afterPindiam);
        requestEntity.add("afterPinlevel",afterPinlevel);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/MaintainItem",requestEntity,Object.class);
    }
    public void clearIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("pinMinlen",pinMinlen);
        requestEntity.add("pinMaxdiam",pinMaxdiam);
        requestEntity.add("pinLevel",pinLevel);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/IQCItem",requestEntity,Object.class);
    }
    public void cleanPM(String cardid,String ownerid) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("cardid",cardid);
        requestEntity.add("ownerid",ownerid);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/PM",requestEntity,String.class);
    }
    public void deleteProberCards(String cardId) {
        HttpEntity<String> entity = new HttpEntity<>(cardId);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCards",entity,String.class);
    }
    public void cleanTD(String cardid,String ownerid) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("cardid",cardid);
        requestEntity.add("ownerid",ownerid);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ReTD",requestEntity,String.class);
    }
    public void clearRBIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId",proberCardId);
        requestEntity.add("pinMinlen",pinMinlen);
        requestEntity.add("pinMaxdiam",pinMaxdiam);
        requestEntity.add("pinLevel",pinLevel);
        restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/RBIqcItem",requestEntity,Object.class);
    }
}
