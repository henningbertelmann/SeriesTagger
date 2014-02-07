package seriesTagger.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.swt.modeling.EMenuService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import seriesTagger.AppConstants;
import seriesTagger.datamodel.DataModel.Episode;
import seriesTagger.datamodel.EpisodesListContentProvider;
import seriesTagger.datamodel.ModelService;
import seriesTagger.datamodel.EpisodesListLabelProvider;
import seriesTagger.dnd.MyDropListener;

public class SeriesView {

	public TreeViewer treeViewer;
	private static String EPISODE_AT_MOUSE_CLICK = "episode_at_mouse_click";

	
	@PostConstruct
	public void postConstruct(Composite parent, final IEventBroker eventBroker,
			EMenuService menuService, final IEclipseContext ctx) {
		Composite seriesComposite = new Composite(parent, SWT.None);
		// We will use GridLayout with two columns.
		// The second argument to GridLayout constructor below
		// is a flag to indicate if columns should be of eual width
		seriesComposite.setLayout(new FillLayout());

		treeViewer = new TreeViewer(seriesComposite, SWT.BORDER);
		treeViewer.setContentProvider(new EpisodesListContentProvider());
		treeViewer.setLabelProvider(new EpisodesListLabelProvider());
		treeViewer.setInput(ModelService.getInstance().episodesList);
		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
		treeViewer.addDropSupport(operations, transferTypes,
				new MyDropListener(treeViewer, eventBroker));
		Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		tree.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer
						.getSelection();
				Episode ep = (Episode) selection.getFirstElement();
				eventBroker.send(AppConstants.EPISODE_SELECTED, ep);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

		});

		treeViewer.getTree().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("MouseEvent");
				if (e.button == 1)
					return; // Ignore if left mouse click
				TreeItem itemAtClick = treeViewer.getTree().getItem(
						new Point(e.x, e.y));

				if (itemAtClick != null) {
					Object o = itemAtClick.getData();
					ctx.set(EPISODE_AT_MOUSE_CLICK, o);
				} else {
					ctx.remove(EPISODE_AT_MOUSE_CLICK);
				}
			}
		});
	}

	@Focus
	public void onFocus() {
		// TODO Your code here
	}

	@Inject
	@Optional
	public void onFileMatched(
			@UIEventTopic(AppConstants.MATCH_FILE_EVENT) Object data) {
		System.out.println(data);
		treeViewer.setInput(ModelService.getInstance().episodesList);

	}

	@Inject
	@Optional
	public void onEpisodeListLoaded(
			@UIEventTopic(AppConstants.EPISODE_LIST_LOADED) Object data) {
		System.out.println(data);
		treeViewer.setInput(ModelService.getInstance().episodesList);

	}

}