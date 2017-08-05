package com.wuxian99.finance.common;

/**
 * Created by sxjiang on 2017/7/25.
 */
public class UploadFileInfo {
    private String fileName;
    private String fileSize;
    private String id;
    private String system_path;
    private String web_path;
    private String relative_path;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystem_path() {
        return system_path;
    }

    public void setSystem_path(String system_path) {
        this.system_path = system_path;
    }

    public String getWeb_path() {
        return web_path;
    }

    public void setWeb_path(String web_path) {
        this.web_path = web_path;
    }

    public String getRelative_path() {
        return relative_path;
    }

    public void setRelative_path(String relative_path) {
        this.relative_path = relative_path;
    }
}
