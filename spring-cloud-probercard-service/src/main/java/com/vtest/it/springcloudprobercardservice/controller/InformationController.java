package com.vtest.it.springcloudprobercardservice.controller;

import com.vtest.it.springcloudprobercardservice.domain.*;
import com.vtest.it.springcloudprobercardservice.service.probercard.ProberCardInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/information")
@CrossOrigin
public class InformationController {
    @Autowired
    private ProberCardInformation proberCardInformation;

    @GetMapping(value = "/AllList",produces = {"application/json;charset=UTF-8"})
    public List<ProberCardEntityBean> getAllList(){
           return proberCardInformation.getAllList();
    }
    @GetMapping(value = "/SingleCard/{cardId}",produces = {"application/json;charset=UTF-8"})
    public ProberCardEntityBean getSingleCard(@PathVariable("cardId") String cardId){
        return proberCardInformation.getCard(cardId);
    }
    @GetMapping("/ProberCardStatus/{proberCardId}")
    public String getProberCardStatus(@PathVariable("proberCardId") String proberCardId){
        return proberCardInformation.getProberCardStatus(proberCardId);
    }
    @GetMapping(value = "/ReleaseCardInfo/{proberCardId}",produces = {"application/json;charset=UTF-8"})
    public ReleaseProberCardBean getReleaseCardInfo(@PathVariable("proberCardId") String proberCardId){
        return proberCardInformation.getReleaseCardInfo(proberCardId);
    }
    @GetMapping("/AllProberCardStatus")
    public ArrayList<ProberCardStatusBean> getAllProberCardStatus(){
        return proberCardInformation.getAllProberCardStatus();
    }
    @GetMapping("/ProberCardReleaseFlag/{proberCardId}")
    public boolean getProberCardReleaseFlag(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getProberCardReleaseFlag(proberCardId);
    }
    @GetMapping(value = "/AllIQCRecord",produces = {"application/json;charset=UTF-8"})
    public ArrayList<IqcRecordBean> getAllIQCRecord() {
        return proberCardInformation.getAllIQCRecord();
    }
    @GetMapping(value = "/AllMaintainRecord",produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardMaintainBean> getAllMaintainRecord() {
        return proberCardInformation.getAllMaintainRecord();
    }
    @GetMapping("/InfoRebuildCount/{proberCardId}")
    public Integer getInfoRebuildCount(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getInfoRebuildCount(proberCardId);
    }
    @GetMapping("/Td")
    public ArrayList<ProberCardTDBean> getTd() {
        return proberCardInformation.getTd();
    }

    @GetMapping(value = "/OutProberCard/{proberCardId}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<OutProberCardBean> getOutProberCard(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getOutProberCard(proberCardId);
    }

    @GetMapping(value = "/BackProberCard/{proberCardId}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<BackProberCardBean> getBackProberCard(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getBackProberCard(proberCardId);
    }

    @GetMapping(value = "/InfoProberCard/{proberCardId}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardEntityBean> getInfoProberCard(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getInfoProberCard(proberCardId);
    }

    @GetMapping(value = "/IQCProberCard/{proberCardId}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<IqcRecordBean> getIQCProberCard(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getIQCProberCard(proberCardId);
    }

    @GetMapping(value = "/MaintainProberCard/{proberCardId}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardMaintainBean> getMaintainProberCard(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getMaintainProberCard(proberCardId);
    }

    @GetMapping(value = "/ReleaseProberCard/{proberCardId}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ReleaseProberCardBean> getReleaseProberCard(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getReleaseProberCard(proberCardId);
    }

    @GetMapping(value = "/ProberCardId/{custName}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardEntityBean> getProberCardId(@PathVariable("custName") String custName) {
        return proberCardInformation.getProberCardId(custName);
    }

    @GetMapping(value = "/EXRecord/{proberCardId}", produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardExtensionBean> getEXRecord(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getEXRecord(proberCardId);
    }
    @GetMapping(value = "/ProberCardEX",produces = {"application/json;charset=UTF-8"})
    public ArrayList<String> getProberCardEX() {
        return proberCardInformation.getProberCardEX();
    }
    @GetMapping(value = "/EXInfoSingle/{proberCardId}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardExtensionBean> getEXInfoSingle(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getEXInfoSingle(proberCardId);
    }
    @GetMapping(value = "/AllIQCRecordByMinTime",produces = {"application/json;charset=UTF-8"})
    public ArrayList<IqcRecordBean> getAllIQCRecordByMinTime() {
        return proberCardInformation.getAllIQCRecordByMinTime();
    }
    @GetMapping(value = "/AllIQCRecordByMaxTime",produces = {"application/json;charset=UTF-8"})
    public ArrayList<IqcRecordBean> getAllIQCRecordByMaxTime() {
        return proberCardInformation.getAllIQCRecordByMaxTime();
    }
    @GetMapping(value = "/AllIQCRecordByRebuild",produces = {"application/json;charset=UTF-8"})
    public ArrayList<IqcRecordBean> getAllIQCRecordByRebuild() {
        return proberCardInformation.getAllIQCRecordByRebuild();
    }
    @GetMapping("/ExistFlag/{proberCardId}")
    public Integer getExistFlag(@PathVariable("proberCardId") String proberCardId){
        return  proberCardInformation.getExistFlag(proberCardId);
    }
    @GetMapping("/Spec/{proberCardId}")
    public ArrayList<ProberCardEntityBean>  getSpec(@PathVariable("proberCardId") String proberCardId){
        return  proberCardInformation.getSpec(proberCardId);
    }
    @GetMapping("/PinMinByMaxTime/{proberCardId}")
    public double  getPinMinByMaxTime(@PathVariable("proberCardId") String proberCardId){
        return  proberCardInformation.getPinMinByMaxTime(proberCardId);
    }
    @GetMapping("/AfterPinByMaxTime/{proberCardId}")
    public double getAfterPinByMaxTime(@PathVariable("proberCardId") String proberCardId){
        return  proberCardInformation.getAfterPinByMaxTime(proberCardId);
    }

    @GetMapping("/DepthSpec/{proberCardId}")
    public ArrayList<DepthSpecBean> getDepthSpec(@PathVariable("proberCardId") String proberCardId) {
        return proberCardInformation.getDepthSpec(proberCardId);
    }
}
