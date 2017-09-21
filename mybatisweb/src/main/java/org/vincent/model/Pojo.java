package org.vincent.model;

public class Pojo {
	String brand;
	String description;
	String name;
	double price;

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Pojo [brand=" + this.brand + ", description="
				+ this.description + ", name=" + this.name + ", price="
				+ this.price + "]";
	}

}
