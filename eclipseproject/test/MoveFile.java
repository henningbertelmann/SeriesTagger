import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoveFile {

	public static void main(String[] args) {
		moveWithFiles();
	}
	
	public static void moveWithFiles() {
		File f = new File("C:\home\mboeschen\git\SeriesTaggerRepo\test\data\2.Broke.Girls.S01E15.HDTV.XviD-LOL.[VTV].avi")
		System.out.println(f.exists());
		// Path source = Paths.get(this.filename.getAbsolutePath());
	}
	
	public static void moveSimple() {
		File f = new File("C:\\Users\\mboeschen\\test.py");
		f.renameTo(new File("C:\\Users\\mboeschen\\programming\\test.py"));
	}

}
