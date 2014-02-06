package seriesTagger.datamodel;

import org.eclipse.jface.viewers.LabelProvider;

import seriesTagger.datamodel.DataModel.EpguideSerie;

public class EpguideSeriesLabelProvider extends LabelProvider {
	public static String SeriesToString(Object element) {
		if (element instanceof EpguideSerie) {
			EpguideSerie series = (EpguideSerie) element;
			return series.title;
		} else {
			assert false;
		}
		return "";

	}

	@Override
	public String getText(Object element) {
		return SeriesToString(element);
	}

}
