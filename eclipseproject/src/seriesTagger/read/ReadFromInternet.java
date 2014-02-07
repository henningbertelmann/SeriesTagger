package seriesTagger.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import seriesTagger.datamodel.DataModel.EpguideSerie;
import seriesTagger.datamodel.DataModel.Episode;
import au.com.bytecode.opencsv.CSVReader;

/*
 * Reads a list from series from the internet.
 */
public class ReadFromInternet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> ls = getEpguidesEpisode(8511);
		for (String s : ls)
			System.out.println(s);
	}

	/* Get a list from all the series catagolized at epguides.com */
	public static List<EpguideSerie> getEpguideSeries() {
		URL url;
		URLConnection conn = null;
		BufferedReader br = null;

		ArrayList<EpguideSerie> seriesList = new ArrayList<EpguideSerie>();
		String a = "http://epguides.com/common/allshows.txt";
		try {
			url = new URL(a);
			conn = url.openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] row = null;

		try {
			CSVReader csvReader = new CSVReader(br);
			List content = csvReader.readAll();

			for (Object object : content) {
				row = (String[]) object;
				EpguideSerie es = new EpguideSerie();
				try {
					es.title = row[0];
					es.tvrage = Integer.parseInt(row[2]);
					seriesList.add(es);
				} catch (Exception e) {

				}

			}
			// ...
			csvReader.close();
		} catch (Exception e) {

		}

		return seriesList;

	}

	public static List<String> getEpguidesEpisode(int tvrage) {
		URL url;
		URLConnection conn = null;
		BufferedReader br = null;

		ArrayList<String> seriesList = new ArrayList<String>();
		String a = "http://epguides.com/common/exportToCSV.asp?rage=" + tvrage;
		System.out.println(a);
		try {
			url = new URL(a);
			conn = url.openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] row = null;

		try {
			CSVReader csvReader = new CSVReader(br, ',', '"', 8);
			List content = csvReader.readAll();

			for (Object object : content) {
				row = (String[]) object;
				try {
					seriesList.add(row[5]);
				} catch (Exception e) {

				}

			}
			// ...
			csvReader.close();
		} catch (Exception e) {

		}

		return seriesList;
	}

	public static List<Episode> getEpguidesEpisode2(int tvrage) {
		URL url;
		URLConnection conn = null;
		BufferedReader br = null;

		ArrayList<Episode> episodeList = new ArrayList<Episode>();
		String a = "http://epguides.com/common/exportToCSV.asp?rage=" + tvrage;
		System.out.println(a);
		try {
			url = new URL(a);
			conn = url.openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] row = null;

		try {
			CSVReader csvReader = new CSVReader(br, ',', '"', 8);
			List content = csvReader.readAll();

			for (Object object : content) {
				row = (String[]) object;
				try {
					Episode e = new Episode();
					e.episode_number = Integer.parseInt(row[2]);
					e.season_number = Integer.parseInt(row[1]);
					e.title = row[5];
					e.series_name = "Default";
					episodeList.add(e);
				} catch (Exception e) {

				}

			}
			// ...
			csvReader.close();
		} catch (Exception e) {

		}

		return episodeList;
	}

	
}
