package com.wuxian99.finance.basedata.web.action;

import com.wuxian99.finance.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

@RestController
public class UploadAction {
	static final Logger logger = LoggerFactory.getLogger(UploadAction.class);

	@Autowired
	private MultipartProperties multipartProperties;

	@Value("${handpay.upload.ftpDir}")
	private String ftpDir;
	@Value("${handpay.upload.ftpType}")
	private String ftpType;
	@Value("${handpay.upload.ftpUser}")
	private String ftpUser;
	@Value("${handpay.upload.ftpPassword}")
	private String ftpPassword;

	private Pattern pattern = Pattern.compile("(\\D+)-(\\D+)-(\\d+[_]*\\d*)\\.(\\D+)");

	@RequestMapping("/upload/{module}")
	public Result<?> singleFileUpload(@RequestParam("uploadFile") MultipartFile file, @PathVariable String module) {

		return Result.buildSuccess(null);
	}

	
}
