package org.vincent.mybatisweb;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.vincent.dao.BaseDao;
import org.vincent.dao.StudentInterfaceDaoImpl;
import org.vincent.model.Student;

/*
 * ORM Mybatis 数据库单表  增 删  改 查操作的单元测试;
 * 该系列操作通过把SQL写在配置文件里面，
 * 然后利用SqlSession进行操作的
 * @ClassName: StudentSingleTableTestsqlsession
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年6月16日 上午1:10:46
 *
 */
public class StudentSingleTableTestsqlsession {
	/**
	 * 根据一个sqlStatement获取到一条记录；使用selcetOne 函数，通过
	 * sqlStatement字符串直接映射为Student中对应的SQL语句去执行查询。
	 *
	 * @Title: test
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void testSelectOne() throws IOException {
		// sqlStatement是根据Student.xml 这个mapper映射文件中namespace+ select
		// 的id=getStudentbyId 唯一确定的sql字符串
		String sqlStatement = "org.vincent.model.IStudentOperation.getStudentbyId";
		StudentInterfaceDaoImpl studentInterfaceDaoImpl = new StudentInterfaceDaoImpl();
		SqlSession session = studentInterfaceDaoImpl.getLocalsession();
		Student student = session.selectOne(sqlStatement, 20);
		BaseDao.logger.info(student);
		session.commit();
		session.close();
	}

	// 通过Id获取一条记录
	@Test
	public void getStudentbyIdTest() {
		StudentInterfaceDaoImpl studentInterfaceDaoImpl = new StudentInterfaceDaoImpl();
		// ① 获取session
		SqlSession session = studentInterfaceDaoImpl.getLocalsession();
		String sqlStatement = "org.vincent.model.IStudentOperation.getStudentbyId";
		BaseDao.logger.info(session.selectOne(sqlStatement, 34));
		session.commit();
		session.close();
	}

	/**
	 *
	 * 获取满足条件的一组条件
	 *
	 * @Title: testMutiStudent
	 * @Description: TODO(通过接口方法获取到数据库数据)
	 * @param
	 * @throws IOException
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void getStudentsTest() throws IOException {
		StudentInterfaceDaoImpl impl = new StudentInterfaceDaoImpl();
		SqlSession session = impl.getLocalsession();
		String sql = "org.vincent.model.IStudentOperation.getStudents";
		List<Student> students = session.selectList(sql, 20);
		for (Student student : students) {
			BaseDao.logger.info(student);
		}
		session.commit();
		session.close();
	}

	/**
	 * 插入一条数据
	 *
	 * @Title: insertStudentTest
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void insertStudentTest() throws IOException {
		Student student = new Student();
		student.setAddress("深圳市罗湖区红岭北");
		student.setAge(27);
		student.setName("babacc");
		/* Assert.assertEquals("XXYY", student.getName()); */
		StudentInterfaceDaoImpl impl = new StudentInterfaceDaoImpl();
		SqlSession session = impl.getLocalsession();
		String sql = "org.vincent.model.IStudentOperation.insertStudent";
		session.insert(sql, student);
		BaseDao.logger.info(student);
		session.commit();
		session.close();
	}

	/**
	 * 通过id查询一条记录对应的对象，然后更改记录对象，最后通过接口方法更新一条记录
	 *
	 * @Title: updateStudent
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @throws IOException
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void updateStudent() throws IOException {
		StudentInterfaceDaoImpl impl = new StudentInterfaceDaoImpl();
		SqlSession session = impl.getLocalsession();
		String sqlStatement = "org.vincent.model.IStudentOperation.getStudentbyId";
		Student student = session.selectOne(sqlStatement, 64);
		BaseDao.logger.info(student);
		String update = "org.vincent.model.IStudentOperation.updateStudent";
		student.setAddress("旭飞华天苑A座");
		session.update(update, student);
		session.commit();
		session.close();
	}

	/**
	 * 通过数据库id， 删除一条记录。
	 *
	 * @Title: deleteStudent
	 * @Description: TODO
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void deleteStudent() throws IOException {
		StudentInterfaceDaoImpl dao = new StudentInterfaceDaoImpl();
		SqlSession session = dao.getLocalsession();
		String delete = "org.vincent.model.IStudentOperation.deleteStudent";
		session.delete(delete, 38);
		session.commit();
		session.close();
	}

	@Test
	public void fuzzyQuery() {
		StudentInterfaceDaoImpl dao = new StudentInterfaceDaoImpl();
		SqlSession session = dao.getLocalsession();
		String delete = "org.vincent.model.IStudentOperation.fuzzyQuery";
		List<Student> students = session.selectList(delete, "晓");
		for (Student student : students) {
			BaseDao.logger.info(student);
		}
		session.commit();
		session.close();
	}
}
