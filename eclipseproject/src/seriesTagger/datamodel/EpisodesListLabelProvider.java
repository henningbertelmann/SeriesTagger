package seriesTagger.datamodel;

/* deprecated: to be replaced */

import org.eclipse.jface.viewers.LabelProvider;

import seriesTagger.datamodel.DataModel.Episode;

public class EpisodesListLabelProvider extends LabelProvider {
	
	
	@Override
	  public String getText(Object element) {
	    return element.toString();
	  }
}
