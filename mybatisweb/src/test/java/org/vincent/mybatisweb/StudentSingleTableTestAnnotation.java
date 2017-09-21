package org.vincent.mybatisweb;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.vincent.dao.BaseDao;
import org.vincent.dao.StudentInterfaceDaoImpl;
import org.vincent.model.IStudentOperation;
import org.vincent.model.Student;

/*
 * ORM Mybatis 数据库单表  增 删  改 查操作的单元测试
 * 通过Mapper接口进行操作，其执行的相应SQL语句通过对应的注解获取。
 * @ClassName: StudentTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年6月16日 上午1:10:46
 *
 */
public class StudentSingleTableTestAnnotation {

	// 通过Id获取一条记录
	@Test
	public void getStudentbyIdTest() {
		StudentInterfaceDaoImpl studentInterfaceDaoImpl = new StudentInterfaceDaoImpl();
		// ① 通过接口方法注解执行sql查询
		SqlSession session = studentInterfaceDaoImpl.getLocalsession();
		IStudentOperation operation = session
				.getMapper(IStudentOperation.class);
		Student student = operation.getStudentbyId2(41);
		BaseDao.logger.info(student);
		session.commit();
		session.close();
	}

	/**
	 * 用接口的方式编程。这种方式，要注意的一个地方就是。在Student.xml 的映射配置文件中。 mapper节点的
	 * namespace="org.vincent.model.IStudentOperation" ，
	 * 命名空间非常重要，不能有错，必须与我们定义的接口全类名一致。如果不一致就会出错,
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
		IStudentOperation operation = session
				.getMapper(IStudentOperation.class);
		List<Student> list = operation.getStudents2(20);
		for (Student student : list) {
			System.out.println(student);
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
		student.setName("YYYY-MM-DD");
		StudentInterfaceDaoImpl impl = new StudentInterfaceDaoImpl();
		SqlSession session = impl.getLocalsession();
		IStudentOperation operation = session
				.getMapper(IStudentOperation.class);
		operation.insertStudent2(student);
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
		IStudentOperation operation = session
				.getMapper(IStudentOperation.class);

		Student student = operation.getStudentbyId2(68);
		student.setAddress("东莞市长安街");
		operation.updateStudent2(student);
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
		IStudentOperation operation = session
				.getMapper(IStudentOperation.class);
		operation.deleteStudent2(45);
		session.commit();
		session.close();
	}

	/**
	 * 通过名字模糊搜索，SQL语言通过注解提供 测试中发现一个问题就是注解中SQL语句在双引号中间不要有空白符。
	 * 
	 * @Title: fuzzyQueryTest
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void fuzzyQueryTest() {
		StudentInterfaceDaoImpl daoImpl = new StudentInterfaceDaoImpl();
		SqlSession session = daoImpl.getLocalsession();
		IStudentOperation operation = session
				.getMapper(IStudentOperation.class);
		List<Student> students = operation.fuzzyQuery2("马");
		for (Student student : students) {
			BaseDao.logger.info(student);
		}
		session.commit();
		session.close();
	}
}
