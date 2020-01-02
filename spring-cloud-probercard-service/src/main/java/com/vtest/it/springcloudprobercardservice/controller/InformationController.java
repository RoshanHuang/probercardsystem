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
    @GetMapping(value = "/OutProberCard/{proberCardIdArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<OutProberCardBean> getOutProberCard(@PathVariable("proberCardIdArrays") String[] proberCardIdArrays) {
        return proberCardInformation.getOutProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/BackProberCard/{proberCardIdArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<BackProberCardBean> getBackProberCard(@PathVariable("proberCardIdArrays") String[] proberCardIdArrays) {
        return proberCardInformation.getBackProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/InfoProberCard/{proberCardIdArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardEntityBean> getInfoProberCard(@PathVariable("proberCardIdArrays") String[] proberCardIdArrays) {
        return proberCardInformation.getInfoProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/IQCProberCard/{proberCardIdArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<IqcRecordBean> getIQCProberCard(@PathVariable("proberCardIdArrays") String[] proberCardIdArrays) {
        return proberCardInformation.getIQCProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/MaintainProberCard/{proberCardIdArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardMaintainBean> getMaintainProberCard(@PathVariable("proberCardIdArrays") String[] proberCardIdArrays) {
        return proberCardInformation.getMaintainProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/ReleaseProberCard/{proberCardIdArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<ReleaseProberCardBean> getReleaseProberCard(@PathVariable("proberCardIdArrays") String[] proberCardIdArrays) {
        return proberCardInformation.getReleaseProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/ProberCardId/{custNameArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardEntityBean> getProberCardId(@PathVariable("custNameArrays") String[] custNameArrays) {
        return proberCardInformation.getProberCardId(custNameArrays);
    }
    @GetMapping(value = "/EXRecord/{proberCardIdArrays}",produces = {"application/json;charset=UTF-8"})
    public ArrayList<ProberCardExtensionBean> getEXRecord(@PathVariable("proberCardIdArrays") String[] proberCardIdArrays) {
        return proberCardInformation.getEXRecord(proberCardIdArrays);
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
}
