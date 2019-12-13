package com.vtest.it.springcloudtoolingservice.dao.config;


import com.vtest.it.springcloudtoolingservice.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface GetDataSourceConfigDao {
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
