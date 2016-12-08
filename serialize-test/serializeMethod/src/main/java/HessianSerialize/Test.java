package HessianSerialize;

/**
 * Created by Jo on 12/8/16.
 */
public class Test {
	public static void main(String[] args){
		Person person = new Person();
		person.setAge(24);
		person.setName("ltw");
		Serialize serialize = new Serialize();
		AntiSerialize antiSerialize = new AntiSerialize();
		Person person1 = antiSerialize.antiSer(serialize.ser(person));
		System.out.println(person1.getName());
		System.out.println(person1.getAge());
	}
}
