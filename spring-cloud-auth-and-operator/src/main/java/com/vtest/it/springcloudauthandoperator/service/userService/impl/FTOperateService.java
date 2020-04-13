package com.vtest.it.springcloudauthandoperator.service.userService.impl;


import common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class FTOperateService {
    @Autowired
    private RestTemplate restTemplate;

    public void addLoadboardInfo(LoadboardBean bean) {
        HttpEntity<LoadboardBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/LoadboardInfo", entity, Object.class);
    }

    public void addSocketInfo(SocketBean bean) {
        HttpEntity<SocketBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/SocketInfo", entity, Object.class);
    }

    public void addCorrelationInfo(CorrelationBean bean) {
        HttpEntity<CorrelationBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/CorrelationInfo", entity, Object.class);
    }

    public void addToolingBackInfo(ToolingBackBean bean) {
        HttpEntity<ToolingBackBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/ToolingBackInfo", entity, Object.class);
    }

    public void addToolingOutInfo(ToolingOutBean bean) {
        HttpEntity<ToolingOutBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/ToolingOutInfo", entity, Object.class);
    }

    public void addFTConfig(FTConfigBean bean) {
        HttpEntity<FTConfigBean> entity = new HttpEntity<>(bean);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/FTConfig", entity, Object.class);
    }

    public void updateLoadboardLocation(String tfccNo, String location) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("tfccNo", tfccNo);
        requestEntity.add("location", location);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/LoadboardLocation", requestEntity, String.class);
    }

    public void updateSocketLocation(String tfccNo, String location) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("tfccNo", tfccNo);
        requestEntity.add("location", location);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/SocketLocation", requestEntity, String.class);
    }

    public void updateCorrelationLocation(String tfccNo, String location) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("tfccNo", tfccNo);
        requestEntity.add("location", location);
        restTemplate.postForEntity("http://192.168.10.182:20300/FT-Service/FtOperate/CorrelationLocation", requestEntity, String.class);
    }
}
