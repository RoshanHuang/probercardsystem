package com.vtest.it.springcloudprobercardservice.service.probercard;

import com.vtest.it.springcloudprobercardservice.domain.*;

import java.util.ArrayList;
import java.util.List;


public interface ProberCardInformation {
    public List<ProberCardEntityBean> getAllList();

    public ProberCardEntityBean getCard(String cardId);

    public String getProberCardStatus(String proberCardId);

    public ReleaseProberCardBean getReleaseCardInfo(String proberCardId);

    public ArrayList<ProberCardStatusBean> getAllProberCardStatus();

    public boolean getProberCardReleaseFlag(String proberCardId);

    public ArrayList<IqcRecordBean> getAllIQCRecord();

    public ArrayList<ProberCardMaintainBean> getAllMaintainRecord();

    public Integer getInfoRebuildCount(String proberCardId);

    public ArrayList<ProberCardTDBean> getTd();

    public ArrayList<OutProberCardBean> getOutProberCard(String proberCardId);

    public ArrayList<BackProberCardBean> getBackProberCard(String proberCardId);

    public ArrayList<ProberCardEntityBean> getInfoProberCard(String proberCardId);

    public ArrayList<IqcRecordBean> getIQCProberCard(String proberCardId);

    public ArrayList<ProberCardMaintainBean> getMaintainProberCard(String proberCardId);

    public ArrayList<ReleaseProberCardBean> getReleaseProberCard(String proberCardId);

    public ArrayList<ProberCardEntityBean> getProberCardId(String custName);

    public ArrayList<ProberCardExtensionBean> getEXRecord(String proberCardId);

    public ArrayList<String> getProberCardEX();

    public ArrayList<ProberCardExtensionBean> getEXInfoSingle(String proberCardId);

    public ArrayList<IqcRecordBean> getAllIQCRecordByMinTime();

    public ArrayList<IqcRecordBean> getAllIQCRecordByMaxTime();

    public ArrayList<IqcRecordBean> getAllIQCRecordByRebuild();

    public Integer getExistFlag(String proberCardId);

    public ArrayList<ProberCardEntityBean> getSpec(String proberCardId);

    public double getPinMinByMaxTime(String proberCardId);

    public double getAfterPinByMaxTime(String proberCardId);

}
