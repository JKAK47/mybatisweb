package org.vincent.model;

public class Product {

	private String prod_id;
	private Vendor vendor;
	private String prod_name;
	private double prod_price;
	private String prod_desc;

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Product [prod_id=" + this.prod_id + ", vendor=" + this.vendor
				+ ", prod_name=" + this.prod_name + ", prod_price="
				+ this.prod_price + ", prod_desc=" + this.prod_desc + "]";
	}

	public String getProd_id() {
		return this.prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return this.prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public double getProd_price() {
		return this.prod_price;
	}

	public void setProd_price(double prod_price) {
		this.prod_price = prod_price;
	}

	public String getProd_desc() {
		return this.prod_desc;
	}

	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}

}
