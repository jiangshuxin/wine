package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.service.IService;
import com.wuxian99.finance.basedata.service.system.DdicItemService;
import com.wuxian99.finance.basedata.service.system.MetadataService;
import com.wuxian99.finance.basedata.service.system.impl.UploadFileService;
import com.wuxian99.finance.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class Action {
	
	@Autowired IService service;
	@Autowired
	MetadataService metadataService;
	@Autowired
	DdicItemService ddicItemService;

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

	@RequestMapping("/api/{category}/ddic")
	public Object ddic(@PathVariable String category) {
		if("all".equalsIgnoreCase(category)){
			return Result.buildSuccess(ddicItemService.findAll());
		}
		return Result.buildSuccess(ddicItemService.findByCategory(category));
	}

	@Autowired
	private UploadFileService uploadFileService;

	@RequestMapping("/api/upload/{module}")
	public Result singleFileUpload(@RequestParam(name="upload",required = false) MultipartFile file, ModelMap modelMap, CUDCommand command, @PathVariable String module, @SessionAttribute("signinUser") SigninUser signinUser) throws IOException {
		if(file != null){
			UploadFileInfo result = uploadFileService.save(file,module);
			return UploadResult.buildSuccess(result);
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
