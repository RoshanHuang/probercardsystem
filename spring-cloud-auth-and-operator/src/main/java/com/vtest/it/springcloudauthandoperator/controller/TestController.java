package com.vtest.it.springcloudauthandoperator.controller;

import com.alibaba.fastjson.JSON;
import com.vtest.it.springcloudauthandoperator.service.userService.impl.OperatorService;
import com.vtest.it.springcloudauthandoperator.service.userService.impl.ProbercardInfoService;
import common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@PreAuthorize("hasAuthority('ROLE_ADMIN-TOOLING') or hasAuthority('ROLE_TOP-TOOLING')")
public class TestController {
    // 测试普通权限
    @Autowired
    private ProbercardInfoService probercardInfoService;

    @Autowired
    private OperatorService operatorService;

    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping(value = "/normal/test", method = RequestMethod.GET)
    public String test1() {
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    // 测试管理员权限
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/admin/test", method = RequestMethod.GET)
    public String test2() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }

    @RequestMapping(value = "/xixi/test", method = RequestMethod.GET)
    public String test3() {
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    @GetMapping(value = "/AllList")
    public String getAllList(){
        return probercardInfoService.getAllList();
    }

    @GetMapping(value = "/SingleCard")
    public String getSingleCard(String cardId){
        return probercardInfoService.getSingleCard(cardId);
    }
    @GetMapping(value = "/ProberCardStatus")
    public String getProberCardStatus(String proberCardId){
        return probercardInfoService.getProberCardStatus(proberCardId);
    }
    @GetMapping(value = "/ReleaseCardInfo")
    public String getReleaseCardInfo(String proberCardId){
        return probercardInfoService.getReleaseCardInfo(proberCardId);
    }
    @GetMapping(value = "/AllProberCardStatus")
    public String getAllProberCardStatus(){
        return probercardInfoService.getAllProberCardStatus();
    }
    @GetMapping(value = "/ProberCardReleaseFlag")
    public boolean getProberCardReleaseFlag(String proberCardId){
        return probercardInfoService.getProberCardReleaseFlag(proberCardId);
    }
    @GetMapping(value = "/AllIQCRecord")
    public String getAllIQCRecord(){
        return probercardInfoService.getAllIQCRecord();
    }

    @GetMapping(value = "/AllMaintainRecord")
    public String getAllMaintainRecord(){
        return probercardInfoService.getAllMaintainRecord();
    }
    @GetMapping(value = "/InfoRebuildCount")
    public Integer getInfoRebuildCount(String proberCardId){
        return probercardInfoService.getInfoRebuildCount(proberCardId);
    }
    @GetMapping(value = "/Td")
    public String getTd(){
        return probercardInfoService.getTd();
    }
    @GetMapping(value = "/OutProberCard")
    public String getOutProberCard(String[] proberCardIdArrays){
        return probercardInfoService.getOutProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/BackProberCard")
    public String getBackProberCard(String[] proberCardIdArrays){
        return probercardInfoService.getBackProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/InfoProberCard")
    public String getInfoProberCard(String[] proberCardIdArrays){
        return probercardInfoService.getInfoProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/IQCProberCard")
    public String getIQCProberCard(String[] proberCardIdArrays){
        return probercardInfoService.getIQCProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/MaintainProberCard")
    public String getMaintainProberCard(String[] proberCardIdArrays){
        return probercardInfoService.getMaintainProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/ReleaseProberCard")
    public String getReleaseProberCard(String[] proberCardIdArrays){
        return probercardInfoService.getReleaseProberCard(proberCardIdArrays);
    }
    @GetMapping(value = "/ProberCardId")
    public String getProberCardId(String[] custNameArrays){
        return probercardInfoService.getProberCardId(custNameArrays);
    }
    @GetMapping(value = "/EXRecord")
    public String getEXRecord(String[] proberCardIdArrays){
        return probercardInfoService.getEXRecord(proberCardIdArrays);
    }
    @GetMapping(value = "/ProberCardEX")
    public String getProberCardEX(){
        return probercardInfoService.getProberCardEX();
    }
    @GetMapping(value = "/EXInfoSingle")
    public String getEXInfoSingle(String proberCardId){
        return probercardInfoService.getEXInfoSingle(proberCardId);
    }
    @GetMapping(value = "/AllIQCRecordByMinTime")
    public String getAllIQCRecordByMinTime(){
        return probercardInfoService.getAllIQCRecordByMinTime();
    }
    @GetMapping(value = "/AllIQCRecordByMaxTime")
    public String getAllIQCRecordByMaxTime(){
        return probercardInfoService.getAllIQCRecordByMaxTime();
    }

    @PostMapping(value = "/ProberCardInfo")
    public void addProberCardInfo(@RequestBody ProberCardEntityBean bean){
          operatorService.addProberCardInfo(bean);
    }
    @PostMapping(value = "/NewIqcRecord")
    public void addNewIqcRecord(@RequestBody IqcRecordBean bean){
        operatorService.addNewIqcRecord(bean);
    }

    @PreAuthorize("!hasAuthority('ROLE_NORMAL-TOOLING')")
    @PostMapping(value = "/File")
    public void putFile(@RequestParam(value = "excelFile", required = false) MultipartFile file, String proberCardId) {
        String descPath = "D:/upload/" +proberCardId;
        File descFile = new File(descPath);
        if (!descFile.exists()) {
            descFile.mkdir();
        }
        if (file != null) {
            File newFile = new File(descFile, "/" + file.getOriginalFilename());
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "/outProberCard")
    public void outProberCard(@RequestBody OutProberCardBean bean){
        operatorService.outProberCard(bean);
    }
    @PostMapping(value = "/backProberCard")
    public void backProberCard(@RequestBody BackProberCardBean bean){
        operatorService.backProberCard(bean);
    }
    @PostMapping(value = "/ProberCardState")
    public void updateProberCardState(String proberCardId, String newStatus, String oldStatus, String operator){
           operatorService.updateProberCardState(proberCardId,newStatus,oldStatus,operator);
    }
    @PostMapping(value = "/ProbercardCheck")
    public void checkProberCard(@RequestBody ReleaseProberCardBean bean){
        operatorService.checkProberCard(bean);
    }
    @PostMapping(value = "/ReleaseProberCard")
    public void ReleaseProberCard(@RequestBody ReleaseProberCardBean bean){
        operatorService.ReleaseProberCard(bean);
    }
    @PostMapping(value = "/ProberCardMaintain")
    public void ProberCardMaintain(@RequestBody ProberCardMaintainBean bean){
        operatorService.ProberCardMaintain(bean);
    }
    @PostMapping(value = "/ProberCardEX")
    public void addProberCardEX(@RequestBody ProberCardExtensionBean bean){
        operatorService.addProberCardEX(bean);
    }

    @PreAuthorize("!hasAuthority('ROLE_NORMAL-TOOLING') or !hasAuthority('ROLE_PTE-TOOLING')")
    @PostMapping("/ProberCard")
    public void updateProberCard(@RequestBody ProberCardEntityBean bean) {
        operatorService.updateProberCard(bean);
    }
    @PostMapping("/ProberCardReleaseFlag")
    public void updateProberCardReleaseFlag(String proberCardId, boolean releaseFlag)  {
        operatorService.updateProberCardReleaseFlag(proberCardId,releaseFlag);
    }

    @PreAuthorize("!hasAuthority('ROLE_NORMAL-TOOLING') or !hasAuthority('ROLE_PTE-TOOLING')")
    @PostMapping("/ProberCardInfoReleaseFlag")
    public void updateProberCardInfoReleaseFlag(String proberCardId, boolean releaseFlag) {
        operatorService.updateProberCardInfoReleaseFlag(proberCardId,releaseFlag);
    }

    @PreAuthorize("!hasAuthority('ROLE_NORMAL-TOOLING') or !hasAuthority('ROLE_PTE-TOOLING')")
    @PostMapping("/SingleState")
    public void updateSingleState(String proberCardId, String currentProcess){
        operatorService.updateSingleState(proberCardId,currentProcess);
    }
    @PostMapping("/ProberCardItem")
    public void clearProberCardItem(String proberCardId, String pinlenSpec, String pindiamSpec, String pinlevelSpec, Integer rebuildCount) {
        operatorService.clearProberCardItem(proberCardId,pinlenSpec,pindiamSpec,pinlevelSpec,rebuildCount);
    }
    @PostMapping("/MaintainItem")
    public void clearMaintainItem(String proberCardId, double afterPinlen, double afterPindiam, double afterPinlevel) {
        operatorService.clearMaintainItem(proberCardId, afterPinlen, afterPindiam, afterPinlevel);
    }
    @PostMapping("/IQCItem")
    public void clearIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
        operatorService.clearIQCItem(proberCardId, pinMinlen, pinMaxdiam, pinLevel);
    }
    @PostMapping("/PM")
    public void  cleanPM(String cardid,String ownerid) {
        operatorService.cleanPM(cardid, ownerid);
    }
    @DeleteMapping("/ProberCards")
    public void ProberCards(String cardId) {
        operatorService.deleteProberCards(cardId);
    }
}
