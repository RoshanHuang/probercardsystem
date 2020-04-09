package com.vtest.it.springcloudprobercardservice.dao;

import com.vtest.it.springcloudprobercardservice.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface ProberCardInformationDao {

    public List<ProberCardEntityBean> getAllList();

    public ProberCardEntityBean getCard(@Param("cardId") String cardId);

    public String getProberCardStatus(@Param("proberCardId") String proberCardId);

    public ReleaseProberCardBean getReleaseCardInfo(@Param("proberCardId") String proberCardId);

    public ArrayList<ProberCardStatusBean> getAllProberCardStatus();

    public boolean getProberCardReleaseFlag(@Param("proberCardId") String proberCardId);

    public ArrayList<IqcRecordBean> getAllIQCRecord();

    public ArrayList<ProberCardMaintainBean> getAllMaintainRecord();

    public Integer getInfoRebuildCount(@Param("proberCardId") String proberCardId);

    public ArrayList<ProberCardTDBean> getTd();

    public ArrayList<OutProberCardBean> getOutProberCard(@Param("proberCardId") String proberCardId);

    public ArrayList<BackProberCardBean> getBackProberCard(@Param("proberCardId") String proberCardId);

    public ArrayList<ProberCardEntityBean> getInfoProberCard(@Param("proberCardId") String proberCardId);

    public ArrayList<IqcRecordBean> getIQCProberCard(@Param("proberCardId") String proberCardId);

    public ArrayList<ProberCardMaintainBean> getMaintainProberCard(@Param("proberCardId") String proberCardId);

    public ArrayList<ReleaseProberCardBean> getReleaseProberCard(@Param("proberCardId") String proberCardId);

    public ArrayList<ProberCardEntityBean> getProberCardId(@Param("custName") String custName);

    public ArrayList<ProberCardExtensionBean> getEXRecord(@Param("proberCardId") String proberCardId);

    public ArrayList<String> getProberCardEX();

    public ArrayList<ProberCardExtensionBean> getEXInfoSingle(@Param("proberCardId") String proberCardId);

    public ArrayList<IqcRecordBean> getAllIQCRecordByMinTime();

    public ArrayList<IqcRecordBean> getAllIQCRecordByMaxTime();

    public ArrayList<IqcRecordBean> getAllIQCRecordByRebuild();

    public Integer getExistFlag(@Param("proberCardId") String proberCardId);

    public ArrayList<ProberCardEntityBean> getSpec(@Param("proberCardId") String proberCardId);

    public double getPinMinByMaxTime(@Param("proberCardId") String proberCardId);

    public double getAfterPinByMaxTime(@Param("proberCardId") String proberCardId);

    public ArrayList<DepthSpecBean> getDepthSpec(@Param("proberCardId") String proberCardId);
}
