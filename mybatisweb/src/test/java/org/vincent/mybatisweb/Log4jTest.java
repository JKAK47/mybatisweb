package org.vincent.mybatisweb;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {
	private static final Logger Logger = Log4jTest.Logger
			.getLogger(Log4jTest.class);

	public static void main(String[] args) throws MalformedURLException,
			IOException {
		// 使用刚才定义的工具类获取配置文件的输入流，../../src/main/resources/log4j.properties进行了和classpath合成为一个在classpath外的绝对地址
		PropertyConfigurator.configure(ClassLoaderUtil
				.getStream("../../src/main/resources/log4j.properties"));
		// 没有和classpath合成为绝对地址，执行错误。
		// PropertyConfigurator.configure(ClassLoaderUtil
		// .getResource("../../src/main/resouces/log4j.properties"));
		System.out
				.println("---------------读取classpath下资源 log4j.properties----------------");

		System.out.println(ClassLoader.getSystemResource("log4j.properties"));
		PropertyConfigurator.configure(ClassLoader
				.getSystemResource("log4j.properties"));
		// 错误。PropertyConfigurator.configure("log4j.properties");

		// log4j 日志
		Log4jTest.Logger.setLevel(Level.WARN);
		Log4jTest.Logger.info("log4j 1.2.7 info");
		Log4jTest.Logger.error("晚安");
		Log4jTest.Logger.error("Vincent");
		Log4jTest.Logger.debug("debug msg");
		Log4jTest.Logger.trace("trace");
		Log4jTest.Logger.fatal("fatal msg");
		// Log4jTest.Logger.info("date : {}", new Date());
		System.out.println("---------------------------------------");
		System.out.println(Log4jTest.class.getResource(""));// 类Log4jTest.class文件的目录,URL形式

		// 获取到当前工程的classpath路径的绝对路径。六种方式。
		System.out.println(Log4jTest.class.getResource("/"));
		System.out.println(Thread.currentThread().getContextClassLoader()
				.getResource(""));
		System.out.println(Log4jTest.class.getClassLoader().getResource(""));
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(ClassLoader.getSystemClassLoader().getResource(""));
		System.out.println(Log4jTest.class.getClassLoader().getResource(""));

	}
}
