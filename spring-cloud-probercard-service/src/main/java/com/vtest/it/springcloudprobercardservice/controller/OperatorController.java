package com.vtest.it.springcloudprobercardservice.controller;

import com.vtest.it.springcloudprobercardservice.domain.*;
import com.vtest.it.springcloudprobercardservice.service.probercard.ProberCardOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/operator")
@CrossOrigin
public class OperatorController {

    @Autowired
    private ProberCardOperator proberCardOperator;


    @PostMapping("/ProberCardInfo")
    public void addProberCardInfo(@RequestBody ProberCardEntityBean bean){
            proberCardOperator.addProberCardInfo(bean);
    }
    @PostMapping("/NewIqcRecord")
    public void addNewIqcRecord(@RequestBody IqcRecordBean bean) {
            proberCardOperator.addNewIqcRecord(bean);
    }
    @PostMapping("/RBIqcRecord")
    public void addRBIqcRecord(@RequestBody IqcRecordBean bean) {
        proberCardOperator.addRBIqcRecord(bean);
    }
    @PostMapping("/backProberCard")
    public void backProberCard(@RequestBody BackProberCardBean bean) {
            proberCardOperator.addNewBackRecord(bean);
    }
    @PostMapping("/outProberCard")
    public void outProberCard(@RequestBody OutProberCardBean bean){
        proberCardOperator.outProberCard(bean);
    }
    @PostMapping("/ReleaseProberCard")
    public void ReleaseProberCard(@RequestBody ReleaseProberCardBean bean) {
            proberCardOperator.addnewReleaseProberCard(bean);
    }

    @PostMapping("/ProberCardMaintain")
    public void ProberCardMaintain(@RequestBody ProberCardMaintainBean bean) {
            proberCardOperator.addNewMaintainRecord(bean);
    }
    @PostMapping("/ProberCardEX")
    public void addProberCardEX(@RequestBody ProberCardExtensionBean bean) {
            proberCardOperator.addProberCardEX(bean);
    }
    @PostMapping("/ProbercardCheck")
    public void checkProberCard(@RequestBody ReleaseProberCardBean bean) {
            proberCardOperator.addnewReleaseProberCard(bean);
    }
    @PostMapping("/ProberCardState")
    public void updateProberCardState(String proberCardId, String newStatus, String oldStatus, String operator) {
        proberCardOperator.updateProberCardState(proberCardId,newStatus,oldStatus,operator);
    }

    @PostMapping("/ProberCard")
    public void updateProberCard(@RequestBody ProberCardEntityBean bean) {
            proberCardOperator.updateProberCard(bean);
    }
    @PostMapping("/ProberCardReleaseFlag")
    public void updateProberCardReleaseFlag(String proberCardId, boolean releaseFlag) {
            proberCardOperator.updateProberCardReleaseFlag(proberCardId, releaseFlag);
    }
    @PostMapping("/ProberCardInfoReleaseFlag")
    public void updateProberCardInfoReleaseFlag(String proberCardId, boolean releaseFlag) {
            proberCardOperator.updateProberCardInfoReleaseFlag(proberCardId, releaseFlag);
    }
    @PostMapping("/SingleState")
    public void updateSingleState(String proberCardId, String currentProcess) {
            proberCardOperator.updateSingleState(proberCardId, currentProcess);
    }
    @PostMapping("/ProberCardItem")
    public void updateProberCardItem(String proberCardId, Integer rebuildCount) {
            if(rebuildCount==null){
                rebuildCount=1;
            }else {
                rebuildCount++;
            }
            proberCardOperator.updateProberCardItem(proberCardId, rebuildCount);
    }
    @PostMapping("/MaintainItem")
    public void updateMaintainItem(String proberCardId, double afterPinlen, double afterPindiam, double afterPinlevel) {
            afterPinlen = 0;
            afterPindiam = 0;
            afterPinlevel = 0;
            proberCardOperator.updateMaintainItem(proberCardId, afterPinlen, afterPindiam, afterPinlevel);
    }
    @PostMapping("/IQCItem")
    public void updateIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
            pinMinlen = 0;
            pinMaxdiam = 0;
            pinLevel = 0;
            proberCardOperator.updateIQCItem(proberCardId, pinMinlen, pinMaxdiam, pinLevel);
    }
    @PostMapping("/PM")
    public boolean cleanPM(String cardid,String ownerid) {
        return proberCardOperator.cleanPM(cardid,ownerid);
    }
    @PostMapping("/ReTD")
    public boolean cleanTD(String cardid,String ownerid) {
        return proberCardOperator.cleanTD(cardid,ownerid);
    }
    @DeleteMapping("/ProberCards")
    public void deleteProberCards(@RequestParam("cardId") String cardId) {
        proberCardOperator.deleteProberCardInfo(cardId);
    }
    @PostMapping("RBIqcItem")
    public void updateRBIqcItem(String proberCardId,double pinMinlen,double pinMaxdiam,double pinLevel){
        pinMinlen = 0;
        pinMaxdiam = 0;
        pinLevel = 0;
        proberCardOperator.updateRBIqcItem(proberCardId, pinMinlen, pinMaxdiam, pinLevel);
    }

    @PostMapping(value = "/OutProberCardRecord", produces = {"application/json;charset=UTF-8"})
    public ArrayList<OutProberCardBean> getOutProberCard(@RequestParam("proberCardId") String proberCardId) {
        return proberCardOperator.getOutProberCard(proberCardId);
    }

    @PostMapping(value = "/BackProberCard", produces = {"application/json;charset=UTF-8"})
    public ArrayList<BackProberCardBean> getBackProberCard(@RequestParam("proberCardId") String proberCardId) {
        return proberCardOperator.getBackProberCard(proberCardId);
    }

    @PostMapping(value = "/InfoProberCard", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardEntityBean> getInfoProberCard(@RequestParam("proberCardId") String proberCardId) {
        return proberCardOperator.getInfoProberCard(proberCardId);
    }

    @PostMapping(value = "/IQCProberCard", produces = {"application/json;charset=UTF-8"})
    public ArrayList<IqcRecordBean> getIQCProberCard(@RequestParam("proberCardId") String proberCardId) {
        return proberCardOperator.getIQCProberCard(proberCardId);
    }

    @PostMapping(value = "/MaintainProberCard", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardMaintainBean> getMaintainProberCard(@RequestParam("proberCardId") String proberCardId) {
        return proberCardOperator.getMaintainProberCard(proberCardId);
    }

    @PostMapping(value = "/ReleaseProberCard", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ReleaseProberCardBean> getReleaseProberCard(@RequestParam("proberCardId") String proberCardId) {
        return proberCardOperator.getReleaseProberCard(proberCardId);
    }

    @PostMapping(value = "/ProberCardId", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardEntityBean> getProberCardId(@RequestParam("custName") String custName) {
        return proberCardOperator.getProberCardId(custName);
    }

    @PostMapping(value = "/EXRecord", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardExtensionBean> getEXRecord(@RequestParam("proberCardId") String proberCardId) {
        return proberCardOperator.getEXRecord(proberCardId);
    }
}
