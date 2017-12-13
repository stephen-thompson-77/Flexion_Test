package thompson.flexion;

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

import thompson.flexion.entities.Developer;

public class App 
{
    public static void main( String[] args )
    {
    	//Run tests
    	IntegrationTestRunner runner = new IntegrationTestRunner();
    	runner.runTests(new Developer("232", new RequestManager()));
    }
}
