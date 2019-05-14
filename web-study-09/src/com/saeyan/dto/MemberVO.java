/*
 * 회원 정보를 하나의 묶음으로 관리하기 위해 나온 메커니즘이 자바 빈이라고 하였지만 이를 데이터베이스와 접목할 경우에는 VO(Value Object)라고 합니다.
 * VO 클래스 역시 자바 빈처럼 다음과 같이 속성(attribute), setter와 getter로 구성됩니다.
 * 1. 속성(attribute) : VO 클래스에 입력되는 정보를 저장합니다.
 * 2. setter 메소드 : 정보를 VO 클래스에 저장할 때 사용합니다.
 * 3. getter 메소드 : VO 클래스의 정보를 조회할 때 사용합니다.
 * 자바 빈 값만을 저장한다고 해서 Value Object라고 부르기도 하고 데이터를 전달하는 목적으로 사용된다고 해서 Data Transfer Object라고 부르기도 합니다.
 * 자바 빈, VO, DTO 3가지 용어가 같은 의미로 사용됩니다. 
 */

package com.saeyan.dto;

public class MemberVO {
	private String name;
	private String userid;
	private String pwd;
	private String email;
	private String phone;
	private int admin;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", email=" + email + ", phone="
				+ phone + ", admin=" + admin + "]";
	}
	
	
}
