package com.wuxian99.finance.basedata.service.system;

import com.wuxian99.finance.basedata.domain.model.MetadataInfo;

import java.util.List;
import java.util.Map;

public interface MetadataService {
	List<MetadataInfo> findAll();
	
	MetadataInfo findByModule(String module);
	
	Map<String, Object> findNeedUpload();
	
	MetadataInfo save(MetadataInfo metadataInfo);
	
	List<MetadataInfo> findModuleByGroup(String group);
	
	Map<String, Object> findDdic(String module);
}
