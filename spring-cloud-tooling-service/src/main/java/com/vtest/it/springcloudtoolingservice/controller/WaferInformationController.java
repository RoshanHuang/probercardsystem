package com.vtest.it.springcloudtoolingservice.controller;

import com.alibaba.fastjson.JSON;
import com.vtest.it.springcloudtoolingservice.domain.*;
import com.vtest.it.springcloudtoolingservice.service.dataparse.GetDataSourceConfig;
import com.vtest.it.springcloudtoolingservice.service.dataparse.ProberData;
import com.vtest.it.springcloudtoolingservice.service.dataparse.TesterData;
import com.vtest.it.springcloudtoolingservice.service.dataparse.TesterLocation;
import com.vtest.it.springcloudtoolingservice.service.dataparse.impl.CheckIfInforToMes;
import com.vtest.it.springcloudtoolingservice.service.dataparse.impl.ParseRawdataNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/information")
@CrossOrigin
public class WaferInformationController {
    @Autowired
    private TesterData testerData;
    @Autowired
    private ProberData proberData;
    @Autowired
    private CheckIfInforToMes checkIfInforToMes;
    @Autowired
    private ParseRawdataNew parseRawdataNew;
    @Autowired
    private GetDataSourceConfig getDataSourceConfig;
    @Autowired
    private TesterLocation testerLocation;


    @GetMapping(value = "/passBin/{waferId}",produces ="application/json;charset=UTF-8")
    public String getPassBin(@PathVariable("waferId")String waferId)
    {
        return testerLocation.getPassBin(waferId);
    }
    @GetMapping(value = "/TesterLocation")
    public ArrayList<testerLocationBean> getTesterLocation(){
        return  testerLocation.getTesterLocation();
    }
    @GetMapping(value = "/status",produces ="application/json;charset=UTF-8")
    public ArrayList<BinWaferInforBean> getStatus()
    {
        return  getDataSourceConfig.getTesterStatus();
    }

    @GetMapping(value="/testerStatusList/{tester}",produces ="application/json;charset=UTF-8")
    public List<WaferInforTesterListBean> getTesterStatusList(@PathVariable("tester") String tester) {
        return getDataSourceConfig.getTesterStatusList(tester);
    }
    @GetMapping("/testerStatusSingle/{tester}")
    public BinWaferInforBean getTesterStatusSingle(@PathVariable("tester") String tester) {
        return getDataSourceConfig.getTesterStatusSingle(tester);
    }
    @GetMapping("/Mapping/{code}/{device}/{lot}/{cp}/{waferId}")
    public ArrayList<mvcDieBean> getMap(@PathVariable("customer") String code, @PathVariable("device") String device, @PathVariable("lot") String lot, @PathVariable("cp") String cp, @PathVariable("wafer") String waferId) {
        if (checkIfInforToMes.check(code, device)) {
            ArrayList<mvcDieBean> result = parseRawdataNew.getMap(new File("/server212/RawData/TesterRawdatabackup/" + code + "/" + device + "/" + lot + "/" + cp + "/" + waferId + ".raw"));
            return result;
        } else {
            ArrayList<mvcDieBean> result = parseRawdataNew.getMap(new File("/server212/RawData/ProberRawdatabackup/" + code + "/" + device + "/" + lot + "/" + cp + "/" + waferId + ".raw"));
            return result;
        }
    }

