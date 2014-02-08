package seriesTagger.datamodel;

/* deprecated: to be replaced */

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import seriesTagger.datamodel.DataModel.Episode;

public class EpisodesListLabelProvider extends LabelProvider {

	private static final Image MATCHED = getImage("MATCHED.png");
	private static final Image UNMATCHED = getImage("UNMATCHED.png");
	private static final Image SAVED = getImage("SAVED.png");

	@Override
	public String getText(Object element) {
		return element.toString();
	}

	@Override
	public Image getImage(Object element) {
		Episode ep = (Episode) element;
		if (ep.filename == null)
			return UNMATCHED;
		if (ep.isSaved())
			return SAVED;
		return MATCHED;
	}

	// Helper Method to load the images
	private static Image getImage(String file) {
		Bundle bundle = FrameworkUtil
				.getBundle(EpisodesListLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();

	}
}
