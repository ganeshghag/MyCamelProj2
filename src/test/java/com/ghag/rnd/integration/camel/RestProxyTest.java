package com.ghag.rnd.integration.camel;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

public class RestProxyTest extends CamelSpringTestSupport {
 
    @DirtiesContext
    @Test
    public void testPing() throws Exception {
        
    	//setup test conditions
    	MockEndpoint mock = getMockEndpoint("mock:quote");
    	mock.expectedMessageCount(1);
    	
    	//send message
        template.sendBody("jetty:http://localhost:9999/restproxy",null);
		
		//assert
        mock.assertIsSatisfied();
        
        
        List<Exchange> list = mock.getReceivedExchanges();
        String body1 = list.get(0).getIn().getBody(String.class);
        //System.out.println("body="+body1);
        //System.out.println("body="+ list.get(0));
        //System.out.println("body="+ list.get(0).getIn());
        assertTrue("Body does not contain ...",body1.contains("Jupiter Hospital"));
 
    }
    
 

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(new String[] { 
		"classpath*:/META-INF/spring/restproxy-camel-context.xml" });
	}

}
