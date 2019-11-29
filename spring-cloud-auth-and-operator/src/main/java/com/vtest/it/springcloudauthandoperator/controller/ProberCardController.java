package com.vtest.it.springcloudauthandoperator.controller;

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
public class ProberCardController {
    @Autowired
    private ProbercardInfoService probercardInfoService;

    @Autowired
    private OperatorService operatorService;


    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/AllList")
    public String getAllList() {
        return probercardInfoService.getAllList();
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/SingleCard")
    public String getSingleCard(String cardId) {
        return probercardInfoService.getSingleCard(cardId);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/ProberCardStatus")
    public String getProberCardStatus(String proberCardId) {
        return probercardInfoService.getProberCardStatus(proberCardId);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/ReleaseCardInfo")
    public String getReleaseCardInfo(String proberCardId) {
        return probercardInfoService.getReleaseCardInfo(proberCardId);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/AllProberCardStatus")
    public String getAllProberCardStatus() {
        return probercardInfoService.getAllProberCardStatus();
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/ProberCardReleaseFlag")
    public boolean getProberCardReleaseFlag(String proberCardId) {
        return probercardInfoService.getProberCardReleaseFlag(proberCardId);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/AllIQCRecord")
    public String getAllIQCRecord() {
        return probercardInfoService.getAllIQCRecord();
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/AllMaintainRecord")
    public String getAllMaintainRecord() {
        return probercardInfoService.getAllMaintainRecord();
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/InfoRebuildCount")
    public Integer getInfoRebuildCount(String proberCardId) {
        return probercardInfoService.getInfoRebuildCount(proberCardId);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/Td")
    public String getTd() {
        return probercardInfoService.getTd();
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/OutProberCard")
    public String getOutProberCard(String[] proberCardIdArrays) {
        return probercardInfoService.getOutProberCard(proberCardIdArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/BackProberCard")
    public String getBackProberCard(String[] proberCardIdArrays) {
        return probercardInfoService.getBackProberCard(proberCardIdArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/InfoProberCard")
    public String getInfoProberCard(String[] proberCardIdArrays) {
        return probercardInfoService.getInfoProberCard(proberCardIdArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/IQCProberCard")
    public String getIQCProberCard(String[] proberCardIdArrays) {
        return probercardInfoService.getIQCProberCard(proberCardIdArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/MaintainProberCard")
    public String getMaintainProberCard(String[] proberCardIdArrays) {
        return probercardInfoService.getMaintainProberCard(proberCardIdArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/ReleaseProberCard")
    public String getReleaseProberCard(String[] proberCardIdArrays) {
        return probercardInfoService.getReleaseProberCard(proberCardIdArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/ProberCardId")
    public String getProberCardId(String[] custNameArrays) {
        return probercardInfoService.getProberCardId(custNameArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/EXRecord")
    public String getEXRecord(String[] proberCardIdArrays) {
        return probercardInfoService.getEXRecord(proberCardIdArrays);
    }

    @PreAuthorize("hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/EXProberCard")
    public String getProberCardEX() {
        return probercardInfoService.getProberCardEX();
    }

    @PreAuthorize("hasAuthority('ROLE_PCRLeader-TOOLING')")
    @GetMapping(value = "/EXInfoSingle")
    public String getEXInfoSingle(String proberCardId) {
        return probercardInfoService.getEXInfoSingle(proberCardId);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/AllIQCRecordByMinTime")
    public String getAllIQCRecordByMinTime() {
        return probercardInfoService.getAllIQCRecordByMinTime();
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING') or hasAuthority('ROLE_NORMAL-TOOLING') ")
    @GetMapping(value = "/AllIQCRecordByMaxTime")
    public String getAllIQCRecordByMaxTime() {
        return probercardInfoService.getAllIQCRecordByMaxTime();
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/ProberCardInfo")
    public void addProberCardInfo(@RequestBody ProberCardEntityBean bean) {
        operatorService.addProberCardInfo(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/NewIqcRecord")
    public void addNewIqcRecord(@RequestBody IqcRecordBean bean) {
        operatorService.addNewIqcRecord(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/File")
    public void putFile(@RequestParam(value = "excelFile", required = false) MultipartFile file, String proberCardId) {
        String descPath = "/upload/" + proberCardId;
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

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/outProberCard")
    public void outProberCard(@RequestBody OutProberCardBean bean) {
        operatorService.outProberCard(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/backProberCard")
    public void backProberCard(@RequestBody BackProberCardBean bean) {
        operatorService.backProberCard(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING') or hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/ProberCardState")
    public void updateProberCardState(String proberCardId, String newStatus, String oldStatus, String operator) {
        operatorService.updateProberCardState(proberCardId, newStatus, oldStatus, operator);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/ProbercardCheck")
    public void checkProberCard(@RequestBody ReleaseProberCardBean bean) {
        operatorService.checkProberCard(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PTE-TOOLING')")
    @PostMapping(value = "/PTECheck")
    public void PTECheck(@RequestBody ReleaseProberCardBean bean) {
        operatorService.checkProberCard(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/ReleaseProberCard")
    public void ReleaseProberCard(@RequestBody ReleaseProberCardBean bean) {
        operatorService.ReleaseProberCard(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/ProberCardMaintain")
    public void ProberCardMaintain(@RequestBody ProberCardMaintainBean bean) {
        operatorService.ProberCardMaintain(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping(value = "/ProberCardEX")
    public void addProberCardEX(@RequestBody ProberCardExtensionBean bean) {
        operatorService.addProberCardEX(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/ProberCard")
    public void updateProberCard(@RequestBody ProberCardEntityBean bean) {
        operatorService.updateProberCard(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/ProberCardReleaseFlag")
    public void updateProberCardReleaseFlag(String proberCardId, boolean releaseFlag) {
        operatorService.updateProberCardReleaseFlag(proberCardId, releaseFlag);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/ProberCardInfoReleaseFlag")
    public void updateProberCardInfoReleaseFlag(String proberCardId, boolean releaseFlag) {
        operatorService.updateProberCardInfoReleaseFlag(proberCardId, releaseFlag);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/SingleState")
    public void updateSingleState(String proberCardId, String currentProcess) {
        operatorService.updateSingleState(proberCardId, currentProcess);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/ProberCardItem")
    public void clearProberCardItem(String proberCardId, Integer rebuildCount) {
        operatorService.clearProberCardItem(proberCardId, rebuildCount);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/MaintainItem")
    public void clearMaintainItem(String proberCardId, double afterPinlen, double afterPindiam, double afterPinlevel) {
        operatorService.clearMaintainItem(proberCardId, afterPinlen, afterPindiam, afterPinlevel);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/IQCItem")
    public void clearIQCItem(String proberCardId, double pinMinlen, double pinMaxdiam, double pinLevel) {
        operatorService.clearIQCItem(proberCardId, pinMinlen, pinMaxdiam, pinLevel);
    }

    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/PM")
    public void cleanPM(String cardid, String ownerid) {
        operatorService.cleanPM(cardid, ownerid);
    }
    @PreAuthorize("hasAuthority('ROLE_PCR-TOOLING') or hasAuthority('ROLE_PCRLeader-TOOLING')")
    @PostMapping("/ReTD")
    public void cleanTD(String cardid, String ownerid) {
        operatorService.cleanTD(cardid, ownerid);
    }
    @DeleteMapping("/ProberCards")
    public void ProberCards(String cardId) {
        operatorService.deleteProberCards(cardId);
    }
}
