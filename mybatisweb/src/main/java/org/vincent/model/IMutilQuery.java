package org.vincent.model;

import java.util.List;

public interface IMutilQuery {

	// 从多个表中查询数据获取到一组数据，
	public List<Product> queryProducts(int id);

}
