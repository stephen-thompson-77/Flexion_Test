package thompson.flexion.entities;

import java.util.List;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import thompson.flexion.RequestHandler;

public class Developer implements Integration {
	
	private String developerId;
	private RequestHandler requestHandler;
	private Gson gson;
	
	private Developer(){
	}
	
	public Developer(String id, RequestHandler handler){
		setId(id);
		setRequestHandler(handler);
		this.gson = new Gson();
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
		return gson.fromJson(response, GamePurchase.class);
	}

	public void consume(Purchase arg0) {
		requestHandler.doPost("/" + this.developerId + "/consume/" + arg0.getId());
	}

	public List<Purchase> getPurchases() {
		String response = requestHandler.doGet("/" + this.developerId + "/all");
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(response);
		JsonArray jsonArr = jo.getAsJsonArray("purchases");
		List<Purchase> results = gson.fromJson(jsonArr, new TypeToken<List<GamePurchase>>(){}.getType());
		return results;
	}
}
