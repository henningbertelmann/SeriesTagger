 
package seriesTagger.handlers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;

import seriesTagger.datamodel.DataModel.Episode;
import seriesTagger.AppConstants;

public class SaveHandler {
	@Execute
	public void execute(final IEclipseContext ctx) {
		Episode e = (Episode) ctx.get("episode_at_mouse_click");
		
		// check if root folder exists
		File f = new File(AppConstants.VIDEOS_ROOT_FOLDER);
		if(!f.exists()){
			System.out.println("Can't find the video root folder");
			System.out.println("Will not save!");
			return;
		}
		// check if folder for series exists
		String series = e.series_name;
		File seriesFolder=new File(f,series);
		if(!seriesFolder.exists()){
			System.out.println("Erstelle Ordner für Series folder");
			seriesFolder.mkdir();
			return;
		}
		
		
		System.out.println("Save Handler executed.");
	}
		
}