package thompson.flexion;

public interface RequestHandler {
	
	/**
	 * Handles get requests made by client.
	 * @param appendUrl - a built url for parameters
	 * @return json encoded response string
	 */
	public String doGet(String appendUrl);
	
	/**
	 * Handles post requests made by client.
	 * @param appendUrl - a built url for parameters
	 * @return json encoded response string
	 */
	public String doPost(String appendUrl);
	
}
