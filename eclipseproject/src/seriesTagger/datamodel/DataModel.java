package seriesTagger.datamodel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataModel {
	public static class Episode {
		public int episode_number;
		public int season_number;
		public String title;
		public String series_name;
		public String file;
		public File filename;
		boolean saved;

		public String toString() {

			return series_name + " -  S" + season_number + "E" + episode_number
					+ " - " + title + " ------" + filename;
		}

		public Episode(int episode_number, int season_number, String title,
				String series_name) {
			this.episode_number = episode_number;
			this.season_number = season_number;
			this.title = title;
			this.series_name = series_name;
			this.file = null;
			this.saved = false;
		}

		public boolean isSaved() {
			return saved;
		}

		public Episode() {
		}

		public void save(String videosRootFolder) {
			// create folder structure, if necessary

			String newfile = videosRootFolder /* + "/" + this.series_name + "/" */
					+ "\\" + "S" + season_number + "E" + episode_number + " - "
					+ title;
			System.out.println(newfile);
			System.out.println(this.filename.getAbsolutePath());

			// Check if file exits at this place
			if (!this.filename.exists()) {
				System.out.println("datei existiert ueberhaupt gar nicht");
				return;
			}

			// check, if target file already exists
			File nf = new File(newfile);
			if (nf.exists()) {
				System.out.println("target existiert schon");
				return;
			}

			/*
			 * boolean b = this.filename.renameTo(nf); System.out.println(b);
			 * System.out.println(this.filename);
			 */
			Path source = Paths.get(this.filename.getAbsolutePath());
			Path target = Paths.get(nf.getAbsolutePath());
			try {

				System.out.println(source.toFile().exists());
				Files.move(source, target);
				System.out.println(source.toFile().getAbsolutePath() + " -> "
						+ target.toFile().getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			this.filename = target.toFile();
			this.saved = true;

		}

	}

	public List<File> fileList;
	public List<Episode> episodesList;

	public DataModel() {
		this.fileList = new ArrayList<File>();
		this.episodesList = new ArrayList<Episode>();
		this.episodesList.add(new Episode(1, 1, "the one with the pilot",
				"Friends"));
		this.episodesList.add(new Episode(1, 2, "the one with the second",
				"Friends"));
	}

	public void addFile(File f) {
		fileList.add(f);
	}

	/*
	 * macthes a file with an episode The file will be removed from the filelist
	 * and it will be added to the episode, int the episode list
	 */
	public void matchfile(File destination, Episode ep) {
		// check if f is in file list
		int x = fileList.indexOf(destination);
		assert (x != -1);
		fileList.remove(x);
		ep.filename = destination;
	}

	public static class EpguideSerie {
		public String title;
		public int tvrage;
	}

}
