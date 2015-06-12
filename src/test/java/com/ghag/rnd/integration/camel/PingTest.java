package com.ghag.rnd.integration.camel;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

public class PingTest extends CamelSpringTestSupport {
 
    @DirtiesContext
    @Test
    public void testPing() throws Exception {
        
    	//setup test conditions
    	MockEndpoint mock = getMockEndpoint("mock:quote1");
    	mock.expectedMessageCount(1);
    	mock.expectedBodiesReceived("PONG-from ganesh ghag");
    	
    	//send message
        template.sendBody("jetty:http://localhost:9999/ping",null);
		
		//assert
        mock.assertIsSatisfied();
        
        
        List<Exchange> list = mock.getReceivedExchanges();
        String body1 = list.get(0).getIn().getBody(String.class);
        assertTrue("Body does not contain ganesh ghag",body1.contains("ganesh ghag"));
       
        
    }
    
 

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(new String[] { 
		"classpath*:/META-INF/spring/ping-camel-context.xml" });
	}

}
