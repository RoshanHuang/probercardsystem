package com.vtest.it.springcloudauthandoperator.service.userService.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class FTInfoService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProbercardInfoService.class);

    public String getSocket(String tfccNo) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/Socket/{tfccNo}", String.class, tfccNo);
        return responseEntity.getBody();
    }

    public String getLoadboard(String tfccNo) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/Loadboard/{tfccNo}", String.class, tfccNo);
        return responseEntity.getBody();
    }

    public String getCorrelation(String tfccNo) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/Correlation/{tfccNo}", String.class, tfccNo);
        return responseEntity.getBody();
    }

    public String getToolingState(String toolingId, String type) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("toolingId", toolingId);
        requestEntity.add("type", type);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/ToolingState", String.class, requestEntity);
        return responseEntity.getBody();
    }

    public String getLastEquipment(String toolingId, String type) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("toolingId", toolingId);
        requestEntity.add("type", type);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/LastEquipment", String.class, requestEntity);
        return responseEntity.getBody();
    }

    public String getLastBackEquipment(String toolingId, String type) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("toolingId", toolingId);
        requestEntity.add("type", type);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/LastBackEquipment", String.class, requestEntity);
        return responseEntity.getBody();
    }

    public String getSocketNo() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/SocketNo", String.class);
        return responseEntity.getBody();
    }

    public String getLoadboardNo() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/LoadboardNo", String.class);
        return responseEntity.getBody();
    }

    public String getCorrelationNo() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/CorrelationNo", String.class);
        return responseEntity.getBody();
    }

    public String getToolingTD(String type) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/ToolingTD/{type}", String.class, type);
        return responseEntity.getBody();
    }

    public String getTesterByLendRecord(String type) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/TesterByLendRecord/{type}", String.class, type);
        return responseEntity.getBody();
    }

    public String getAllState(String type) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/AllState/{type}", String.class, type);
        return responseEntity.getBody();
    }

    public String getTesterByBackRecord(String type) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/TesterByBackRecord/{type}", String.class, type);
        return responseEntity.getBody();
    }

    public String getPMFTInfo(String eqtype) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/PMFTInfo/{eqtype}", String.class, eqtype);
        return responseEntity.getBody();
    }

    public Date getPMFTInfoSingle(String eqid) {
        ResponseEntity<Date> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/PMFTInfoSingle/{eqid}", Date.class, eqid);
        return responseEntity.getBody();
    }

    public String getAllPassTfccNo() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/FT-Service/FtInformation/AllPassTfccNo", String.class);
        return responseEntity.getBody();
    }

    public String getToolingLendRecord(String toolingId, String type) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("toolingId", toolingId);
        requestEntity.add("type", type);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/ToolingLendRecord", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getToolingBackRecord(String toolingId, String type) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("toolingId", toolingId);
        requestEntity.add("type", type);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/ToolingBackRecord", requestEntity, String.class);
        return responseEntity.getBody();
    }
}
