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
		}

		public Episode() {
		}

	}

	public static class Season {
		int number;
		List<Episode> episodesList;

	}

	public static class Series {
		public String name;
		public List<Season> seasonsList;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static List<Series> exampleList() {
		Episode e1 = new Episode();
		e1.episode_number = 1;
		e1.title = "Die erste Episode";
		Episode e2 = new Episode();
		e2.episode_number = 2;
		e2.title = "Die erste Episode";
		Season s1 = new Season();
		s1.number = 1;
		s1.episodesList = new ArrayList<Episode>();
		s1.episodesList.add(e1);
		s1.episodesList.add(e2);
		Series bigbang = new Series();
		bigbang.name = "Big Bang Theory";
		bigbang.seasonsList = new ArrayList<>();
		bigbang.seasonsList.add(s1);

		Episode e3 = new Episode();
		e3.episode_number = 1;
		e3.title = "Die erste Episode";
		Episode e4 = new Episode();
		e4.episode_number = 2;
		e4.title = "Die erste Episode";
		Season s2 = new Season();
		s2.episodesList = new ArrayList<Episode>();
		s2.episodesList.add(e3);
		s2.episodesList.add(e4);
		bigbang.seasonsList.add(s2);

		Series howIMetYourMother = new Series();
		howIMetYourMother.name = "How I met your mother";
		howIMetYourMother.seasonsList = new ArrayList<>();
		howIMetYourMother.seasonsList.add(s1);
		howIMetYourMother.seasonsList.add(s2);

		ArrayList<Series> ret = new ArrayList<>();
		ret.add(howIMetYourMother);
		ret.add(bigbang);
		return ret;
	}

	public List<File> fileList;
	public List<Series> seriesList;
	public List<Episode> episodesList;

	public DataModel() {
		this.fileList = new ArrayList<File>();
		this.seriesList = exampleList();
		this.episodesList = new ArrayList<Episode>();
		this.episodesList.add(new Episode(1, 1, "the one with the pilot",
				"Friends"));
		this.episodesList.add(new Episode(1, 2, "the one with the second",
				"Friends"));
		// develop begonnen
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
