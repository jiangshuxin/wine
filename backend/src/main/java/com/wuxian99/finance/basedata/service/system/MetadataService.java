package com.wuxian99.finance.basedata.service.system;

import com.wuxian99.finance.basedata.domain.model.MetadataInfo;
import com.wuxian99.finance.basedata.support.annotation.UploadRef;

import java.util.List;
import java.util.Map;

public interface MetadataService {
	List<MetadataInfo> findAll();
	
	MetadataInfo findByModule(String module);
	
	MetadataInfo save(MetadataInfo metadataInfo);
	
	Map<String, Map<String, UploadRef>> getEntityUploadRefMap();
}
