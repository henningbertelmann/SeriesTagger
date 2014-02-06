package seriesTagger.datamodel;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import seriesTagger.datamodel.DataModel.EpguideSerie;

public class EpguideSeriesContentProvider implements ITreeContentProvider {

	private List<EpguideSerie> seriesList;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		this.seriesList = (List<EpguideSerie>) newInput;

	}

	@Override
	public Object[] getElements(Object inputElement) {
		this.seriesList = (List<EpguideSerie>) inputElement;
		return seriesList.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
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
