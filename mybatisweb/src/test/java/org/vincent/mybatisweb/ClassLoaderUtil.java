package org.vincent.mybatisweb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.UUID;

import org.apache.log4j.Logger;

public class ClassLoaderUtil {
	private static Logger log = Logger.getLogger(ClassLoaderUtil.class);

	/**
	 * Thread.currentThread().getContextClassLoader().getResource("")
	 */

	/**
	 * 加载Java类。 使用全限定类名字符串加载一个类。
	 *
	 * @paramclassName
	 * @return
	 */
	public static Class loadClass(String className) {
		try {
			return ClassLoaderUtil.getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("class not found '" + className + "'", e);
		}
	}

	/**
	 * 得到类加载器
	 *
	 * @return
	 */
	private static ClassLoader getClassLoader() {

		return ClassLoaderUtil.class.getClassLoader();
	}

	/**
	 * 提供相对于classpath的资源路径，返回文件的输入流
	 *
	 * @paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
	 * @return 文件输入流
	 * @throwsIOException
	 * @throwsMalformedURLException
	 */
	public static InputStream getStream(String relativePath)
			throws MalformedURLException, IOException {
		if (!relativePath.contains("../")) {// 在classpath下面可以直接使用JDK的API.
			return ClassLoaderUtil.getClassLoader().getResourceAsStream(
					relativePath);

		} else {
			return ClassLoaderUtil.getStreamByExtendResource(relativePath);
		}

	}

	/**
	 *
	 * @paramurl
	 * @return
	 * @throwsIOException
	 */
	private static InputStream getStreamFromURL(URL url) throws IOException {
		if (url != null) {
			return url.openStream();
		} else {
			return null;
		}
	}

	/**
	 *
	 * @paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
	 * @return
	 * @throwsMalformedURLException
	 * @throwsIOException
	 */
	private static InputStream getStreamByExtendResource(String relativePath)
			throws MalformedURLException, IOException {
		return ClassLoaderUtil.getStreamFromURL(ClassLoaderUtil
				.getExtendResource(relativePath));

	}

	/**
	 * 提供相对于classpath的资源路径，返回属性对象，它是一个散列表
	 *
	 * @paramresource
	 * @return
	 */
	public static Properties getProperties(String resource) {
		Properties properties = new Properties();
		try {
			properties.load(ClassLoaderUtil.getStream(resource));
		} catch (IOException e) {
			throw new RuntimeException("couldn't load properties file '"
					+ resource + "'", e);
		}
		return properties;
	}

	/**
	 * 得到本Class所在的ClassLoader的Classpath 的绝对路径。 URL形式的
	 *
	 * @return
	 */
	public static String getAbsolutePathOfClassLoaderClassPath() {
		ClassLoaderUtil.log.info(ClassLoaderUtil.getClassLoader()
				.getResource("").toString());
		return ClassLoaderUtil.getClassLoader().getResource("").toString();// java工程
																			// classpath变量的字符串形式
	}

	/**
	 *
	 * @param relativePath
	 *            必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用. ./来查找
	 *            直接以../a/b 形式给出 /a/b/c形式给出会切换为a/b/c 形式。
	 * @return 资源的绝对URL
	 * @throws MalformedURLException
	 */
	private static URL getExtendResource(String relativePath)
			throws MalformedURLException {

		ClassLoaderUtil.log.info("传入的相对路径：" + relativePath);
		// ClassLoaderUtil.log.info(Integer.valueOf(relativePath.indexOf("../")))
		// ;
		if (!relativePath.contains("../")) {
			return ClassLoaderUtil.getResource(relativePath);

		}
		String classPathAbsolutePath = ClassLoaderUtil
				.getAbsolutePathOfClassLoaderClassPath();// 工程的classpath目录,
															// file:/
		if (relativePath.substring(0, 1).equals("/")) { // 路径以"/a/bsd/d"形式给出时候，去掉左边第一个斜杠。
			relativePath = relativePath.substring(1);
		}
		ClassLoaderUtil.log.info("最后一个../ : "
				+ Integer.valueOf(relativePath.lastIndexOf("../")));// 加入路径以
																	// "../sdf/df  或者../../../sdf  或者 sdf/df/dfdf/../"
		String wildcardString = relativePath.substring(0,
				relativePath.lastIndexOf("../") + 3);// 获取：最后一个../子串以前的字符串
		relativePath = relativePath
				.substring(relativePath.lastIndexOf("../") + 3);// ../子串以后的字符串。
		// '../'字符串个数
		int containSum = ClassLoaderUtil.containSum(wildcardString, "../");

		ClassLoaderUtil.log.info(" containSum = " + containSum);
		classPathAbsolutePath = ClassLoaderUtil.cutLastString(
				classPathAbsolutePath, "/", containSum);
		String resourceAbsolutePath = classPathAbsolutePath + relativePath;
		ClassLoaderUtil.log.debug("绝对路径：" + resourceAbsolutePath);
		URL resourceAbsoluteURL = new URL(resourceAbsolutePath);
		return resourceAbsoluteURL;
	}

