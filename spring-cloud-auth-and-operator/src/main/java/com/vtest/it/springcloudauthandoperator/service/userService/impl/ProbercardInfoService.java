package com.vtest.it.springcloudauthandoperator.service.userService.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ProbercardInfoService {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProbercardInfoService.class);

    public String getAllList() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AllList", String.class);

        return responseEntity.getBody();
    }
    public String getSingleCard(String cardId){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/SingleCard/{cardId}", String.class,cardId);
        return responseEntity.getBody();
    }
    public String getProberCardStatus(String proberCardId){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/ProberCardStatus/{proberCardId}", String.class,proberCardId);
        return responseEntity.getBody();
    }
    public String getReleaseCardInfo(String proberCardId){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/ReleaseCardInfo/{proberCardId}", String.class,proberCardId);
        return responseEntity.getBody();
    }
    public String getAllProberCardStatus(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AllProberCardStatus", String.class);
        logger.error(responseEntity.getBody());
        return responseEntity.getBody();
    }
    public boolean getProberCardReleaseFlag(String proberCardId){
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/ProberCardReleaseFlag/{proberCardId}", Boolean.class,proberCardId);
        return responseEntity.getBody();
    }
    public String getAllIQCRecord(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AllIQCRecord", String.class);
        return responseEntity.getBody();
    }
    public String getAllMaintainRecord(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AllMaintainRecord", String.class);
        return responseEntity.getBody();
    }
    public Integer getInfoRebuildCount(String proberCardId){
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/InfoRebuildCount/{proberCardId}", Integer.class,proberCardId);
        return responseEntity.getBody();
    }
    public String getTd(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/Td", String.class);
        return responseEntity.getBody();
    }

    public String getOutProberCard(String proberCardId) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId", proberCardId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/OutProberCardRecord", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getBackProberCard(String proberCardId) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId", proberCardId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/BackProberCard", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getInfoProberCard(String proberCardId) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId", proberCardId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/InfoProberCard", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getIQCProberCard(String proberCardId) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId", proberCardId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/IQCProberCard", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getMaintainProberCard(String proberCardId) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId", proberCardId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/MaintainProberCard", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getReleaseProberCard(String proberCardId) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId", proberCardId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ReleaseProberCard", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getProberCardId(String custName) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("custName", custName);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/ProberCardId", requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getEXRecord(String proberCardId) {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("proberCardId", proberCardId);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.10.182:20300/prober-card-service/operator/EXRecord", requestEntity, String.class);
        return responseEntity.getBody();
    }
    public String getProberCardEX(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/ProberCardEX", String.class);
        return responseEntity.getBody();
    }
    public String getEXInfoSingle(String proberCardId){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/EXInfoSingle/{proberCardId}", String.class,proberCardId);
        return responseEntity.getBody();
    }
    public String getAllIQCRecordByMinTime(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AllIQCRecordByMinTime", String.class);
        return responseEntity.getBody();
    }
    public String getAllIQCRecordByMaxTime(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AllIQCRecordByMaxTime", String.class);
        return responseEntity.getBody();
    }
    public String getAllIQCRecordByRebuild(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AllIQCRecordByRebuild", String.class);
        return responseEntity.getBody();
    }
    public String getSpec(String proberCardId){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/Spec/{proberCardId}", String.class,proberCardId);
        return responseEntity.getBody();
    }
    public double getPinMinByMaxTime(String proberCardId){
        ResponseEntity<Double> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/PinMinByMaxTime/{proberCardId}", Double.class,proberCardId);
        return responseEntity.getBody();
    }
    public double getAfterPinByMaxTime(String proberCardId){
        ResponseEntity<Double> responseEntity = restTemplate.getForEntity("http://192.168.10.182:20300/prober-card-service/information/AfterPinByMaxTime/{proberCardId}", Double.class,proberCardId);
        return responseEntity.getBody();
    }
}
