package pojo;

import java.io.Serializable;

/**
 * Created by Jo on 8/12/16.
 */
public class User implements Serializable{

	private String userName;

	private String password;

	private String score;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override public String toString() {
		final StringBuffer sb = new StringBuffer("User{");
		sb.append("userName='").append(userName).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", score='").append(score).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
