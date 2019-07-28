package weixinkeji.vip.jweb.validate.tdata.entity;

import weixinkeji.vip.jweb.validate.ann.BindRegex;

public class Hello {
	
	@BindRegex(regex="int",error = "错误的主键")
	private Long id;
	
	@BindRegex(regex = "[0-9]{??1,??2}", placeholder = { "12", "22" }, alloyNull = false, error = "主键")
	private String helloId;
	@BindRegex(regex = "[a-z0-9]{??1,??2}", placeholder = { "12", "22" }, alloyNull = false, error = "helloName")
	private String helloName;
	
	@BindRegex("int")
	private int age = 1;
	
	@BindRegex(regex="double", alloyNull = false, error = "num2")
	private double num2;
	
	//找不到关联的正则表达式，同时本身也没有正则表达式，还设置为非null的：那么，只要非null只放行
	@BindRegex(error = "错误的orderId")
	private Integer orderId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHelloId() {
		return helloId;
	}

	public void setHelloId(String helloId) {
		this.helloId = helloId;
	}

	public String getHelloName() {
		return helloName;
	}

	public void setHelloName(String helloName) {
		this.helloName = helloName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getNum2() {
		return num2;
	}

	public void setNum2(double num2) {
		this.num2 = num2;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
