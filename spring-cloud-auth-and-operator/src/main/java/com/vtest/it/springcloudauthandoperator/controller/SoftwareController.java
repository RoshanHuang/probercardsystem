package com.vtest.it.springcloudauthandoperator.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;

@RestController
@CrossOrigin
public class SoftwareController {
    @GetMapping("/download/{SourceName:.+}")
    public void getSource(@PathVariable("SourceName") String sourceName, OutputStream outputStream) {
        try {
            HttpHeaders headers = new HttpHeaders();
            File source = new File("/vtestSoftSource/" + sourceName);
            FileInputStream fileInputStream = new FileInputStream(source);
            FileCopyUtils.copy(fileInputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/png/{SourceName:.+}")
    public void getPng(@PathVariable("SourceName") String pngName, OutputStream outputStream) {
        File png = new File("/vtestSoftSourceDescriptions/" + pngName + ".png");
        try {
            FileInputStream inputStream = new FileInputStream(png);
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/sourceList")
    public String getSourcesList() {
        ArrayList<VtestSource> sources = new ArrayList<>();
        File file = new File("/vtestSoftSource/");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            VtestSource source = new VtestSource();
            String sourceName = files[i].getName();
            source.setName(sourceName);
            source.setImage("/png/" + sourceName);
            StringBuilder stringBuilder = new StringBuilder();
            File description = new File("/vtestSoftSourceDescriptions/" + sourceName + ".txt");
            try {
                String content = null;
                BufferedReader reader = new BufferedReader(new FileReader(description));
                while ((content = reader.readLine()) != null) {
                    stringBuilder.append(content);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            source.setDescription(stringBuilder.toString());
            source.setUrl("/download/" + sourceName);
            sources.add(source);
        }
        return JSON.toJSONString(sources);
    }

    class VtestSource {
        private String name;
        private String image;
        private String description;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
