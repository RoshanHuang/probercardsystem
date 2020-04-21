package com.vtest.it.springcloudtoolingservice;

import com.vtest.it.springcloudtoolingservice.service.dataparse.VtMesConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private VtMesConfig vtMesConfig;
    @Test
    public void toolingTest(){
        System.err.println(vtMesConfig.currentStepCheck("R45EE4G"));
    }
}
