package com.vtest.it.springcloudprobercardservice.service.probercard.impl;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.vtest.it.springcloudprobercardservice.service.probercard.ProberCardInformation;
import com.vtest.it.springcloudprobercardservice.service.probercard.ProberCardOperator;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProberCardInformationImplTest {
    private static final Logger TESTLOGGER=Logger.getLogger(ProberCardInformationImplTest.class);
    @Autowired
    private ProberCardInformation proberCardInformation;
    @Autowired
    private ProberCardOperator proberCardOperator;
    @Autowired
    private ProberCardInformationImpl proberCardInformationImpl;

    @Test
   public void getEXInfoSingle() {
//      ArrayList<ProberCardExtensionBean> bean= proberCardInformation.getEXInfoSingle("S-1");
//        for (ProberCardExtensionBean  infor: bean) {
//               System.out.println(infor.getCardType());
//        }
//        ProberCardEntityBean bean=proberCardInformation.getCard("S-1");
//        for (ProberCardEntityBean info: bean.) {
//
//        }
//        boolean flag=proberCardOperator.updateProberCardInfoReleaseFlag("S-1",false);
//        TESTLOGGER.warn(flag);
//        proberCardOperator.cleanTD("S-1","V236");
        String[] array={"T20-PC-01"};
        System.err.println(JSON.toJSONString(proberCardInformationImpl.getMaintainProberCard(array)));
    }
}