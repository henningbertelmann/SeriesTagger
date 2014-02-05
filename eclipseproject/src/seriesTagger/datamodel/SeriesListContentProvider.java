package seriesTagger.datamodel;

/* Provides the content in the dialog when choosing the series which goes into the episode list. */

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import seriesTagger.datamodel.DataModel.Season;
import seriesTagger.datamodel.DataModel.Series;

public class SeriesListContentProvider implements ITreeContentProvider {
	
	private List<Series> seriesList;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings("unchecked")
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.seriesList = (List<Series>) newInput;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {
		this.seriesList = (List<Series>) inputElement;
		return seriesList.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof Series){
			Series series = (Series) parentElement;
			if(series.seasonsList!=null)
				return series.seasonsList.toArray();
			else return (new Object[0]);
		}
		else if (parentElement instanceof Season){
			Season season = (Season) parentElement;
			if(season.episodesList!=null)
				return season.episodesList.toArray();
			else return (new Object[0]);
		}
		else
			return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof Series || element instanceof Season){
			return true;
		}
		return false;
	}

}