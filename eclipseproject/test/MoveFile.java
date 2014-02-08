import java.io.File;

public class MoveFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\mboeschen\\test.py");
		f.renameTo(new File("C:\\Users\\mboeschen\\programming\\test.py"));

	}

}
