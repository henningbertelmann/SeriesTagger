package seriesTagger.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import seriesTagger.AppConstants;
import seriesTagger.datamodel.DataModel.EpguideSerie;
import seriesTagger.datamodel.DataModel.Episode;
import seriesTagger.datamodel.DataModel.Series;
import seriesTagger.datamodel.EpguideSeriesContentProvider;
import seriesTagger.datamodel.EpguideSeriesLabelProvider;
import seriesTagger.datamodel.ModelService3;
import seriesTagger.read.ReadFromInternet;

/* This Handler opens a Dialog that lets you choose your series. */
public class OpenSeriesHandler {

	@Execute
	public void execute(IEclipseContext context, Shell shell,
			IEventBroker eventBroker) throws InvocationTargetException,
			InterruptedException {
		final IEclipseContext pmContext = context.createChild();

		List<Series> seriesList = ReadFromInternet.readfromInternet();
		List<EpguideSerie> ls = ReadFromInternet.getEpguideSeries();
		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				shell, new EpguideSeriesLabelProvider(),
				new EpguideSeriesContentProvider());
		dialog.setInput(ls);

		dialog.setTitle("Choose Series!");
		// user pressed cancel
		if (dialog.open() != 0) {
			return;
		}
		Object[] result = dialog.getResult();
		for (Object r : result) {
			System.out.println(EpguideSeriesLabelProvider.SeriesToString(r));
		}

		EpguideSerie e = (EpguideSerie) result[0];
		int tvrage = e.tvrage;

		// read now the corresponding episode form the internet
		List<Episode> ls1 = ReadFromInternet.getEpguidesEpisode2(tvrage);

		// put the data back
		ModelService3.getInstance().episodesList = ls1;

		// inform others that the event list has been loaded
		eventBroker.send(AppConstants.EPISODE_LIST_LOADED, null);

	}
}
