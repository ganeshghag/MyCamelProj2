package com.ghag.rnd.integration.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.test.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

public class MyRouteTest extends CamelSpringTestSupport {
 
    @DirtiesContext
    @Test
    public void testMoveFile() throws Exception {
        String inputxml = "<person><city>Mumbai</city></person>";
        
        
        template.sendBodyAndHeader("file:src/data", inputxml, Exchange.FILE_NAME, "hello.xml");
		Thread.sleep(1000);
		
		//test is file has been created in destination
		File target = new File("target/messages/others/hello.xml");
		assertTrue("File not moved", target.exists());
		
		//test if content of file is OK
		String content = context.getTypeConverter().convertTo(String.class, target);
		assertEquals(inputxml, content);
    }
 

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(new String[] { 
		"classpath*:/META-INF/spring/camel-context.xml" });
	}

}
