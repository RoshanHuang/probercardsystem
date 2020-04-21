package com.vtest.it.springcloudtoolingservice.service.dataparse;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public interface Rawdata {
    abstract LinkedHashMap<String, String> getProperties();

    abstract TreeMap<Integer, Integer> getBinSummary();

    abstract HashMap<String, String> getMarkAndSkipDies();

    abstract String[][] getMarkAndSkipDiesDimensionalArray();

    abstract HashMap<String, String> getHardBinTestDie();

    abstract HashMap<String, String> getSoftBinTestDie();

    abstract String[][] getHardBinTestDiesDimensionalArray();

    abstract String[][] getSoftBinTestDiesDimensionalArray();
}
