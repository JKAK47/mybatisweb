package org.vincent.mybatisweb;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.vincent.dao.BaseDao;
import org.vincent.dao.MutilTableQueryDaoImpl;
import org.vincent.model.Pojo;
import org.vincent.model.Product;

/**
 * 多表联合查询，
 *
 * @ClassName: MutilTableQuery
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年6月22日 下午11:14:26
 *
 */
public class MutilTableQueryTest {

	/**
	 * 基于接口方法实现数据库操作
	 *
	 * @Title: test
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void test() throws IOException {
		MutilTableQueryDaoImpl impl = new MutilTableQueryDaoImpl();
		List<Product> products = impl.queryProducts(1002);
		for (Product product : products) {
			BaseDao.logger.info(product);
			BaseDao.logger.info(product.getVendor().toString());
		}
		impl.closeSession();
	}

	/**
	 * 基于sqlsession 的selectList方法， 返回POJO类实例
	 *
	 * @Title: testA
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void testA() {
		MutilTableQueryDaoImpl impl = new MutilTableQueryDaoImpl();
		List<Pojo> pojos = impl.session.selectList(
				"org.vincent.model.IMutilQuery.sqlsession", 1002);
		for (Pojo pojo : pojos) {
			System.out.println(pojo);
		}

		impl.closeSession();
	}

	@Test
	public void testLineSplit() {
		StringBuilder sb = new StringBuilder("");
		sb.append("   aaa \n");
		sb.append("              bbb \n");
		sb.append("ccc  \n");
		sb.append("12\n");
		sb.append("vc\r"); // 不能识别这个
		sb.append("ddd\r\n");
		sb.append("314\r\n");
		sb.append("eee\n");
		String text = sb.toString();
		System.out.println("---Original---");
		System.out.print(text);

		System.out.println("---Split---");
		int count = 1;
		String[] lines = text.split("\\r?\\n?"); // \r 可有一次或者 无
		for (String line : lines) {
			System.out.print("line " + count++ + " : " + line);
			System.out.println("");
		}
		System.out.println("\\\\"); // 输出 \\
		// 手动构建模式文本 并轮询结果集
		Pattern p = Pattern.compile("\\Q$()*+.?[\\^{|\\E"); // 建立模式文本
		Matcher m = p.matcher("$()*+.?[\\^{|");// 待匹配文本
		while (m.find()) {
			System.out.println(m.group());
		}
		// 通过\Q \E 界定符来定义转义元字符
		p = Pattern.compile("\\Q$()*+.?[\\^{|\\E"); // 通过\Q 和 \E 两个界定符号来转义元字符

		System.out.println(p.matcher("$()*+.?[\\^{|").matches());

		// 想要匹配$()*+.?[\^{|
		// 通过手动转义形式构建模式文本
		p = Pattern.compile("\\$\\(\\)\\*\\+\\.\\?\\[\\\\\\^\\{\\|");

		System.out.println(p.matcher("$()*+.?[\\^{|").matches());

	}
}
