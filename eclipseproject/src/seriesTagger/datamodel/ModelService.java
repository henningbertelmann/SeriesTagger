package seriesTagger.datamodel;

/* 
 * Provides the singleton instance of the data model.
 */
public class ModelService {
	private static DataModel modelService = new DataModel();
	public static DataModel getInstance(){
		return modelService;
	}

}
