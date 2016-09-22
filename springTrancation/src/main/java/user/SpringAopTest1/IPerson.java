package user.SpringAopTest1;

/**
 * Created by Jo on 8/26/16.
 */
public interface IPerson {
	public Name getName();
	public void setName(Name name);
	public void setAddress(String address);
	public String getAddress();

	public void say();
}