    @GetMapping("/PrimaryOrRetestMap/{code}/{device}/{lot}/{cp}/{waferId}")
    public ArrayList<mvcDieBean> getPrimaryOrRetestMap(@PathVariable("customer") String code, @PathVariable("device") String device, @PathVariable("lot") String lot, @PathVariable("cp") String cp, @PathVariable("wafer") String waferId, @PathVariable("type") String type) {
        if (type.equals("P")) {
            ArrayList<mvcDieBean> result = parseRawdataNew.getPrimaryMap(new File("/server212/RawData/RawdataDetails/" + code + "/" + device + "/" + lot + "/" + cp + "/" + waferId + ".dtl"));
            return result;
        } else {
            ArrayList<mvcDieBean> result = parseRawdataNew.getRetestMap(new File("/server212/RawData/RawdataDetails/" + code + "/" + device + "/" + lot + "/" + cp + "/" + waferId + ".dtl"));
            return result;
        }
    }
    @GetMapping("/dataSourceType/{customer}/{device}")
    public boolean checkDataTypeSource(@PathVariable("customer") String customer, @PathVariable("device") String device) {
        return checkIfInforToMes.check(customer, device);
    }
    @GetMapping("/binSummary/{customer}/{device}/{lot}/{cp}")
    public ArrayList<LotSummaryWaferBean> getBinSummary(@PathVariable("customer") String customer, @PathVariable("device") String device, @PathVariable("lot") String lot, @PathVariable("cp") String cp) {
        ArrayList<waferYieldBean> summary = null;
        ArrayList<LotSummaryWaferBean> lotSummary = null;
        if (!checkIfInforToMes.check(customer, device)) {
            summary = proberData.getWaferBinSummary(customer, device, lot, cp, null);
            lotSummary = proberData.getwaferInfor(customer, device, lot, cp);
        } else {
            summary = testerData.getWaferBinSummary(customer, device, lot, cp, null);
            lotSummary = testerData.getwaferInfor(customer, device, lot, cp);
        }
        TreeSet<Integer> binSet = new TreeSet<>();
        HashMap<String, TreeMap<Integer, Integer>> lotBinMap = new HashMap<>();
        for (waferYieldBean bean : summary) {
            binSet.add(bean.getSoftBinNo());
            if (lotBinMap.containsKey(bean.getWaferNo())) {
                TreeMap<Integer, Integer> binMap = lotBinMap.get(bean.getWaferNo());
                if (binMap.containsKey(bean.getSoftBinNo())) {
                    binMap.put(bean.getSoftBinNo(), binMap.get(bean.getSoftBinNo()) + bean.getBinCount());
                } else {
                    binMap.put(bean.getSoftBinNo(), bean.getBinCount());
                }
            } else {
                TreeMap<Integer, Integer> binMap = new TreeMap<>();
                binMap.put(bean.getSoftBinNo(), bean.getBinCount());
                lotBinMap.put(bean.getWaferNo(), binMap);
            }
        }
        TreeSet<String> waferIdSet = new TreeSet<>(lotBinMap.keySet());
        for (String waferId : waferIdSet) {
            TreeMap<Integer, Integer> binSummary = lotBinMap.get(waferId);
            for (Integer bin : binSet) {
                if (!binSummary.containsKey(bin)) {
                    binSummary.put(bin, 0);
                }
            }
            for (LotSummaryWaferBean bean : lotSummary) {
                if (bean.getWaferNo().equals(waferId)) {
                    ArrayList<Integer> yyy = new ArrayList<Integer>(binSummary.values());
                    bean.setBinSummary(yyy);
                }
            }
        }
        LotSummaryWaferBean binDefine = new LotSummaryWaferBean();
        binDefine.setWaferNo("bin_define");
        binDefine.setBinSummary(new ArrayList<Integer>(binSet));
        lotSummary.add(0, binDefine);
        return lotSummary;
    }
    @GetMapping("/superPosition/{customer}/{device}/{lot}/{cp}")
    public ArrayList<superPostionBean> getSuperPosition(@PathVariable("customer") String customer, @PathVariable("device") String device, @PathVariable("lot") String lot, @PathVariable("cp") String cp) {
        ArrayList<superPostionBean> result = new ArrayList<>();
        ArrayList<Integer> passBins = new ArrayList<>();
        File[] rawdatas = null;
        if (checkIfInforToMes.check(customer, device)) {
            rawdatas = new File("/server212/RawData/TesterRawdatabackup/" + customer + "/" + device + "/" + lot + "/" + cp).listFiles();
        } else {
            rawdatas = new File("/server212/RawData/ProberRawdatabackup/" + customer + "/" + device + "/" + lot + "/" + cp).listFiles();
        }
        String passInfor = "1";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(rawdatas[0]));
            String content;
            while ((content = reader.readLine()) != null) {
                if (content.contains("Pass Bins")) {
                    passInfor = content.split(":")[1];
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            passBins.add(1);
        }
        String[] passBinStr = (passInfor).split(",");
        for (int i = 0; i < passBinStr.length; i++) {
            passBins.add(Integer.valueOf(passBinStr[i]));
        }
        HashMap<String, Integer> resultMap = new HashMap<>();
        for (File rawdata : rawdatas) {
            ArrayList<mvcDieBean> binInfors = parseRawdataNew.getMap(rawdata);
            for (mvcDieBean bean : binInfors) {
                if (!passBins.contains(bean.getS())) {
                    if (resultMap.containsKey(bean.getX() + ":" + bean.getY())) {
                        resultMap.put(bean.getX() + ":" + bean.getY(), resultMap.get(bean.getX() + ":" + bean.getY()) + 1);
                    } else {
                        resultMap.put(bean.getX() + ":" + bean.getY(), 1);
                    }
                } else if (!resultMap.containsKey(bean.getX() + ":" + bean.getY())) {
                    resultMap.put(bean.getX() + ":" + bean.getY(), 0);
                }
            }
        }
        Set<String> coordinateSet = resultMap.keySet();
        for (String coodinate : coordinateSet) {
            superPostionBean bean = new superPostionBean();
            bean.setX(Integer.valueOf(coodinate.substring(0, coodinate.indexOf(":"))));
            bean.setY(Integer.valueOf(coodinate.substring(coodinate.indexOf(":") + 1)));
            bean.setV(resultMap.get(coodinate));
            result.add(bean);
        }
        return result;
    }
    @GetMapping("/customerAndDevices")
    public ArrayList<customerAndDevice> getCustomerAndDevice() {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        ArrayList<DataInforToMesBean> allConfigs = getDataSourceConfig.getList();
        ArrayList<CustomerAndDevicesBean> testerList = testerData.getCustomerAndDevices();
        for (DataInforToMesBean bean : allConfigs) {
            String dbCustomer = bean.getCustomCode();
            String dbDevice = bean.getDevice();
            if (dbDevice.equals("ALL")) {
                for (CustomerAndDevicesBean listBean : testerList) {
                    if (listBean.getCustomerCode().equals(dbCustomer)) {
                        if (result.containsKey(dbCustomer)) {
                            ArrayList<String> devices = result.get(dbCustomer);
                            devices.add(listBean.getDevice());
                            result.put(dbCustomer, devices);
                        } else {
                            ArrayList<String> devices = new ArrayList<>();
                            devices.add(listBean.getDevice());
                            result.put(dbCustomer, devices);
                        }
                    }
                }
            } else {
                if (result.containsKey(dbCustomer)) {
                    ArrayList<String> devices = result.get(dbCustomer);
                    devices.add(dbDevice);
                    result.put(dbCustomer, devices);
                } else {
                    ArrayList<String> devices = new ArrayList<>();
                    devices.add(dbDevice);
                    result.put(dbCustomer, devices);
                }
            }
        }
        ArrayList<CustomerAndDevicesBean> list = proberData.getCustomerAndDevices();
        for (CustomerAndDevicesBean bean : list) {
            if (!checkIfInforToMes.check(bean.getCustomerCode(), bean.getDevice(),allConfigs)) {
                if (result.containsKey(bean.getCustomerCode())) {
                    ArrayList<String> devices = result.get(bean.getCustomerCode());
                    devices.add(bean.getDevice());
                    result.put(bean.getCustomerCode(), devices);
                } else {
                    ArrayList<String> devices = new ArrayList<>();
                    devices.add(bean.getDevice());
                    result.put(bean.getCustomerCode(), devices);
                }
            }
        }
        ArrayList<customerAndDevice> customerAndDevicesArray = new ArrayList<>();
        Set<String> customerSet = result.keySet();
        for (String customer : customerSet) {
            customerAndDevice customerAndDevice = new customerAndDevice();
            customerAndDevice.setCustomer(customer);
            customerAndDevice.setDevice(result.get(customer));
            customerAndDevicesArray.add(customerAndDevice);
        }
        return customerAndDevicesArray;
    }

