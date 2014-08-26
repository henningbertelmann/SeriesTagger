package seriesTagger.handlers;


import javax.inject.Named;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import seriesTagger.AppConstants;


public class SavePlaceHandler extends AbstractHandler implements IHandler {

		@Execute
		public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
			

			// Directly standard selection
			DirectoryDialog dirDialog = new DirectoryDialog(shell);
			dirDialog.setText("Bestimmen Sie Ihren neuen Speicherplatz");
			String selectedDir = dirDialog.open();
			String path = dirDialog.getFilterPath();
			path = path.replace('\\','/');
			
			AppConstants.VIDEOS_ROOT_FOLDER = path;
		}
		
		

		@Override
		public Object execute(ExecutionEvent event) throws ExecutionException {
			// TODO Auto-generated method stub
			return null;
		}



	}
	

	
