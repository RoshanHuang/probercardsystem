package com.vtest.it.springcloudtoolingservice.service.dataparse.impl;

import com.vtest.it.springcloudtoolingservice.dao.testerLocation.VtMesConfigDao;
import com.vtest.it.springcloudtoolingservice.domain.MesConfigBean;
import com.vtest.it.springcloudtoolingservice.service.dataparse.VtMesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class VtMesConfigImpl implements VtMesConfig {
    @Autowired
    private VtMesConfigDao vtMesConfigDao;

    @Override
    public MesConfigBean getBean(String waferId, String cpProcess) {
        return vtMesConfigDao.getBean(waferId, cpProcess);
    }

    @Override
    public String getBinDescription(String waferId, String cpProcess) {
        return vtMesConfigDao.getBinDescription(waferId, cpProcess);
    }

    @Override
    public String currentStepCheck(String waferId) {
        return vtMesConfigDao.currentStepCheck(waferId);
    }

    @Override
    public String getLastStep(String waferId) {
        return vtMesConfigDao.getLastStep(waferId);
    }

    public boolean check(File file) {
        String[] Tokens = file.getName().split("_");
        int length = Tokens.length;
        StringBuffer Devicebuffer = new StringBuffer(Tokens[1]);
        for (int j = 2; j < Tokens.length - 4; j++) {
            Devicebuffer.append("_" + Tokens[j]);
        }
        String waferId = Tokens[length - 3].trim();
        String cpStep = Tokens[length - 2].substring(0, 3).trim();
        boolean flag = false;
        String content = vtMesConfigDao.currentStepCheck(waferId);
        if (content.contains("CP") && content.equals(cpStep)) {
            flag = true;
        }
        if (content.equals("WV1")) {
            String lastStep = vtMesConfigDao.getLastStep(waferId);
            if (lastStep.equals(cpStep)) {
                flag = true;
            }
        }
        return flag;
    }
}
