package com.vtest.it.springcloudprobercardservice.service.probercard.impl;

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
        proberCardOperator.cleanTD("S-1","V236");
    }
}