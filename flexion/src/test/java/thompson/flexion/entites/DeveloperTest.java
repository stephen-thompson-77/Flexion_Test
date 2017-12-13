package thompson.flexion.entites;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import thompson.flexion.RequestHandler;
import thompson.flexion.entities.Developer;
import thompson.flexion.entities.GamePurchase;

public class DeveloperTest {
	
	private Integration developer;
	private GamePurchase pOne;
	private GamePurchase pTwo;
	private List<Purchase> pListOne;
	private List<Purchase> pListTwo;
	private RequestHandler mockHandler;
	private String pOneBuyResponse = "{\"consumed\":\"false\",\"id\":\"TestId1\",\"itemId\":\"Test-Item-Id1\"}";
	private String pTwoBuyResponse = "{\"consumed\":\"false\",\"id\":\"TestId2\",\"itemId\":\"Test-Item-Id2\"}";
	private String pOneConsumeResponse = "{\"consumed\":\"true\",\"id\":\"TestId1\",\"itemId\":\"Test-Item-Id1\"}";
	private String pTwoConsumeResponse = "{\"consumed\":\"true\",\"id\":\"TestId2\",\"itemId\":\"Test-Item-Id2\"}";
	private String pListOneResponse = "{\"purchases\":[{\"consumed\":\"false\",\"id\":\"TestId1\",\"itemId\":\"Test-Item-Id1\"}]}";
	private String pListOneAndTwoResponse = "{\"purchases\":[{\"consumed\":\"false\",\"id\":\"TestId1\",\"itemId\":\"Test-Item-Id1\"},"
			+ "{\"consumed\":\"false\",\"id\":\"TestId2\",\"itemId\":\"Test-Item-Id2\"}]}";
	
	
	@Before
	public void setup(){
		mockHandler = Mockito.mock(RequestHandler.class); 
		developer = new Developer("GameDeveloper", mockHandler);
		
		pOne = new GamePurchase();
		pOne.setId("TestId1");
		pOne.setItemId("Test-Item-Id1");
		pOne.setConsumed(false);
		
		pTwo = new GamePurchase();
		pTwo.setId("TestId2");
		pTwo.setItemId("Test-Item-Id2");
		pTwo.setConsumed(false);
		
		pListOne = new ArrayList<Purchase>();
		pListOne.add(pOne);

		pListTwo = new ArrayList<Purchase>();
		pListTwo.add(pOne);
		pListTwo.add(pTwo);
	}
	
	@Test
	public void buySingleItemTest(){
		when(mockHandler.doPost(anyString())).thenReturn(pOneBuyResponse);
		Purchase p = developer.buy("TestId1");
		Assert.assertEquals(pOne, p);
	}
	
	@Test
	public void consumeSingleItemTest(){
		when(mockHandler.doPost(anyString())).thenReturn(pOneConsumeResponse);
		Assert.assertEquals(false, pOne.getConsumed());
		developer.consume(pOne);
		verify(mockHandler).doPost("/GameDeveloper/consume/" + pOne.getId());
	}

	@Test
	public void getAllPurchasesOneTest(){
		when(mockHandler.doPost(anyString())).thenReturn(pOneBuyResponse);
		when(mockHandler.doGet(anyString())).thenReturn(pListOneResponse);
		Purchase p = developer.buy("TestId1");
		List<Purchase> pList = developer.getPurchases();
		Assert.assertEquals(pListOne, pList);
	}
	
	@Test
	public void getAllPurchasesTwoTest(){
		when(mockHandler.doPost(anyString())).thenReturn(pOneBuyResponse);
		Purchase p1 = developer.buy("TestId1");
		when(mockHandler.doPost(anyString())).thenReturn(pTwoBuyResponse);
		when(mockHandler.doGet(anyString())).thenReturn(pListOneAndTwoResponse);
		Purchase p2 = developer.buy("TestId2");
		List<Purchase> pList = developer.getPurchases();
		Assert.assertEquals(pListTwo, pList);
	}
	
	@Test
	public void buyTwoItemsTest(){
		when(mockHandler.doPost(anyString())).thenReturn(pOneBuyResponse);
		Purchase p1 = developer.buy("TestId1");
		when(mockHandler.doPost(anyString())).thenReturn(pTwoBuyResponse);
		Purchase p2 = developer.buy("TestId2");
		Assert.assertEquals(pOne, p1);
		Assert.assertEquals(pTwo, p2);
	}
	
	@Test
	public void consumeTwoItemsTest(){
		when(mockHandler.doPost(anyString())).thenReturn(pOneConsumeResponse);
		Assert.assertEquals(false, pOne.getConsumed());
		when(mockHandler.doPost(anyString())).thenReturn(pTwoConsumeResponse);
		Assert.assertEquals(false, pTwo.getConsumed());
		developer.consume(pOne);
		developer.consume(pTwo);
		verify(mockHandler).doPost("/GameDeveloper/consume/" + pOne.getId());
		verify(mockHandler).doPost("/GameDeveloper/consume/" + pTwo.getId());
	}
	
}
