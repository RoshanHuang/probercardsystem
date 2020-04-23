package com.vtest.it.springcloudauthandoperator.controller;

import com.vtest.it.springcloudauthandoperator.service.userService.impl.DataParseService;
import common.domain.VtestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class DataParseController {
    @Autowired
    private DataParseService dataParseService;

    @GetMapping("/passBin")
    public  String getPassBin(String waferId){
        return  dataParseService.getPassBin(waferId);
    }
    @GetMapping("/TesterLocation")
    public  String getTesterLocation(){
        return  dataParseService.getTesterLocation();
    }
    @GetMapping("/status")
    public String getStatus(){
       return  dataParseService.getStatus();
    }
    @GetMapping("/testerStatusList")
    public String getTesterStatusList(String tester){
        return  dataParseService.getTesterStatusList(tester);
    }
    @GetMapping("/testerStatusSingle")
    public String getTesterStatusSingle(String tester){
        return  dataParseService.getTesterStatusSingle(tester);
    }
    @GetMapping("/Mapping")
    public String getMap(String code, String device,String lot,String cp,String waferId){
        return  dataParseService.getMap(code, device, lot, cp, waferId);
    }
    @GetMapping("/dataSourceType")
    public Boolean checkDataTypeSource(String customer, String device){
        return  dataParseService.checkDataTypeSource(customer, device);
    }
    @GetMapping("/binSummary")
    public String getBinSummary(String customer,String device,String lot,String cp){
        return  dataParseService.getBinSummary(customer, device, lot, cp);
    }
    @GetMapping("/PrimaryOrRetestMap")
    public String getPrimaryOrRetestMap(String code, String device,String lot,String cp,String waferId){
        return  dataParseService.getPrimaryOrRetestMap(code, device, lot, cp, waferId);
    }
    @GetMapping("/superPosition")
    public String getSuperPosition(String customer,String device,String lot,String cp){
        return  dataParseService.getSuperPosition(customer, device, lot, cp);
    }
    @GetMapping("/customerAndDevices")
    public String getCustomerAndDevice(){
        return  dataParseService.getCustomerAndDevice();
    }
    @GetMapping("/lotAndCps")
    public String getLotAndCps(String customer,String device){
        return  dataParseService.getLotAndCps(customer, device);
    }
    @GetMapping("/waferIds")
    public String getWaferIDs(String customer,String device,String lot,String cp){
        return  dataParseService.getWaferIDs(customer, device, lot, cp);
    }
    @GetMapping("/qureyInfors")
    public String getqureyWaferInfors(VtestBean bean){
        return  dataParseService.getqureyWaferInfors(bean);
    }
    @GetMapping("/waferIdSummaryTotal")
    public String getWaferIdInforTotal(VtestBean bean){
        return  dataParseService.getWaferIdInforTotal(bean);
    }
    @GetMapping("/waferIdSummary")
    public String getWaferIdInforByType(VtestBean bean){
        return  dataParseService.getWaferIdInforByType(bean);
    }
    @GetMapping("/yields")
    public String getYields(VtestBean bean){
        return  dataParseService.getYields(bean);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN-PARSE')")
    @PostMapping("/Rawdata")
    public String modify(@RequestParam("modifyFile") MultipartFile modifyFile, String type) {
        return dataParseService.modify(modifyFile, type);
    }

    @PostMapping(value = "/RawdataNormal")
    public String modifyNormal(@RequestParam("modifyFile") MultipartFile modifyFile, String type) {
        return dataParseService.RawdataNormal(modifyFile, type);
    }
}
