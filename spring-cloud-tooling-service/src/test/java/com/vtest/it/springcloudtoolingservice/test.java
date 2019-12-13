package com.vtest.it.springcloudtoolingservice;

import com.alibaba.fastjson.JSON;
import com.vtest.it.springcloudtoolingservice.domain.GetWaferInforBean;
import com.vtest.it.springcloudtoolingservice.domain.LotAndCpsBean;
import com.vtest.it.springcloudtoolingservice.domain.waferIdInforBean;
import com.vtest.it.springcloudtoolingservice.service.dataparse.ProberData;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private ProberData proberData;
    @Test
    public void toolingTest(){
        for (waferIdInforBean bean: proberData.getOthersParam("AVW","AW221CX","CLC86C.1","CP1","CLC86C-01")) {
            System.err.println(JSON.parseObject(bean.getParams()).get("RightID"));
        }


    }
}
