package seriesTagger.datamodel;

/* deprecated: to be deleted */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeriesTree {
	
	public HashMap<String,Series2> seriesList;
	
	public SeriesTree(){
		seriesList=new HashMap<String,Series2>();
	}
	
	public  class Episode2 {
		int episode_number;
		int season_number;
		String name;
		public Episode2(int episode_number, int season_number, String name) {
			this.episode_number = episode_number;
			this.season_number = season_number;
			this.name = name;
		}
		
	}
	
	public  class Series2 {
		public String name;
		public List<Episode2> episodesList;
		public Series2(String name) {
			episodesList = new ArrayList<Episode2>(); 
			this.name = name;
		} 
		
	}
	
	public void addEpisode(String seriesName, int Season, int Episode, String title){
		if(seriesList.containsKey(seriesName)){
			seriesList.get(seriesName).episodesList.add(new Episode2(Episode,Season,title));
		}
		else {
			seriesList.put(seriesName, new Series2(seriesName));
			addEpisode(seriesName, Season,Episode, title);
		}
	}
	
}
