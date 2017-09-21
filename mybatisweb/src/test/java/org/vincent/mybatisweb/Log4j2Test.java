package org.vincent.mybatisweb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log4j2 实例：对应配置文件Log4j2.xml
 *
 * @ClassName: LoggerTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年7月11日 上午12:12:47
 *
 */
public class Log4j2Test {
	public static void main(String[] args) throws Exception {
		Logger logger = LogManager.getLogger();
		logger.trace("trace level");
		logger.debug("debug level");
		logger.info("info level");
		logger.warn("warn level");
		logger.error("error level");
		logger.fatal("fatal level");
		logger.debug("开始");
		System.out.println("你好");
		logger.debug("结束");
	}
}
