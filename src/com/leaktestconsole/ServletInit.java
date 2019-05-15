package com.leaktestconsole;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@WebListener
public class ServletInit implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("LeakTest context init");
		AmazonDynamoDBClientBuilder clientBuilder =
				AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_2);
		AmazonDynamoDB client = clientBuilder.build(); // Everything is fine until this line
		// It doesn't seem to matter whether or not client.shutdown() is called
		// client.shutdown();
		System.out.println("LeakTest context init end");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// It definitely did not work with client.shutdown() here
	}
}
