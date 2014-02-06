package seriesTagger.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import seriesTagger.datamodel.DataModel.EpguideSerie;
import seriesTagger.datamodel.DataModel.Series;
import seriesTagger.datamodel.EpguideSeriesContentProvider;
import seriesTagger.datamodel.EpguideSeriesLabelProvider;
import seriesTagger.read.ReadFromInternet;

/* This Handler opens a Dialog that lets you choose your series. */
public class OpenSeriesHandler {

	@Execute
	public void execute(IEclipseContext context, Shell shell)
			throws InvocationTargetException, InterruptedException {
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

		// read now the corresponding episode form the internet

		// put the data back
		/*
		 * for (String s : dialog.getFileNames()) {
		 * ModelService3.getInstance().addFile(new File(s)); }
		 * eventBroker.send(AppConstants.NEW_FILES_EVENT, null);
		 */

	}
}
