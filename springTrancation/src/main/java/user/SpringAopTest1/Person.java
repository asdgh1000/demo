package user.SpringAopTest1;

/**
 * Created by Jo on 8/26/16.
 */
public class Person implements IPerson {

	private String name2;
	public void setBeanName(String name) {
		System.out.println("set name aware");
		this.name2 = name;
	}
	private Name name;
	private String address;
	public Person() {
	}
	public Person(Name name, String address) {
		this.name = name;
		this.address = address;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		System.out.println("set name field");
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public void say(){
		System.out.println("say");
	}
}