    @GetMapping("/lotAndCps/{customer}/{device}")
    public ArrayList<lotAndCp> getLotAndCps(@PathVariable("customer") String customer, @PathVariable("device") String device) {
        ArrayList<LotAndCpsBean> list = null;
        if (checkIfInforToMes.check(customer, device)) {
            list = testerData.getLotAndCp(customer, device);
        } else {
            list = proberData.getLotAndCp(customer, device);
        }
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (LotAndCpsBean bean : list) {
            if (result.containsKey(bean.getLot())) {
                ArrayList<String> cps = result.get(bean.getLot());
                cps.add(bean.getCp());
                result.put(bean.getLot(), cps);
            } else {
                ArrayList<String> cps = new ArrayList<>();
                cps.add(bean.getCp());
                result.put(bean.getLot(), cps);
            }
        }
        ArrayList<lotAndCp> lotAndCpssArray = new ArrayList<>();
        Set<String> lotSet = result.keySet();
        for (String lot : lotSet) {
            lotAndCp lotAndCp = new lotAndCp();
            lotAndCp.setLot(lot);
            lotAndCp.setCp(result.get(lot));
            lotAndCpssArray.add(lotAndCp);
        }
        return lotAndCpssArray;
    }

    @GetMapping("/waferIds/{customer}/{device}/{lot}/{cp}")
    public ArrayList<String> getWaferIDs(@PathVariable("customer") String customer, @PathVariable("device") String device, @PathVariable("lot") String lot, @PathVariable("cp") String cp) {
        ArrayList<WaferIdBean> waferIds = null;
        if (checkIfInforToMes.check(customer, device)) {
            waferIds = testerData.getWaferIds(customer, device, lot, cp);
        } else {
            waferIds = proberData.getWaferIds(customer, device, lot, cp);
        }

        ArrayList<String> result = new ArrayList<>();
        for (WaferIdBean bean : waferIds) {
            result.add(bean.getWaferNo());
        }
        return result;
    }

