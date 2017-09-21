package org.vincent.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 只有接口没有实现类，用于Mybatis中查询结果 这里的接口方法名必须和Student.xml中select中id属性一致；
 * 比如getStudentbyId 必须和<select id="getStudentbyId" parameterType="int"
 * resultType="Student">一致。 不然测试时候报错说不能绑定异常：
 * org.apache.ibatis.binding.BindingException: Invalid bound statement (not
 * found): org.vincent.model.IStudentOperation.getStudent at
 * org.apache.ibatis.binding
 * .MapperMethod$SqlCommand.<init>(MapperMethod.java:225) at
 * org.apache.ibatis.binding.MapperMethod.<init>(MapperMethod.java:48) at
 * org.apache.ibatis.binding.MapperProxy.cachedMapperMethod(MapperProxy.java:65)
 * at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:58) at
 * com.sun.proxy.$Proxy2.getStudent(Unknown Source) at
 * org.vincent.mybatisweb.StudentTest.testMutiStudent(StudentTest.java:69) at
 * sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
 * sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 * at sun.reflect.DelegatingMethodAccessorImpl.invoke(
 * DelegatingMethodAccessorImpl.java:43) at
 * java.lang.reflect.Method.invoke(Method.java:498) at
 * org.junit.runners.model.FrameworkMethod$1
 * .runReflectiveCall(FrameworkMethod.java:45) at
 * org.junit.internal.runners.model
 * .ReflectiveCallable.run(ReflectiveCallable.java:15) at
 * org.junit.runners.model
 * .FrameworkMethod.invokeExplosively(FrameworkMethod.java:42) at
 * org.junit.internal
 * .runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20) at
 * org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:263) at
 * org.junit.runners
 * .BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:68) at
 * org.junit.
 * runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:47) at
 * org.junit.runners.ParentRunner$3.run(ParentRunner.java:231) at
 * org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:60) at
 * org.junit.runners.ParentRunner.runChildren(ParentRunner.java:229) at
 * org.junit.runners.ParentRunner.access$000(ParentRunner.java:50) at
 * org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:222) at
 * org.junit.runners.ParentRunner.run(ParentRunner.java:300) at
 * org.eclipse.jdt.internal
 * .junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50) at
 * org.eclipse
 * .jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38) at
 * org.eclipse
 * .jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner
 * .java:467) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(
 * RemoteTestRunner.java:683) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
 * .run(RemoteTestRunner.java:390) at
 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
 * .main(RemoteTestRunner.java:197)
 *
 *
 *
 * @ClassName: IStudentOperation
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年5月16日 上午11:51:04
 *
 */

// mybatis 面向接口映射接口，不用实现。
public interface IStudentOperation {
	// 查询一个记录
	public Student getStudentbyId(int id);

	@Select("select * from student where id=#{id}")
	public Student getStudentbyId2(int id);

	// 查询一组记录
	public List<Student> getStudents(int id);

	// 查询大于某一个id的所有记录
	@Select("	select * from student where id >#{id}")
	public List<Student> getStudents2(int id);

	// 插入一条记录
	public void insertStudent(Student student);

	@Insert("INSERT INTO student(name, age, address) VALUES(#{name}, #{age} ,#{address})")
	public void insertStudent2(Student student);

	// 更新一条记录
	public void updateStudent(Student student);

	@Update("update student SET name=#{name} , address=#{address} ,age=#{age} where id=#{id}")
	public void updateStudent2(Student student);

	// 删除一条记录
	public void deleteStudent(int id);

	@Delete("	delete from student where id =#{id}")
	public void deleteStudent2(int id);

	/**
	 * 模糊查询
	 */
	public List<Student> fuzzyQuery(String name);

	@Select("SELECT * FROM student where name like \"%\"#{name}\"%\"")
	public List<Student> fuzzyQuery2(String name);

}
