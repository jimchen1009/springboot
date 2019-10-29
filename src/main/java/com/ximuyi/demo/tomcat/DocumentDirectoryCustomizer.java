package com.ximuyi.demo.tomcat;

import org.apache.catalina.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class DocumentDirectoryCustomizer implements TomcatContextCustomizer {

	private static final Logger logger = LoggerFactory.getLogger(DocumentDirectoryCustomizer.class);


	@Override
	public void customize(Context context) {
		try {
			File resource = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "document");
			String absolutePath = resource.getAbsolutePath();
			context.setDocBase(absolutePath);
		} catch (FileNotFoundException e) {
			logger.error("", e);
		}
	}
}
