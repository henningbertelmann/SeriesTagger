package seriesTagger.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import seriesTagger.datamodel.SeriesLabelProvider;
import seriesTagger.datamodel.SeriesListContentProvider;
import seriesTagger.datamodel.DataModel.Series;
import seriesTagger.read.ReadFromInternet;

public class OpenSeriesHandler {

	@Execute
	public void execute(
			IEclipseContext context,Shell shell)
			throws InvocationTargetException, InterruptedException {
		final IEclipseContext pmContext = context.createChild();

		/*
		System.out.println("save handler");
		MyDialog dialog = new MyDialog(shell);
		dialog.create();
		if (dialog.open() == 0) {
		  System.out.println("fertig");
		} 
		*/
		List<Series> seriesList = ReadFromInternet.readfromInternet();
		ElementTreeSelectionDialog dialog =
				new ElementTreeSelectionDialog(shell,new SeriesLabelProvider(), new SeriesListContentProvider());
		dialog.setInput(seriesList);
		
		dialog.setTitle("Choose Series!");
		// user pressed cancel
		if (dialog.open() != 0) {
		    return;
		}
		Object[] result = dialog.getResult(); 
		for (Object r: result ){
			System.out.println(SeriesLabelProvider.SeriesToString(r));
		}
		
	}
}
