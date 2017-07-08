package com.wuxian99.finance.basedata.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuxian99.finance.basedata.domain.model.SigninUser;
import com.wuxian99.finance.basedata.service.IService;
import com.wuxian99.finance.basedata.service.system.MetadataService;
import com.wuxian99.finance.common.CUDCommand;
import com.wuxian99.finance.common.QueryCommand;
import com.wuxian99.finance.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
}
