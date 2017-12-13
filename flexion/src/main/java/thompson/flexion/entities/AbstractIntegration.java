package thompson.flexion.entities;

import java.util.List;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

public abstract class AbstractIntegration implements Integration {
	
	/**
	 * Performs multiple purchases.
	 * @param ids - a list of the item ids
	 * @return a list of the purchases made
	 */
	public List<Purchase> buyMuliple(List<String> ids){
		return null;
	}
	
	/**
	 * Consumes multiple items
	 * @param ids - a list of the item ids 
	 */
	public void consumeMultiple(List<Purchase> ids){
	}

}
