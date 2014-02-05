package seriesTagger.datamodel;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import seriesTagger.datamodel.DataModel.Episode;

/*
 * Provides the content for the episode list on the right side.
 */

public class EpisodesListContentProvider implements ITreeContentProvider {
	
	private List<Episode> episodesList;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings("unchecked")
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		this.episodesList = (List<Episode>) newInput;

	}

	@Override
	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {
		this.episodesList = (List<Episode>) inputElement;
		return episodesList.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	@Override
	
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
