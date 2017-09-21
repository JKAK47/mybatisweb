package org.vincent.mybatisweb;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vincent.dao.StudentInterfaceDaoImpl;
import org.vincent.model.Student;

/*
 * ORM Mybatis 数据库单表  增 删  改 查操作的单元测试
 * @ClassName: StudentTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年6月16日 上午1:10:46
 *
 */
public class StudentSingleTableTest {
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
	public void test() throws IOException {
		// sqlStatement是根据Student.xml 这个mapper映射文件中namespace+ select
		// 的id=getStudentbyId 唯一确定的sql字符串
		String sqlStatement = "org.vincent.model.IStudentOperation.getStudentbyId";
		StudentInterfaceDaoImpl studentInterfaceDaoImpl = new StudentInterfaceDaoImpl();
		System.out.println((studentInterfaceDaoImpl
				.queryBySql(sqlStatement, 61)));
		studentInterfaceDaoImpl.closeSession();
	}

	// 通过Id获取一条记录
	@Test
	public void getStudentbyIdTest() {
		StudentInterfaceDaoImpl studentInterfaceDaoImpl = new StudentInterfaceDaoImpl();
		// ① 通过接口方法，查询一条数据库 记录。
		Student student = studentInterfaceDaoImpl.getStudentbyId(2);
		System.out.println(student);
		studentInterfaceDaoImpl.closeSession();
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
		List<Student> list = impl.getStudents(20);
		for (Student student : list) {
			System.out.println(student);
		}
		impl.closeSession();
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
		student.setName("XXYY");
		Assert.assertEquals("XXYY", student.getName());
		StudentInterfaceDaoImpl impl = new StudentInterfaceDaoImpl();
		impl.insertStudent(student);
		System.out.println(student);// 能获取到数据库生成得id
		impl.closeSession();
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
		Student student = impl.getStudentbyId(60);
		student.setAddress("东莞市长安街");
		impl.updateStudent(student);
		impl.closeSession();
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
		dao.deleteStudent(59);
		dao.closeSession();
	}
}
