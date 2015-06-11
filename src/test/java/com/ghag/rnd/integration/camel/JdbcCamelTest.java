package com.ghag.rnd.integration.camel;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

public class JdbcCamelTest extends CamelSpringTestSupport {
 
    @DirtiesContext
    @Test
    public void testJDBC() throws Exception {
        
    	//setup test conditions
    	MockEndpoint mock = getMockEndpoint("mock:quote");
    	mock.expectedMessageCount(1);
    	
    	//send message
        template.sendBody("jetty:http://localhost:9999/insertjdbc",null);
		
		//assert
        mock.assertIsSatisfied();
        
        
        List<Exchange> list = mock.getReceivedExchanges();
        String body1 = list.get(0).getIn().getBody(String.class);
        
        System.out.println("body="+body1);
        System.out.println("message with headers="+list.get(0).getIn().getHeaders());
        
        List<Map<String, Object>> generatedKeys = list.get(0).getIn().getHeader("CamelGeneratedKeysRows", List.class);
        Map<String, Object> row = generatedKeys.get(0);
        System.out.println("autokeys="+row);
        
    }
    
 

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(new String[] { 
		"classpath*:/META-INF/spring/jdbc-camel-context.xml" });
	}

}
