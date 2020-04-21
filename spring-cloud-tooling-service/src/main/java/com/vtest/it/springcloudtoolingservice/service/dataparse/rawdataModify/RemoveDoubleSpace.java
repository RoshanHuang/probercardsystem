package com.vtest.it.springcloudtoolingservice.service.dataparse.rawdataModify;

import org.springframework.stereotype.Service;

@Service
public class RemoveDoubleSpace {
    public String remove(String fileName) {
        fileName = fileName.replaceAll("__", "_");
        return fileName;
    }
}
