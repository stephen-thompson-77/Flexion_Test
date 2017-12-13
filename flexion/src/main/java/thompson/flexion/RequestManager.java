package thompson.flexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestManager implements RequestHandler {
	
	private final String BASE_URL = "http://sandbox.flexionmobile.com/javachallenge/rest/developer";
	
	public RequestManager(){
	}

	public String doGet(String appendUrl) {
		URL url = null;
		try {
			url = new URL(BASE_URL + appendUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return doRequest(url, "GET");
	}

	public String doPost(String appendUrl) {
		URL url = null;
		try {
			url = new URL(BASE_URL + appendUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return doRequest(url, "POST");
	}
	
	/**
	 * Handles requests.
	 * @param url - the url of the request
	 * @param requestMethod - the http verb of the request type
	 * @return the json response
	 */
	public String doRequest(URL url, String requestMethod){
		HttpURLConnection con = null;
		StringBuffer content = null;
		int status = 0;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(requestMethod);
			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			status = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
			String inputLine;
			content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				con.disconnect();
			}
		}
		if(status >= 200 && status < 300){
			return content.toString();
		}
		return "";
	}
}