	/**
	 * 计算dest 字符串在source字符串中包含多少个。
	 *
	 * @param source
	 * @param dest
	 * @return 返回出现的次数。
	 */
	private static int containSum(String source, String dest) {
		int containSum = 0;
		int destLength = dest.length();
		while (source.contains(dest)) {
			containSum = containSum + 1;
			source = source.substring(destLength);
		}
		return containSum;
	}

	/**
	 * 根据classpath 和路径分隔符 / ，和../个数。进行对classpath遍历；获取到相对应
	 *
	 * @param source
	 * @param dest
	 * @param num
	 * @return
	 */
	private static String cutLastString(String source, String dest, int num) {
		// String cutSource=null;
		int j;
		for (int i = 0; i < num; i++) {
			// 返回从从source.length()-2 开始，向左的匹配dest字符串的第一个位置下标。
			j = source.lastIndexOf(dest, source.length() - 2);
			source = source.substring(0, j + 1);

		}
		return source;
	}

	/**
	 * 相对于classpath路径
	 * 
	 * @param resource
	 * @return
	 */
	public static URL getResource(String resource) {

		if ("".equals(resource)) {
			ClassLoaderUtil.log.info("传入的相对于classpath的路径：空");
			return ClassLoaderUtil.getClassLoader().getResource(resource);
		} else {
			ClassLoaderUtil.log.info("传入的相对于classpath的路径：" + resource);
			return ClassLoaderUtil.getClassLoader().getResource(resource);
		}

	}

	/**
	 * @throws URISyntaxException
	 * @throws IOException
	 * @paramargs
	 * @throwsMalformedURLException
	 */
	public static void main(String[] args) throws URISyntaxException,
			IOException {
		// 项目为普通的maven javaee项目，log4j.properties文件放置于
		// "工程目录/src/main/resources"目录下；
		// ClassLoaderUtil.getExtendResource("../spring/dao.xml");
		// ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
		String name = "log4j.properties";
		System.out.println(ClassLoaderUtil.getExtendResource("AA"));// 合成后路径不存在。
		System.out.println(ClassLoaderUtil.getExtendResource(name));
		System.out
				.println("*********给出相对路径获取到合成的解决路径URL, 这个URL路径表示的文件资源可能不存在。***********");
		System.out.println(ClassLoaderUtil
				.getExtendResource("../../../src/log4j.properties"));
		System.out.println("---------相对于classpath的路径--------");
		System.out.println(ClassLoaderUtil.getClassLoader().getResource(name)
				.toURI().toString());
		System.out.println("66666666666666666666");
		System.out
				.println(ClassLoaderUtil
						.getExtendResource("../../src/main/resources/log4j.properties"));

		System.out
				.println("----------根据相对路径获取到相对于classpath路径的绝对路径，然后获取到该文件资源的输入流；inputStream-----------");
		/**
		 * classpath：file:/D:/Dev/WorkStation/JavaEEWorkstation/mybatisweb/
		 * target/test-classes/
		 * 给出一个相对于classpath路径的相对路径："../../src/main/resources/log4j.properties"
		 * 并获取到inputsteam输入流。
		 */
		System.out.println(ClassLoaderUtil
				.getStream("../../src/main/resources/log4j.properties"));
		UUID uuid = UUID.randomUUID();
		System.out.println(".{" + uuid.toString() + "}");
		File[] hiddenFiles = new File(".").listFiles(File::isHidden);
		for (File file : hiddenFiles) {
			System.out.println(file.toString());
		}
	}
}