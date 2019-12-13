package com.vtest.it.springcloudtoolingservice.service.dataparse.impl;

import com.vtest.it.springcloudtoolingservice.dao.config.GetDataSourceConfigDao;
import com.vtest.it.springcloudtoolingservice.domain.*;
import com.vtest.it.springcloudtoolingservice.service.dataparse.GetDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetDataSourceConfigImpl implements GetDataSourceConfig {
    @Autowired
    private GetDataSourceConfigDao getDataSourceConfigDao;
    @Override
    public DataSourceBean getConfig(String platform) {
        return getDataSourceConfigDao.getConfig(platform);
    }

    @Override
    public ArrayList<DataInforToMesBean> getList() {
        return getDataSourceConfigDao.getList();
    }

    @Override
    public ArrayList<CheckItemBean> getCheckItemList() {
        return getDataSourceConfigDao.getCheckItemList();
    }

    @Override
    public int dataErrorsRecord(ArrayList<DataParseIssueBean> list) {
        return getDataSourceConfigDao.dataErrorsRecord(list);
    }

    @Override
    public ArrayList<MarkAndSkipToPassBean> getMarkAndSkipToPassConfig() {
        return getDataSourceConfigDao.getMarkAndSkipToPassConfig();
    }

    @Override
    public void addNewModel(MarkToPassModelBean bean) {
         getDataSourceConfigDao.addNewModel(bean);
    }

    @Override
    public MarkToPassModelBean getModel(String modelId) {
        return getDataSourceConfigDao.getModel(modelId);
    }

    @Override
    public ArrayList<MarkToPassConfigBean> getMarkToPassConfigs() {
        return getDataSourceConfigDao.getMarkToPassConfigs();
    }

    @Override
    public int insertWaferInforToBinWaferSummary(BinWaferInforBean binWaferInforBean) {
        return getDataSourceConfigDao.insertWaferInforToBinWaferSummary(binWaferInforBean);
    }

    @Override
    public ArrayList<BinWaferInforBean> getTesterStatus() {
        return getDataSourceConfigDao.getTesterStatus();
    }

    @Override
    public BinWaferInforBean getTesterStatusSingle(String tester) {
        return getDataSourceConfigDao.getTesterStatusSingle(tester);
    }

    @Override
    public List<WaferInforTesterListBean> getTesterStatusList(String tester) {
        return getDataSourceConfigDao.getTesterStatusList(tester);
    }

    @Override
    public void waferFailTypeCheckOthers(String waferId, String cpProcess, String tester) {
        getDataSourceConfigDao.waferFailTypeCheckOthers(waferId,cpProcess,tester);
    }

    @Override
    public void updateTesterCheckResult(String tester, String waferId) {
       getDataSourceConfigDao.updateTesterCheckResult(tester,waferId);
    }
}
