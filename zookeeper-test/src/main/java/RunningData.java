import java.io.Serializable;

/**
 * Created by Jo on 11/24/16.
 */
public class RunningData implements Serializable {

	private Long cid;

	private String name;

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
