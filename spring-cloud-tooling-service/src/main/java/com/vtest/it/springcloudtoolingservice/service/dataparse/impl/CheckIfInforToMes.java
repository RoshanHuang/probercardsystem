package com.vtest.it.springcloudtoolingservice.service.dataparse.impl;


import com.vtest.it.springcloudtoolingservice.dao.config.GetDataSourceConfigDao;
import com.vtest.it.springcloudtoolingservice.domain.DataInforToMesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CheckIfInforToMes {

    @Autowired
    private GetDataSourceConfigDao getDataSourceConfigDao;

    public boolean check(String customCode, String device)
    {
        ArrayList<DataInforToMesBean> allConfigs= getDataSourceConfigDao.getList();
        for (DataInforToMesBean bean : allConfigs) {
            if (bean.getCustomCode().equals(customCode)&&(bean.getDevice().equals("ALL")||bean.getDevice().equals(device)))
            {
                return  true;
            }
        }
        return false;
    }
    public boolean check(String customCode, String device,ArrayList<DataInforToMesBean> allConfigs)
    {
        for (DataInforToMesBean bean : allConfigs) {
            if (bean.getCustomCode().equals(customCode)&&(bean.getDevice().equals("ALL")||bean.getDevice().equals(device)))
            {
                return  true;
            }
        }
        return false;
    }
}
