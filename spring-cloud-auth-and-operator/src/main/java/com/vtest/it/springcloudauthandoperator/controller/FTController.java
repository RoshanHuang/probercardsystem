package com.vtest.it.springcloudauthandoperator.controller;

import com.vtest.it.springcloudauthandoperator.service.userService.impl.FTInfoService;
import com.vtest.it.springcloudauthandoperator.service.userService.impl.FTOperateService;
import common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
public class FTController {
    @Autowired
    private FTInfoService ftInfoService;
    @Autowired
    private FTOperateService ftOperateService;

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/Socket/{tfccNo}")
    public String getSocket(@PathVariable("tfccNo") String tfccNo) {
        return ftInfoService.getSocket(tfccNo);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/Loadboard/{tfccNo}")
    public String getLoadboard(@PathVariable("tfccNo") String tfccNo) {
        return ftInfoService.getLoadboard(tfccNo);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/Correlation/{tfccNo}")
    public String getCorrelation(@PathVariable("tfccNo") String tfccNo) {
        return ftInfoService.getCorrelation(tfccNo);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/ToolingState")
    public String getToolingState(String toolingId, String type) {
        return ftInfoService.getToolingState(toolingId, type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/LastEquipment")
    public String getLastEquipment(String toolingId, String type) {
        return ftInfoService.getLastEquipment(toolingId, type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/LastBackEquipment")
    public String getLastBackEquipment(String toolingId, String type) {
        return ftInfoService.getLastBackEquipment(toolingId, type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/SocketNo")
    public String getSocketNo() {
        return ftInfoService.getSocketNo();
    }

    @GetMapping("/LoadboardNo")
    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    public String getLoadboardNo() {
        return ftInfoService.getLoadboardNo();
    }

    @GetMapping("/CorrelationNo")
    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    public String getCorrelationNo() {
        return ftInfoService.getCorrelationNo();
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/ToolingTD/{type}")
    public String getToolingTD(@PathVariable("type") String type) {
        return ftInfoService.getToolingTD(type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/AllState/{type}")
    public String getAllState(@PathVariable("type") String type) {
        return ftInfoService.getAllState(type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/TesterByLendRecord/{type}")
    public String getTesterByLendRecord(@PathVariable("type") String type) {
        return ftInfoService.getTesterByLendRecord(type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/TesterByBackRecord/{type}")
    public String getTesterByBackRecord(@PathVariable("type") String type) {
        return ftInfoService.getTesterByBackRecord(type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/PMFTInfo/{eqtype}")
    public String getPMFTInfo(@PathVariable("eqtype") String eqtype) {
        return ftInfoService.getPMFTInfo(eqtype);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/PMFTInfoSingle/{eqid}")
    public Date getPMFTInfoSingle(@PathVariable("eqid") String eqid) {
        return ftInfoService.getPMFTInfoSingle(eqid);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @GetMapping("/AllPassTfccNo")
    public String getAllPassTfccNo() {
        return ftInfoService.getAllPassTfccNo();
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/LoadboardInfo")
    public void addLoadboardInfo(@RequestBody LoadboardBean bean) {
        ftOperateService.addLoadboardInfo(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/SocketInfo")
    public void addSocketInfo(@RequestBody SocketBean bean) {
        ftOperateService.addSocketInfo(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/CorrelationInfo")
    public void addCorrelationInfo(@RequestBody CorrelationBean bean) {
        ftOperateService.addCorrelationInfo(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/ToolingBackInfo")
    public void addToolingBackInfo(@RequestBody ToolingBackBean bean) {
        ftOperateService.addToolingBackInfo(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/ToolingOutInfo")
    public void addToolingOutInfo(@RequestBody ToolingOutBean bean) {
        ftOperateService.addToolingOutInfo(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/FTConfig")
    public void addFTConfig(@RequestBody FTConfigBean bean) {
        ftOperateService.addFTConfig(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/LoadboardLocation")
    public void updateLoadboardLocation(String tfccNo, String location) {
        ftOperateService.updateLoadboardLocation(tfccNo, location);
    }

    @PreAuthorize("hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/SocketLocation")
    public void updateSocketLocation(String tfccNo, String location) {
        ftOperateService.updateSocketLocation(tfccNo, location);
    }

    @PreAuthorize("hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/CorrelationLocation")
    public void updateCorrelationLocation(String tfccNo, String location) {
        ftOperateService.updateCorrelationLocation(tfccNo, location);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/ToolingLendRecord")
    public String getToolingLendRecord(String toolingId, String type) {
        return ftInfoService.getToolingLendRecord(toolingId, type);
    }

    @PreAuthorize("hasAuthority('ROLE_NORMAL-FT') or hasAuthority('ROLE_TOP-FT')")
    @PostMapping("/ToolingBackRecord")
    public String getToolingBackRecord(String toolingId, String type) {
        return ftInfoService.getToolingBackRecord(toolingId, type);
    }
}
