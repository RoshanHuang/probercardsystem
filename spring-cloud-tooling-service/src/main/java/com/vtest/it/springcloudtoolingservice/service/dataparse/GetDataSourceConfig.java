package com.vtest.it.springcloudtoolingservice.service.dataparse;


import com.vtest.it.springcloudtoolingservice.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface GetDataSourceConfig {
    public DataSourceBean getConfig(@Param("platform") String platform);
    public ArrayList<DataInforToMesBean> getList();
    public ArrayList<CheckItemBean> getCheckItemList();
    public int dataErrorsRecord(@Param("list") ArrayList<DataParseIssueBean> list);
    public ArrayList<MarkAndSkipToPassBean> getMarkAndSkipToPassConfig();
    public void addNewModel(MarkToPassModelBean bean);
    public MarkToPassModelBean getModel(@Param("modelId") String modelId);
    public ArrayList<MarkToPassConfigBean> getMarkToPassConfigs();
    public int insertWaferInforToBinWaferSummary(BinWaferInforBean binWaferInforBean);
    public ArrayList<BinWaferInforBean> getTesterStatus();
    public BinWaferInforBean getTesterStatusSingle(@Param("tester") String tester);

    public List<WaferInforTesterListBean> getTesterStatusList(@Param("tester") String tester);

    public void waferFailTypeCheckOthers(@Param("waferId") String waferId, @Param("cp") String cpProcess, @Param("tester") String tester);

    public void updateTesterCheckResult(@Param("tester") String tester, @Param("waferId") String waferId);
}
