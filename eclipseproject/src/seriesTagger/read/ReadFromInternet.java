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
import seriesTagger.datamodel.DataModel.Series;
import seriesTagger.datamodel.SeriesTree;
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

	public static List<Series> readfromInternet() {
		URL url;
		URL SingleSeriesUrl;
		URLConnection conn2;

		ArrayList<Series> seriesList = new ArrayList<Series>();

		try {
			// get URL content

			String a = "http://epguides.com/common/allshows.txt";
			url = new URL(a);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				String[] ser = inputLine.split(",");
				Series s = new Series();
				s.name = ser[0];
				if (ser.length >= 2 && ser[2].matches("\\d+")) {
					int tvrage = Integer.parseInt(ser[2]);
					if (tvrage == 8511 || tvrage == 3918 || tvrage == 6454
							|| tvrage == 28416) {
						seriesList.add(s);
						String b = "http://epguides.com/common/exportToCSV.asp?rage="
								+ tvrage;
						System.out.println(b);
						SingleSeriesUrl = new URL(b);
						conn2 = url.openConnection();
						BufferedReader br2 = new BufferedReader(
								new InputStreamReader(conn2.getInputStream()));
						String inputLine2;
						while ((inputLine2 = br.readLine()) != null) {
							String[] ser2 = inputLine.split(",");

						}

					}
				}
			}
			br.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return seriesList;

	}

	public static SeriesTree readfromInternet2() {
		URL url;
		URL SingleSeriesUrl;
		URLConnection conn2;

		SeriesTree seriesList = new SeriesTree();

		try {
			// get URL content

			String a = "http://epguides.com/common/allshows.txt";
			url = new URL(a);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				String[] ser = inputLine.split(",");
				String name = ser[0];
				if (ser.length >= 2 && ser[2].matches("\\d+")) {
					int tvrage = Integer.parseInt(ser[2]);
					if (tvrage == 8511 || tvrage == 3918 || tvrage == 6454
							|| tvrage == 28416) {
						String b = "http://epguides.com/common/exportToCSV.asp?rage="
								+ tvrage;
						SingleSeriesUrl = new URL(b);
						conn2 = SingleSeriesUrl.openConnection();
						BufferedReader br2 = new BufferedReader(
								new InputStreamReader(conn2.getInputStream()));
						String inputLine2;
						while ((inputLine2 = br2.readLine()) != null) {
							String[] ser2 = inputLine2.split(",");
							if (ser2.length >= 6 && ser2[0].matches("\\d+")
									&& ser2[1].matches("\\d+")) {
								seriesList.addEpisode(name,
										Integer.parseInt(ser2[1]),
										Integer.parseInt(ser2[2]), ser2[6]);
							}

						}

					}
				}
			}
			br.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return seriesList;

	}

}
