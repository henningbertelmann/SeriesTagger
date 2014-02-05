package seriesTagger.datamodel;

/* deprecated: to be replaced */

import org.eclipse.jface.viewers.LabelProvider;

import seriesTagger.datamodel.DataModel.Episode;
import seriesTagger.datamodel.DataModel.Season;
import seriesTagger.datamodel.DataModel.Series;

public class SeriesLabelProvider extends LabelProvider {
	
	public static String SeriesToString(Object element){
		if (element instanceof Series) {
		      Series series = (Series) element;
		      return series.getName();
		    }
		    else if(element instanceof Episode){
		    	return element.toString();
		    }
		    else if(element instanceof Season){
		    	Season season = (Season) element;
		    	return ("Season " + season.number);
		    }
		    else { assert false;}
		return "";
		
	}
	@Override
	  public String getText(Object element) {
	    return SeriesToString(element);
	  }
}
