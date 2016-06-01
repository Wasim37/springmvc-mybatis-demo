package com.tyxj.build;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @ClassName: MybatisBuilder
 * @Description: 代码生成器
 * @author WangX
 * @date 2016年4月12日 上午9:54:37
 */
public class MybatisBuilder {
	private static final Logger LOGGER = Logger.getLogger(MybatisBuilder.class);
	
	public static void main(String[] args) {
		try {
			System.setProperty("generated.source.dir","");
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = false;
			File configFile = new File("build/generatorConfig.xml");
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			LOGGER.error("creatbean error:" + e.getMessage(), e);
		}
	}
}
