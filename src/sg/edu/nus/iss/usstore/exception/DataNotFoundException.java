package sg.edu.nus.iss.usstore.exception;

public class DataNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String dataType, String dataId) {
		super(dataType + " " + dataId + " not exist");
	}
}
