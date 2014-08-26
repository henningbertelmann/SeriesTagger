package seriesTagger.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
//import org.eclipse.ui.part.ViewPart;



import org.eclipse.ui.internal.Workbench;

import seriesTagger.AppConstants;
import seriesTagger.datamodel.DataModel.EpguideSerie;
import seriesTagger.datamodel.DataModel.Episode;
import seriesTagger.datamodel.EpguideSeriesContentProvider;
import seriesTagger.datamodel.EpguideSeriesLabelProvider;
import seriesTagger.datamodel.ModelService;
import seriesTagger.read.ReadFromInternet;


/* This Handler opens a Dialog that lets you choose your series. */
public class OpenSeriesHandler {

	



	@Execute
	public void execute(IEclipseContext context, Shell shell,
			IEventBroker eventBroker) throws InvocationTargetException,
			InterruptedException {
		final IEclipseContext pmContext = context.createChild();
		

		
//		List<EpguideSerie> ls = ReadFromInternet.getEpguideSeries();
//		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
//				shell, new EpguideSeriesLabelProvider(),
//				new EpguideSeriesContentProvider());
//		dialog.setInput(ls);
		
		List<EpguideSerie> ls = ReadFromInternet.getEpguideSeries(); 
//		Object[] ls = new String[]{"aa","bbb","cc","aaab"};

		//ElementListSelectionDialog dialog = new ElementListSelectionDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new LabelProvider());
		
		
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, new EpguideSeriesLabelProvider());
		
		dialog.setTitle("Choose Series!");
		
		dialog.setMessage("Select a String: ");
	
		dialog.setElements(ls.toArray());
		
//		dialog.setInitialSelections();
		
//		dialog.setFilter("");
		
		Object[] result = dialog.getResult(); 
		
	
		
		
		// user pressed cancel
		if (dialog.open() != 0) {
			return;
		}
//		Object[] result = dialog.getResult();
//		for (Object r : result) {
//			System.out.println(EpguideSeriesLabelProvider.SeriesToString(r));
//		}
		

		EpguideSerie e = (EpguideSerie) result[0];
		int tvrage = e.tvrage;

		// read now the corresponding episode form the internet
		List<Episode> ls1 = ReadFromInternet.getEpguidesEpisode2(tvrage);

		// put the data back
		ModelService.getInstance().episodesList = ls1;

		// inform others that the event list has been loaded
		eventBroker.send(AppConstants.EPISODE_LIST_LOADED, null);
		
		

	}

}