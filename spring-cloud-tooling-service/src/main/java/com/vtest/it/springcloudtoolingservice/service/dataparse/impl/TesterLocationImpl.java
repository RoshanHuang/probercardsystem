package com.vtest.it.springcloudtoolingservice.service.dataparse.impl;

import com.vtest.it.springcloudtoolingservice.dao.testerLocation.TesterLocationDao;
import com.vtest.it.springcloudtoolingservice.domain.testerLocationBean;
import com.vtest.it.springcloudtoolingservice.service.dataparse.TesterLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TesterLocationImpl implements TesterLocation {

    @Autowired
    private TesterLocationDao testerLocationDao;
    @Override
    public ArrayList<testerLocationBean> getTesterLocation() {
        return testerLocationDao.getTesterLocation();
    }

    @Override
    public String getPassBin(String waferId) {
        return testerLocationDao.getPassBin(waferId);
    }

    @Override
    public void sendWaferMail(String productId, String lotId, String waferNo, String testerId) {
        testerLocationDao.sendWaferMail(productId,lotId,waferNo,testerId);
    }
}
