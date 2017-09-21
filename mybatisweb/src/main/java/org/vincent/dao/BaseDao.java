package org.vincent.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * 访问数据库基本Dao层实例，主要是获取到日志记录器，以及读取Mybatis配置文件并获取到sqlsession
 *
 * @ClassName: BaseDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年7月30日 上午10:54:53
 *
 */
public interface BaseDao {
	// 公共的日志记录器和配置文件配置在接口中。
	public Logger logger = Logger.getLogger(BaseDao.class);
	public String MybatisConfig = "mybatis/MybatisConfig.xml";

	// 打开一个Session
	default public SqlSession getSession() throws IOException {
		InputStream inputStream = null;
		inputStream = Resources.getResourceAsStream(BaseDao.MybatisConfig);

		return new SqlSessionFactoryBuilder().build(inputStream).openSession();
	}

	// 关闭一个Session
	default public void closeSession(SqlSession session) {
		if (session != null) {
			session.close();
		}
	}

	// 获取一个日志记录器
	default public Logger getLogger() {
		return BaseDao.logger;
	}

}
