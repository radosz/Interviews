import java.util.List;


public class Keranov extends Singer  {
	String  name = "Keranov";

	public Keranov(){
		super.setName(name);
	}
	@Override
	List<String> songs() {
		// TODO Auto-generated method stub
		OpenFile file =  new OpenFile();
		return file.open(this.name+".txt");
	}
	@Override
	public String toString() {
		return "Keranov [name=" + name + ", songs=" + songs() + "]";
	}
	

}
