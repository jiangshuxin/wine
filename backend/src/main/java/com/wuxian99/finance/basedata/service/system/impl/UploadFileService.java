package com.wuxian99.finance.basedata.service.system.impl;

import com.wuxian99.finance.basedata.domain.entity.system.UploadFileEntity;
import com.wuxian99.finance.basedata.repository.system.UploadFileRepository;
import com.wuxian99.finance.common.UploadFileInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by sxjiang on 2017/7/29.
 */
@Service
public class UploadFileService {
    @Autowired
    UploadFileRepository uploadFileRepository;

    @Value("${wine.storePath}")
    private String storePath;

    @Value("${wine.picPath}")
    private String picPath;

    public UploadFileInfo save(MultipartFile file,String module) throws IOException {
        String fileName = StringUtils.join(UUID.randomUUID().toString(),".",StringUtils.substringAfterLast(file.getOriginalFilename(),"."));
        String yyyyMMdd = DateFormatUtils.format(new Date(),"/yyyy/MM/dd/");
        String dir = StringUtils.join(storePath,module,yyyyMMdd);
        String relativePath = StringUtils.join(module,yyyyMMdd,fileName);
        String systemPath = StringUtils.join(storePath,relativePath);
        String webPath = StringUtils.join(picPath,relativePath);

        // Save the file locally
        FileUtils.forceMkdir(new File(dir));
        Path path = Paths.get(systemPath);
        byte[] bytes = file.getBytes();
        Files.write(path, bytes);


        UploadFileEntity uploadFileEntity = new UploadFileEntity();
        uploadFileEntity.setFileName(fileName);
        uploadFileEntity.setFileSize(file.getSize());
        uploadFileEntity.setRelativePath(relativePath);
        uploadFileRepository.save(uploadFileEntity);


        UploadFileInfo uploadResult = new UploadFileInfo();
        uploadResult.setSystem_path(systemPath);
        uploadResult.setWeb_path(webPath);
        uploadResult.setId(String.valueOf(uploadFileEntity.getId()));
        uploadResult.setFileName(fileName);
        uploadResult.setFileSize(String.valueOf(uploadFileEntity.getFileSize()));
        //FIXME mock
        //uploadResult.setWeb_path("http://123.57.234.184/wineStatic/2.png");
        return uploadResult;
    }

    public List<UploadFileInfo> findByIds(Integer... ids){
        List<UploadFileEntity> all = uploadFileRepository.findAll(Arrays.asList(ids));
        List<UploadFileInfo> list = new ArrayList();
        for(UploadFileEntity uploadFileEntity : all){
            UploadFileInfo uploadFileInfo = new UploadFileInfo();
            uploadFileInfo.setId(String.valueOf(uploadFileEntity.getId()));
            String relative = uploadFileEntity.getRelativePath();
            String fileName = uploadFileEntity.getFileName();
            uploadFileInfo.setId(String.valueOf(uploadFileEntity.getId()));
            uploadFileInfo.setSystem_path(StringUtils.join(storePath,relative));
            uploadFileInfo.setFileName(fileName);
            uploadFileInfo.setWeb_path(StringUtils.join(picPath,relative));
            uploadFileInfo.setRelative_path(relative);
            uploadFileInfo.setFileSize(String.valueOf(uploadFileEntity.getFileSize()));
            list.add(uploadFileInfo);
        }
        return list;
    }

    public Map<String,UploadFileInfo> findByIds(List<Integer> ids){
        Map<String,UploadFileInfo> uploadFileInfoMap = new HashMap<>();
        List<UploadFileInfo> uploadFileInfos = findByIds(ids.toArray(new Integer[0]));
        for(UploadFileInfo fileInfo : uploadFileInfos){
            uploadFileInfoMap.put(fileInfo.getId(),fileInfo);
        }
        return uploadFileInfoMap;
    }
}
