package thompson.flexion.entities;

import java.util.List;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import thompson.flexion.RequestHandler;

public class Developer implements Integration {
	
	private String developerId;
	private RequestHandler requestHandler;
	
	public Developer(String id, RequestHandler handler){
		setId(id);
		setRequestHandler(handler);
	}

	public String getId() {
		return developerId;
	}

	public void setId(String id) {
		this.developerId = id;
	}
	
	public RequestHandler getRequestHandler() {
		return requestHandler;
	}

	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	public Purchase buy(String arg0) {
		String response = requestHandler.doPost("/" + this.developerId + "/buy/" + arg0);
		
		return null;
	}

	public void consume(Purchase arg0) {
		// TODO Auto-generated method stub
	}

	public List<Purchase> getPurchases() {
		// TODO Auto-generated method stub
		return null;
	}
}
