package seriesTagger.dnd;

import java.io.File;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

import seriesTagger.AppConstants;
import seriesTagger.datamodel.DataModel.Episode;
import seriesTagger.datamodel.ModelService;

public class MyDropListener1 extends ViewerDropAdapter {

	private final Viewer viewer;
	IEventBroker eventBroker;

	  public MyDropListener1(Viewer viewer, IEventBroker eventBroker) {
	    super(viewer);
	    this.viewer = viewer;
	    this.eventBroker=eventBroker;
	  }

	

	  // This method performs the actual drop
	  // We simply add the String we receive to the model and trigger a refresh of the 
	  // viewer by calling its setInput method.
	  @Override
	  public boolean performDrop(Object data) {
	    // ContentProviderTree.INSTANCE.getModel().add(data.toString());
	    // viewer.setInput(ContentProviderTree.INSTANCE.getModel());
		  Object target = getCurrentTarget();
		  File destination = new File( ( String) (data));
		  if(target instanceof Episode){
			  ModelService.getInstance().matchfile(destination,(Episode) target);
			  eventBroker.send(AppConstants.MATCH_FILE_EVENT, "test");
		  }
	    return false;
	  }

	  @Override
	  public boolean validateDrop(Object target, int operation,
	      TransferData transferType) {
	    return true;
	    
	  }


}
