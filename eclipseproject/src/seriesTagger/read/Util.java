package seriesTagger.read;

import java.io.File;

import seriesTagger.AppConstants;
import seriesTagger.datamodel.DataModel.Episode;

public class Util {

	public int saveEpisode(Episode e) {

		// check if episode has already file associated
		File f = e.filename;
		if (f == null || !f.exists() || f.isDirectory()) {
			return 1;
		}

		// if (e.filename)
		return 0;
	}

	public static String constructFileName(Episode e) {
		String s = AppConstants.VIDEOS_ROOT_FOLDER;

		return "";

	}

}
