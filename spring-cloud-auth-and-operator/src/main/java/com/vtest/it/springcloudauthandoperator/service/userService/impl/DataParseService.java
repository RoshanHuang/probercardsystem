package com.vtest.it.springcloudauthandoperator.service.userService.impl;

import common.domain.VtestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DataParseService {
    @Autowired
    private RestTemplate restTemplate;

    public String getPassBin(String waferId){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/passBin/{waferId}",String.class,waferId);
        return  responseEntity.getBody();
    }
    public String getTesterLocation(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/TesterLocation",String.class);
        return  responseEntity.getBody();
    }
    public String getStatus()
    {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/status",String.class);
        return  responseEntity.getBody();
    }
    public String getTesterStatusList(String tester) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/testerStatusList/{tester}",String.class,tester);
        return  responseEntity.getBody();
    }
    public String getTesterStatusSingle(String tester){
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/testerStatusSingle/{tester}",String.class,tester);
        return responseEntity.getBody();
    }
    public String getMap(String code, String device,String lot,String cp,String waferId){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("code",code);
        map.put("device",device);
        map.put("lot",lot);
        map.put("cp",cp);
        map.put("waferId",waferId);
        return restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/Mapping/{code}/{device}/{lot}/{cp}/{waferId}",String.class,map).getBody();
    }
    public String getPrimaryOrRetestMap(String code, String device,String lot,String cp,String waferId){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("code",code);
        map.put("device",device);
        map.put("lot",lot);
        map.put("cp",cp);
        map.put("waferId",waferId);
        return restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/PrimaryOrRetestMap/{code}/{device}/{lot}/{cp}/{waferId}",String.class,map).getBody();
    }
    public boolean checkDataTypeSource(String customer,String device){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("customer",customer);
        map.put("device",device);
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/dataSourceType/{customer}/{device}",Boolean.class,map);
        return responseEntity.getBody();
    }
    public String getBinSummary(String customer,String device,String lot,String cp){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("customer",customer);
        map.put("device",device);
        map.put("lot",lot);
        map.put("cp",cp);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/binSummary/{customer}/{device}/{lot}/{cp}",String.class,map);
        return responseEntity.getBody();
    }
    public String getSuperPosition(String customer,String device,String lot,String cp){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("customer",customer);
        map.put("device",device);
        map.put("lot",lot);
        map.put("cp",cp);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/superPosition/{customer}/{device}/{lot}/{cp}",String.class,map);
        return responseEntity.getBody();
    }
    public String getCustomerAndDevice(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/customerAndDevices",String.class);
        return  responseEntity.getBody();
    }
    public String getLotAndCps(String customer,String device){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("customer",customer);
        map.put("device",device);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/lotAndCps/{customer}/{device}",String.class,map);
        return responseEntity.getBody();
    }
    public String getWaferIDs(String customer,String device,String lot,String cp){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("customer",customer);
        map.put("device",device);
        map.put("lot",lot);
        map.put("cp",cp);
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://192.168.10.182:20300/tooling-service/information/waferIds/{customer}/{device}/{lot}/{cp}",String.class,map);
        return responseEntity.getBody();
    }
    public String getqureyWaferInfors(VtestBean bean){
        HttpEntity<VtestBean> entity=new HttpEntity<>(bean);
        ResponseEntity<String> responseEntity=restTemplate.postForEntity("http://192.168.10.182:20300/tooling-service/information/qureyInfors",entity,String.class);
        return responseEntity.getBody();

    }
    public String getWaferIdInforTotal(VtestBean bean){
        HttpEntity<VtestBean> entity=new HttpEntity<>(bean);
        ResponseEntity<String> responseEntity=restTemplate.postForEntity("http://192.168.10.182:20300/tooling-service/information/waferIdSummaryTotal",entity,String.class);
        return responseEntity.getBody();
    }
    public String getWaferIdInforByType(VtestBean bean){
        HttpEntity<VtestBean> entity=new HttpEntity<>(bean);
        ResponseEntity<String> responseEntity=restTemplate.postForEntity("http://192.168.10.182:20300/tooling-service/information/waferIdSummary",entity,String.class);
        return responseEntity.getBody();
    }
    public String getYields(VtestBean bean){
        HttpEntity<VtestBean> entity=new HttpEntity<>(bean);
        ResponseEntity<String> responseEntity=restTemplate.postForEntity("http://192.168.10.182:20300/tooling-service/information/yields",entity,String.class);
        return responseEntity.getBody();
    }

    public String modify(MultipartFile modifyFile, String type) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("modifyFile", new FileSystemResource(convert(modifyFile)));
        requestEntity.add("type", type);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/tooling-service/Modify/Rawdata", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    public String RawdataNormal(MultipartFile modifyFile, String type) {
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("modifyFile", new FileSystemResource(convert(modifyFile)));
        requestEntity.add("type", type);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/tooling-service/Modify/RawdataNormal", requestEntity, String.class);
        return responseEntity.getBody();
    }
}
