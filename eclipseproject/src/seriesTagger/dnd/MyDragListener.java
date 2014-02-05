package seriesTagger.dnd;

import java.io.File;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;

public class MyDragListener implements DragSourceListener {
	 private final TableViewer viewer;

	public MyDragListener(TableViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("Start Drag");

	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		File firstElement = (File) selection.getFirstElement();
		if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
		      event.data = firstElement.toString(); 
		    }
		
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("Finshed Drag");

	}

}