    @PostMapping("/qureyInfors")
    public ArrayList<GetWaferInforBean> getqureyWaferInfors(@RequestBody VtestBean bean) {
        ArrayList<GetWaferInforBean> result = null;
        if (checkIfInforToMes.check(bean.getCustomer(), bean.getDevice())) {
            result = testerData.getQureyInfors(bean.getCustomer(),bean.getDevice(),bean.getLot(),bean.getCp(),bean.getWaferId());
        } else {
            result = proberData.getQureyInfors(bean.getCustomer(),bean.getDevice(),bean.getLot(),bean.getCp(),bean.getWaferId());
        }

        return result;
    }

    @PostMapping("/waferIdSummaryTotal")
    public  TreeMap<String, Integer> getWaferIdInforTotal(@RequestBody  VtestBean vtestBean) {
        TreeMap<String, Integer> totalSummary = new TreeMap<>();
        ArrayList<waferYieldBean> summary = null;
        if (checkIfInforToMes.check(vtestBean.getCustomer(), vtestBean.getDevice())) {
            summary = testerData.getWaferBinSummaryByType(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId(),vtestBean.getType());
        } else {
            summary = proberData.getWaferBinSummary(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId());
        }
            for (waferYieldBean bean : summary) {
                if (totalSummary.containsKey(String.valueOf(bean.getSoftBinNo()))) {
                    totalSummary.put(String.valueOf(bean.getSoftBinNo()), totalSummary.get(String.valueOf(bean.getSoftBinNo())) + bean.getBinCount());
                } else {
                    totalSummary.put(String.valueOf(bean.getSoftBinNo()), bean.getBinCount());
                }
            }
            return totalSummary;
    }
    @PostMapping("/waferIdSummary")
    public  ArrayList<waferSummary> getWaferIdInforByType(@RequestBody  VtestBean vtestBean) {
        ArrayList<waferYieldBean> summary = null;
        if (checkIfInforToMes.check(vtestBean.getCustomer(), vtestBean.getDevice())) {
            summary = testerData.getWaferBinSummaryByType(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId(),vtestBean.getType());
        } else {
            summary = proberData.getWaferBinSummary(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId());
        }
            Set<Integer> siteSet = new HashSet<>();
            Set<Integer> SoftBinSet = new HashSet<>();
            ArrayList<waferSummary> allSiteInfors = new ArrayList<>();
            waferSummary totalSummaryBean = new waferSummary();
            int totalDie = 0;
            int totalPass = 0;
            int totalFail = 0;
            TreeMap<String, Integer> totalSummaryMap = new TreeMap<>();
            for (waferYieldBean bean : summary) {
                int sum = bean.getBinCount();
                boolean passFlag = bean.getPassFail();
                int site = bean.getSiteId();
                int softBin = bean.getSoftBinNo();
                SoftBinSet.add(softBin);
                totalDie += sum;
                if (passFlag) {
                    totalPass += sum;
                } else {
                    totalFail += sum;
                }
                if (totalSummaryMap.containsKey(String.valueOf(softBin))) {
                    totalSummaryMap.put(String.valueOf(softBin), totalSummaryMap.get(String.valueOf(softBin)) + sum);
                } else {
                    totalSummaryMap.put(String.valueOf(softBin), sum);
                }
                if (siteSet.contains(site)) {
                    for (waferSummary infor : allSiteInfors) {
                        if (infor.getName().equals("Site" + site)) {
                            infor.setTotal(infor.getTotal() + sum);
                            infor.setPass(passFlag ? infor.getPass() + sum : infor.getPass());
                            infor.setFail(passFlag ? infor.getFail() : sum + infor.getFail());
                            TreeMap<String, Integer> siteSummaryMap = infor.getBinMap();
                            siteSummaryMap.put(String.valueOf(softBin), sum);
                            infor.setBinMap(siteSummaryMap);
                        }
                    }
                } else {
                    waferSummary siteWaferSummarybean = new waferSummary();
                    siteWaferSummarybean.setName("Site" + site);
                    siteWaferSummarybean.setTotal(sum);
                    siteWaferSummarybean.setPass(passFlag ? sum : 0);
                    siteWaferSummarybean.setFail(passFlag ? 0 : sum);
                    TreeMap<String, Integer> siteSummaryMap = new TreeMap<>();
                    siteSummaryMap.put(String.valueOf(softBin), sum);
                    siteWaferSummarybean.setBinMap(siteSummaryMap);
                    allSiteInfors.add(siteWaferSummarybean);
                }
                siteSet.add(site);
            }
            totalSummaryBean.setName("total");
            totalSummaryBean.setTotal(totalDie);
            totalSummaryBean.setPass(totalPass);
            totalSummaryBean.setFail(totalFail);
            totalSummaryBean.setYield((double) totalPass / totalDie);
            totalSummaryBean.setTotalYield((double) totalPass / totalDie);
            totalSummaryBean.setBinMap(totalSummaryMap);

            for (waferSummary infor : allSiteInfors) {
                infor.setTotalYield((double) infor.getPass() / totalDie);
                infor.setYield((double) infor.getPass() / infor.getTotal());
                TreeMap<String, Integer> siteSummaryMap = infor.getBinMap();
                for (Integer softBin : SoftBinSet) {
                    if (!siteSummaryMap.containsKey(String.valueOf(softBin))) {
                        siteSummaryMap.put(String.valueOf(softBin), 0);
                    }
                }
                infor.setBinMap(siteSummaryMap);
            }
            allSiteInfors.add(0, totalSummaryBean);
            return allSiteInfors;
    }
    @PostMapping("/yields")
    public ArrayList<Infor> getYields(@RequestBody  VtestBean vtestBean) {
        ArrayList<waferYieldBean> summary = null;
        ArrayList<waferIdInforBean> paramBeanSummary = null;
        if (checkIfInforToMes.check(vtestBean.getCustomer(),vtestBean.getDevice())) {
            summary = testerData.getWaferBinSummary(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId());
            paramBeanSummary = testerData.getOthersParam(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId());
        } else {
            summary = proberData.getWaferBinSummary(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId());
            paramBeanSummary = proberData.getOthersParam(vtestBean.getCustomer(),vtestBean.getDevice(),vtestBean.getLot(),vtestBean.getCp(),vtestBean.getWaferId());
        }
        HashMap<Integer, String> lotMap = new HashMap<>();
        HashMap<String, String> waferLimitYieldMap = new HashMap<>();
        HashMap<String, String> lotLimitYieldMap = new HashMap<>();
        for (waferIdInforBean bean : paramBeanSummary) {
            String id = JSON.parseObject(bean.getParams()).get("RightID").toString();
            String limits=JSON.parseObject(bean.getParams()).get("Process Yield").toString();
            String waferLimit = limits.split("\\|")[0];
            String lotLimit = limits.split("\\|")[1];
            String waferId = bean.getWaferId();
            lotMap.put(Integer.valueOf(id), waferId);
            waferLimitYieldMap.put(waferId, waferLimit.equals("NA") ? "0.00" : waferLimit.substring(0, waferLimit.length() - 1));
            lotLimitYieldMap.put(waferId, lotLimit.equals("NA") ? "0.00" : lotLimit.substring(0, lotLimit.length() - 1));
        }
        TreeSet<Integer> binSet = new TreeSet<>();
        HashMap<String, Integer> passDieMap = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> lotBinMap = new HashMap<>();
        HashMap<String, Integer> waferIdGrossDieMap = new HashMap<>();
        for (waferYieldBean bean : summary) {
            binSet.add(bean.getSoftBinNo());
            if (waferIdGrossDieMap.containsKey(bean.getWaferNo())) {
                waferIdGrossDieMap.put(bean.getWaferNo(), bean.getBinCount() + waferIdGrossDieMap.get(bean.getWaferNo()));
            } else {
                waferIdGrossDieMap.put(bean.getWaferNo(), bean.getBinCount());
            }
            if (bean.getPassFail()) {
                if (passDieMap.containsKey(bean.getWaferNo())) {
                    passDieMap.put(bean.getWaferNo(), passDieMap.get(bean.getWaferNo()) + bean.getBinCount());
                } else {
                    passDieMap.put(bean.getWaferNo(), bean.getBinCount());
                }
            }
            if (lotBinMap.containsKey(bean.getWaferNo())) {
                HashMap<Integer, Integer> binMap = lotBinMap.get(bean.getWaferNo());
                if (binMap.containsKey(bean.getSoftBinNo())) {
                    binMap.put(bean.getSoftBinNo(), binMap.get(bean.getSoftBinNo()) + bean.getBinCount());
                } else {
                    binMap.put(bean.getSoftBinNo(), bean.getBinCount());
                }
            } else {
                HashMap<Integer, Integer> binMap = new HashMap<>();
                binMap.put(bean.getSoftBinNo(), bean.getBinCount());
                lotBinMap.put(bean.getWaferNo(), binMap);
            }
        }
        //wafer order by id
        Infor waferIdsOrder = new Infor();
        waferIdsOrder.setName("wafer");
        Infor passYieldInfor = new Infor();
        passYieldInfor.setName("yield");
        Infor waferLimit = new Infor();
        waferLimit.setName("wafer yield limit");
        ArrayList<Object> waferLimitYieldArray = new ArrayList<>();

        Infor lotLimit = new Infor();
        lotLimit.setName("lot yield limit");
        ArrayList<Object> lotLimitYieldArray = new ArrayList<>();

        ArrayList<Object> waferIdArray = new ArrayList<>();
        ArrayList<Object> waferIdPassYieldArray = new ArrayList<>();
        TreeSet<Integer> waferOrderSet = new TreeSet<>(lotMap.keySet());
        ArrayList<Infor> binYieldInforArray = new ArrayList<>();
        for (Integer bin : binSet) {
            Infor infor = new Infor();
            infor.setName("Bin" + bin);
            ArrayList<Object> yieldArray = new ArrayList<>();
            infor.setValues(yieldArray);
            binYieldInforArray.add(infor);
        }
        for (Integer order : waferOrderSet) {
            String waferId = lotMap.get(order);
            waferLimitYieldArray.add(waferLimitYieldMap.get(waferId));
            lotLimitYieldArray.add(lotLimitYieldMap.get(waferId));

            waferIdArray.add(waferId);
            HashMap<Integer, Integer> binSummaryMap = lotBinMap.get(waferId);
            for (Infor infor : binYieldInforArray) {
                Integer bin = Integer.valueOf(infor.getName().substring(3));
                if (binSummaryMap.containsKey(bin)) {
                    infor.getValues().add(String.format("%.6f", ((double) binSummaryMap.get(bin)) * 100 / waferIdGrossDieMap.get(waferId)));
                } else {
                    infor.getValues().add(String.format("%.6f", 0.00));
                }
            }
            waferIdPassYieldArray.add(String.format("%.6f", (((double) (null != passDieMap.get(waferId) ? passDieMap.get(waferId) : 0)) * 100 / waferIdGrossDieMap.get(waferId))));
        }
        waferLimit.setValues(waferLimitYieldArray);
        lotLimit.setValues(lotLimitYieldArray);
        waferIdsOrder.setValues(waferIdArray);
        passYieldInfor.setValues(waferIdPassYieldArray);
        binYieldInforArray.add(waferIdsOrder);
        binYieldInforArray.add(0, passYieldInfor);
        binYieldInforArray.add(waferLimit);
        binYieldInforArray.add(lotLimit);
        //wafer  pass yield by id
        return binYieldInforArray;
    }
    class Infor {
        private String name;
        private ArrayList<Object> values;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Object> getValues() {
            return values;
        }

