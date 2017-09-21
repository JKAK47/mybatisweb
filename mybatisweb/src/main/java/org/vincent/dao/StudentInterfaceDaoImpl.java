package org.vincent.dao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.vincent.model.IStudentOperation;
import org.vincent.model.Student;

import com.mysql.jdbc.StringUtils;

/**
 * Student 表的单表增删改查实现，基于接口
 *
 * @ClassName: StudentInterfaceDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年7月23日 上午11:46:00
 *
 */
public class StudentInterfaceDaoImpl implements BaseDao, IStudentOperation {
	private SqlSession Localsession = null;

	public SqlSession getLocalsession() {
		return this.Localsession;
	}

	public StudentInterfaceDaoImpl() {
		try {
			this.Localsession = this.getSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 获取到sqlSession
	}

	/**
	 * 关闭Session
	 *
	 * @Title: closeSession
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void closeSession() {
		this.closeSession(this.Localsession);
	}

	// 通关id和匹配到Student.xml中对应的select标签执行单表查询;没用通关Mapper接口
	public Student queryBySql(String sql, int id) {
		Student result = null;
		if (!StringUtils.isNullOrEmpty(sql)) {
			result = this.Localsession.selectOne(sql, id);
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * 通过一条记录的id根据IStudentOperation接口方法映射到sql mapper文件中一个select标签；获取到一条记录，
	 */
	@Override
	public Student getStudentbyId(int id) {
		// TODO Auto-generated method stub
		IStudentOperation operation = this.Localsession
				.getMapper(IStudentOperation.class);
		Student student = operation.getStudentbyId(id);
		BaseDao.logger.debug(student);
		return student;
	}

	// 获取到大于指定id的所有记录
	@Override
	public List<Student> getStudents(int id) {
		// TODO Auto-generated method stub
		IStudentOperation operation = this.Localsession
				.getMapper(IStudentOperation.class);
		// ② 通过接口方法获取一组数据库记录
		List<Student> list = operation.getStudents(id);
		return list;
	}

	/**
	 * 根据一个实例student，将对象插入到数据库中。
	 */
	@Override
	public void insertStudent(Student student) {
		// TODO Auto-generated method stub
		IStudentOperation operation = this.Localsession
				.getMapper(IStudentOperation.class);
		if (Objects.nonNull(student)) {
			operation.insertStudent(student);
		} else {
			//
		}
	}

	/**
	 * 更新一条记录的相关属性。
	 */
	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		IStudentOperation operation = this.Localsession
				.getMapper(IStudentOperation.class);
		operation.updateStudent(student);
	}

	/**
	 * 删除一条数据记录
	 */
	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		IStudentOperation operation = this.Localsession
				.getMapper(IStudentOperation.class);
		operation.deleteStudent(id);
	}

	@Override
	@Deprecated
	public Student getStudentbyId2(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public List<Student> getStudents2(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public void insertStudent2(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	@Deprecated
	public void updateStudent2(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	@Deprecated
	public void deleteStudent2(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Deprecated
	public List<Student> fuzzyQuery(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public List<Student> fuzzyQuery2(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
