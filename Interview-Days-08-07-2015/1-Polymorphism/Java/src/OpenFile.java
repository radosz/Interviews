import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OpenFile {
	public List<String> open(String fileName) {
		String filePath = System.getProperty("user.dir")+"/"+fileName;
		Scanner reader;
		List<String> songs = null;
		try {
			reader = new Scanner(new File(filePath));
			songs = new ArrayList<String>();
			while (reader.hasNext()){
			    songs.add(reader.next().replace(",", ""));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songs;
	}
}
