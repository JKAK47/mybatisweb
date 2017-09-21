package org.vincent.model;

/**
 * 数据库表对应的Java类；POJO对象
 *
 * @ClassName: Student
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author PengRong
 * @date 2017年6月16日 上午12:35:37
 *
 */
public class Student {
	private int id;
	private String address;
	private String name;
	private int age;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + this.id + ", address=" + this.address
				+ ", name=" + this.name + ", age=" + this.age + "]";
	}

}
