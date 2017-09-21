package org.vincent.model;

public class Vendor {

	private int vend_id;
	private String vend_name;
	private String vend_address;
	private String vend_city;
	private String vend_state;
	private String vend_zip;
	private String vend_country;

	@Override
	public String toString() {
		return "Vendor [vend_id=" + this.vend_id + ", vend_name="
				+ this.vend_name + ", vend_adderss=" + this.vend_address
				+ ", vend_city=" + this.vend_city + ", vend_state="
				+ this.vend_state + ", vend_zip=" + this.vend_zip
				+ ", vend_country=" + this.vend_country + "]";
	}

	public int getVend_id() {
		return this.vend_id;
	}

	public void setVend_id(int vend_id) {
		this.vend_id = vend_id;
	}

	public String getVend_name() {
		return this.vend_name;
	}

	public void setVend_name(String vend_name) {
		this.vend_name = vend_name;
	}

	public String getVend_adderss() {
		return this.vend_address;
	}

	public void setVend_adderss(String vend_address) {
		this.vend_address = vend_address;
	}

	public String getVend_city() {
		return this.vend_city;
	}

	public void setVend_city(String vend_city) {
		this.vend_city = vend_city;
	}

	public String getVend_state() {
		return this.vend_state;
	}

	public void setVend_state(String vend_state) {
		this.vend_state = vend_state;
	}

	public String getVend_zip() {
		return this.vend_zip;
	}

	public void setVend_zip(String vend_zip) {
		this.vend_zip = vend_zip;
	}

	public String getVend_country() {
		return this.vend_country;
	}

	public void setVend_country(String vend_country) {
		this.vend_country = vend_country;
	}

}
