package com.wuxian99.finance.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sxjiang on 2017/7/25.
 */
public class UploadResult extends Result{

    private Map<String,Map<String,UploadFileInfo>> files;
    private UploadInfo upload;
    private List<FieldError> fieldErrors;

    public static UploadResult buildSuccess(UploadFileInfo uploadFileInfo) {
        UploadResult uploadResult = new UploadResult();
        UploadInfo uploadInfo = new UploadInfo();
        uploadInfo.setId(uploadFileInfo.getId());
        Map<String,Map<String,UploadFileInfo>> files = new HashMap<>();
        Map<String,UploadFileInfo> fileInfoMap = new HashMap<>();
        files.put("files",fileInfoMap);
        fileInfoMap.put(uploadInfo.getId(),uploadFileInfo);
        uploadResult.setFiles(files);
        uploadResult.setUpload(uploadInfo);
        return uploadResult;
    }

    public static UploadResult buildError(String errorMsg){
        List<FieldError> fieldErrors = new ArrayList<>();
        FieldError fieldError = new FieldError();
        fieldError.setName("files[].id");
        fieldError.setStatus(errorMsg);
        UploadResult uploadResult = new UploadResult();
        uploadResult.setFieldErrors(fieldErrors);
        return uploadResult;
    }

    public Map<String, Map<String, UploadFileInfo>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Map<String, UploadFileInfo>> files) {
        this.files = files;
    }

    public UploadInfo getUpload() {
        return upload;
    }

    public void setUpload(UploadInfo upload) {
        this.upload = upload;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
