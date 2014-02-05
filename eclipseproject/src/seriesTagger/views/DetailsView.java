 
package seriesTagger.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import seriesTagger.AppConstants;
import seriesTagger.datamodel.DataModel.Episode;

public class DetailsView {
	private Text text;
	private Text text_1;
	@Inject private Logger logger; 
	
	@Inject
	public DetailsView() {
		//TODO Your code here
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		Composite snippetDetailsComposite = new Composite(parent, SWT.None);
		//We will use GridLayout with two columns.
		//The second argument to GridLayout constructor below
		//is a flag to indicate if columns should be of eual width
		snippetDetailsComposite.setLayout(new GridLayout(2, false));
		
		Label lblFile = new Label(snippetDetailsComposite, SWT.NONE);
		lblFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFile.setText("File:");
		
		text = new Text(snippetDetailsComposite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblEpisode = new Label(snippetDetailsComposite, SWT.NONE);
		lblEpisode.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEpisode.setText("Episode:");
		
		text_1 = new Text(snippetDetailsComposite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		logger.info("Details view created");
	}
	
	
	@Focus
	public void onFocus() {
		//TODO Your code here
	}
	
	@Inject @Optional
	public void onUnmatchedFilesSelected (@UIEventTopic(AppConstants.UNMATCHED_FILES_SELECTED) Object data)
	{
		if(data instanceof String){
			text.setText((String)data);
			text_1.setText("");
		}
		
	}
	
	@Inject @Optional
	public void onEpisodeSelected (@UIEventTopic(AppConstants.EPISODE_SELECTED) Object data)
	{
		System.out.println(data);
		if(data instanceof String){
			Episode e = (Episode) data;
			text_1.setText(e.toString());
			text.setText("");
		}
	}
	
}