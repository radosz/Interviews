import java.util.List;


public class QvkataDLG extends Singer {
	String  name = "QvkataDLG";
	
	public QvkataDLG() {
		// TODO Auto-generated constructor stub
		super.setName(name);
	}

	@Override
	List<String> songs() {
		// TODO Auto-generated method stub
		OpenFile  file = new OpenFile();
		return file.open("qvkata.txt");
	}

	@Override
	public String toString() {
		return "QvkataDLG [name=" + name + ", songs=" + songs() + "]";
	}
	

}
