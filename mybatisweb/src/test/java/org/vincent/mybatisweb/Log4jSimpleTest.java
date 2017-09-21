package org.vincent.mybatisweb;

import org.apache.log4j.Logger;

/**
 * Log4j 只要获取到Logger实例即可，不用获取到Log4j配置文件
 * 
 * @ClassName: Log4jSimpleTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年7月20日 下午11:54:50
 *
 */
public class Log4jSimpleTest {

	static Logger logger = Logger.getLogger(Log4jSimpleTest.class);

	public Log4jSimpleTest() {
		// TODO Auto-generated constructor stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Log4jSimpleTest.logger.debug("debug msg");
		Log4jSimpleTest.logger.info("info msg");
		Log4jSimpleTest.logger.warn("warn msg");
		Log4jSimpleTest.logger.error("error msg");
		Log4jSimpleTest.logger.fatal("fatal msg");
		Log4jSimpleTest.logger.trace("trace msg");
	}

}
