package NewGeneration.TooSmall;

/**
 * Created by Jo on 1/4/17.
 */
public class GCDataObject {
	byte[] bytes = null;

	RefObject object = null;

	public GCDataObject(int factor){
		bytes = new byte[factor*1024];
		object = new RefObject();
	}
}