        public void setValues(ArrayList<Object> values) {
            this.values = values;
        }
    }

    class customerAndDevice {
        private String customer;
        private ArrayList<String> device;

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public ArrayList<String> getDevice() {
            return device;
        }

        public void setDevice(ArrayList<String> device) {
            this.device = device;
        }
    }

    class lotAndCp {
        private String lot;
        private ArrayList<String> cp;

        public String getLot() {
            return lot;
        }

        public void setLot(String lot) {
            this.lot = lot;
        }

        public ArrayList<String> getCp() {
            return cp;
        }

        public void setCp(ArrayList<String> cp) {
            this.cp = cp;
        }
    }

    class superPostionBean {
        private Integer x;
        private Integer y;
        private Integer v;

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }
    }

    class waferSummary {
        private String name;
        private int total;
        private int pass;
        private int fail;
        private double totalYield;
        private double yield;
        TreeMap<String, Integer> binMap = new TreeMap<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPass() {
            return pass;
        }

        public void setPass(int pass) {
            this.pass = pass;
        }

        public int getFail() {
            return fail;
        }

        public void setFail(int fail) {
            this.fail = fail;
        }

        public double getTotalYield() {
            return totalYield;
        }

        public void setTotalYield(double totalYield) {
            this.totalYield = totalYield;
        }

        public double getYield() {
            return yield;
        }

        public void setYield(double yield) {
            this.yield = yield;
        }

        public TreeMap<String, Integer> getBinMap() {
            return binMap;
        }

        public void setBinMap(TreeMap<String, Integer> binMap) {
            this.binMap = binMap;
        }
    }
}
