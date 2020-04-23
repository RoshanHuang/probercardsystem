package com.vtest.it.springcloudtoolingservice.controller;

import com.vtest.it.springcloudtoolingservice.domain.RawdataModifyBean;
import com.vtest.it.springcloudtoolingservice.service.dataparse.rawdataModify.RawdataModify;
import com.vtest.it.springcloudtoolingservice.service.dataparse.rawdataModify.RawdataModifyInitFile;
import com.vtest.it.springcloudtoolingservice.service.dataparse.rawdataModify.RawdataModifyUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

@RestController
@RequestMapping("/Modify")
@CrossOrigin
public class RawdataModifyController {
    private RawdataModify rawdataModify;
    private RawdataModifyInitFile rawdataModifyInitFile;
    private RawdataModifyUpdate rawdataModifyUpdate;

    @Autowired
    public void setRawdataModify(RawdataModify rawdataModify) {
        this.rawdataModify = rawdataModify;
    }

    @Autowired
    public void setRawdataModifyInitFile(RawdataModifyInitFile rawdataModifyInitFile) {
        this.rawdataModifyInitFile = rawdataModifyInitFile;
    }

    @Autowired
    public void setRawdataModifyUpdate(RawdataModifyUpdate rawdataModifyUpdate) {
        this.rawdataModifyUpdate = rawdataModifyUpdate;
    }

    @PostMapping(value = "/Rawdata", produces = {"application/json;charset=UTF-8"})
    public ArrayList<RawdataModifyBean> modify(@RequestParam("modifyFile") MultipartFile modifyFile, @RequestParam("type") String type) {
        ArrayList<RawdataModifyBean> dealList = new ArrayList<>();
        try {
            File Directory = new File("/FileUpload");
            if (!Directory.exists()) {
                Directory.mkdirs();
            }
            File backupFile = new File("/FileUpload/" + modifyFile.getOriginalFilename());
            modifyFile.transferTo(backupFile);
            File[] modifyDatalogs = rawdataModifyInitFile.initUploadFile(backupFile);
            if (type.equals("deepModify")) {
                dealList = rawdataModify.Transfer(modifyDatalogs, false);
            } else {
                dealList = rawdataModifyUpdate.Transfer(modifyDatalogs, false);
            }
            return dealList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/RawdataNormal", produces = {"application/json;charset=UTF-8"})
    public ArrayList<RawdataModifyBean> modifyNormal(@RequestParam("modifyFile") MultipartFile modifyFile, @RequestParam("type") String type) {
        ArrayList<RawdataModifyBean> dealList = new ArrayList<>();
        try {
            File Directory = new File("/FileUpload");
            if (!Directory.exists()) {
                Directory.mkdirs();
            }
            File backupFile = new File("/FileUpload/" + modifyFile.getOriginalFilename());
            modifyFile.transferTo(backupFile);
            File[] modifyDatalogs = rawdataModifyInitFile.initUploadFile(backupFile);
            if (type.equals("deepModify")) {
                dealList = rawdataModify.Transfer(modifyDatalogs, true);
            } else {
                dealList = rawdataModifyUpdate.Transfer(modifyDatalogs, true);
            }
            return dealList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
