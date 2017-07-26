package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.service.IService;
import com.wuxian99.finance.basedata.service.system.MetadataService;
import com.wuxian99.finance.common.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
public class Action {
	
	@Autowired IService service;
	@Autowired
	MetadataService metadataService;

	@RequestMapping("/api/{module}/list")
    public Result<?> findAll(ModelMap modelMap, @RequestBody QueryCommand command, @PathVariable String module, @SessionAttribute("signinUser") SigninUser signinUser){
		command.setModule(module);
		Result<?> result = service.executeFind(command,signinUser);
		modelMap.put("moduleName",module);
        return result;
    }

	@RequestMapping({"/api/{module}/create","/api/{module}/edit"})
    public Result<?> save(ModelMap modelMap,@RequestBody CUDCommand command, @PathVariable String module, @SessionAttribute("signinUser") SigninUser signinUser){
		command.setModule(module);
		Result<?> result = service.executeCud(command,signinUser);
		modelMap.put("moduleName",module);
        return result;
    }

	@RequestMapping("/api/{module}/remove")
    public Result<?> delete(ModelMap modelMap,@RequestBody CUDCommand command, @PathVariable String module, @SessionAttribute("signinUser") SigninUser signinUser){
		command.setModule(module);
		Result<?> result = service.executeCud(command,signinUser);
		modelMap.put("moduleName",module);
        return result;
    }

	@RequestMapping("/api/{module}/ddic")
	public Object ddic(@PathVariable String module) {
		return metadataService.findDdic(module);
	}

	@Value("${wine.storePath}")
	private String storePath;

	@Value("${wine.picPath}")
	private String picPath;

	@RequestMapping("/api/upload/{module}")
	public Result singleFileUpload(@RequestParam(name="upload",required = false) MultipartFile file, ModelMap modelMap, CUDCommand command, @PathVariable String module, @SessionAttribute("signinUser") SigninUser signinUser) throws IOException {
		if(file != null){
			String yyyyMMdd = DateFormatUtils.format(new Date(),"/yyyy/MM/dd/");
			String dirStr = StringUtils.join(storePath,module,yyyyMMdd);
			String fileName = file.getOriginalFilename();
			FileUtils.forceMkdir(new File(dirStr));

			// Save the file locally
			Path path = Paths.get(dirStr + fileName);
			byte[] bytes = file.getBytes();
			Files.write(path, bytes);
			UploadFileInfo uploadFileInfo = new UploadFileInfo();
			uploadFileInfo.setId("1");
			uploadFileInfo.setFileName(fileName);
			uploadFileInfo.setFileSize(String.valueOf(file.getSize()));
			uploadFileInfo.setSystem_path(path.toString());
			//uploadFileInfo.setWeb_path(StringUtils.join(picPath,module,yyyyMMdd,fileName));
			uploadFileInfo.setWeb_path("http://123.57.234.184/wineStatic/2.png");
			return UploadResult.buildSuccess(uploadFileInfo);
		}
		if(command != null){
			command.setModule(module);
			Result<?> result = service.executeCud(command,signinUser);
			modelMap.put("moduleName",module);
			return result;
		}
		return Result.buildSuccess();
	}
}
