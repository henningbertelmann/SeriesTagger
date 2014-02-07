package seriesTagger.datamodel;

import java.io.File;
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
			this.saved=false;
		}

		public boolean isSaved() {
			return saved;
		}

		public Episode() {
		}
		
		public void save(String videosRootFolder){
			String newfile = videosRootFolder + "/" + this.series_name + "/"
		                     + "S" + season_number + "E" + episode_number
		 					+ " - " + title;
			System.out.println(newfile);
			System.out.println(this.filename.getAbsolutePath());
			this.filename.renameTo(new File(newfile));
			System.out.println(this.filename);
			
			
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
