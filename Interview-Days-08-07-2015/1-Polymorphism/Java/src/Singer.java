import java.util.List;


public abstract class Singer {
	
	private  String  name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	abstract List<String> songs(); 

}
