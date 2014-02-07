package seriesTagger.handlers;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;


import seriesTagger.AppConstants;
import seriesTagger.datamodel.ModelService;


public class OpenFilesHandler {

	@Execute
	public void execute(
			IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			IEventBroker eventBroker)
			throws InvocationTargetException, InterruptedException {
		FileDialog dialog = new FileDialog(shell,SWT.MULTI);
		dialog.open();
		for(String s : dialog.getFileNames()){
			ModelService.getInstance().addFile(new File(s));
		}
		eventBroker.send(AppConstants.NEW_FILES_EVENT, null);
		for(File f : ModelService.getInstance().fileList) System.out.println(f.toString());
	}
}
