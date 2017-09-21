package org.vincent.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.vincent.model.IMutilQuery;
import org.vincent.model.Product;

/*
 * 多表查询，关联数据查询
 * @ClassName: MutilTableQuery
 * @Description: TODO()
 * @author PengRong
 * @date 2017年7月23日 下午3:23:46
 *
 */
public class MutilTableQueryDaoImpl implements BaseDao, IMutilQuery {
	public SqlSession session;

	public void closeSession() {
		this.closeSession(this.session);
	}

	public MutilTableQueryDaoImpl() {
		try {
			this.session = this.getSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 从两个表中查询数据
	@Override
	public List<Product> queryProducts(int id) {
		IMutilQuery query = this.session.getMapper(IMutilQuery.class);
		List<Product> products = query.queryProducts(id);
		return products;
	}

}
