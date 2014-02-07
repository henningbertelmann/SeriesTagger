 
package seriesTagger.views;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import seriesTagger.AppConstants;
import seriesTagger.datamodel.DataModel;
import seriesTagger.datamodel.ModelService;
import seriesTagger.dnd.MyDragListener;

public class UnmatchedFilesPart {
	private TableViewer tableViewer;
	
	@Inject
	public UnmatchedFilesPart() {
		//TODO Your code here
	}
	
	@PostConstruct
	public void postConstruct(Composite parent,final IEventBroker eventBroker) {
		Composite unmatchedFilesComposite = new Composite(parent, SWT.None);
		//We will use GridLayout with two columns.
		//The second argument to GridLayout constructor below
		//is a flag to indicate if columns should be of eual width
		unmatchedFilesComposite.setLayout(new FillLayout());
		
		tableViewer = new TableViewer(unmatchedFilesComposite, SWT.BORDER | SWT.V_SCROLL);
		Table table = tableViewer.getTable();
		table.setLayoutData(new RowData(443, 293));
		
		 int operations = DND.DROP_COPY| DND.DROP_MOVE;
		 Transfer[] transferTypes = new Transfer[]{TextTransfer.getInstance()};
		 tableViewer.addDragSupport(operations, transferTypes , new MyDragListener(tableViewer));
		
		tableViewer.setContentProvider(new IStructuredContentProvider() {
			
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
			
			@Override
			public void dispose() {
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof DataModel)
				{
					System.out.println("test");
					return ((DataModel)inputElement).fileList.toArray();
				}
				
				return new Object[]{};
			}
		});
		tableViewer.setInput(ModelService.getInstance().fileList);
		
		tableViewer.getTable().addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				File f = (File) selection.getFirstElement();
				eventBroker.send(AppConstants.UNMATCHED_FILES_SELECTED, f);
				System.out.println(f);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	
	@Focus
	public void onFocus() {
		//TODO Your code here
	}
	
	@Inject @Optional
	public void onNewFilesAdded (@UIEventTopic(AppConstants.NEW_FILES_EVENT) Object data)
	{
		System.out.println(data);
		tableViewer.setInput(ModelService.getInstance());
		
	}
	
	@Inject @Optional
	public void onFileMatched (@UIEventTopic(AppConstants.MATCH_FILE_EVENT) Object data)
	{
		System.out.println(data);
		tableViewer.setInput(ModelService.getInstance());
		
	}
	
	
}