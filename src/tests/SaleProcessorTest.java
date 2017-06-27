package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import message.Message;
import sale.SaleProcessor;


public class SaleProcessorTest {

	@Test
	public void addSimpleSaleTest() {
		SaleProcessor saleProcessor = new SaleProcessor();
		String msg = "1 app 4";
		Message.addNewMessage(msg);
		assertEquals(Message.getMessage(Message.getNextMessageId()),msg);
		saleProcessor.processMessage();
	}
	
	@Test
	public void addMultipleSalesTest() {
		SaleProcessor saleProcessor = new SaleProcessor();
		String msg = "2 2 app 4";
		Message.addNewMessage(msg);
		assertEquals(Message.getMessage(Message.getNextMessageId()),msg);
		saleProcessor.processMessage();
	}
	
	@Test
	public void addSalesOperationTest() {
		SaleProcessor saleProcessor = new SaleProcessor();
		String msg = "3 ADD app 4";
		Message.addNewMessage(msg);
		assertEquals(Message.getMessage(Message.getNextMessageId()),msg);
		saleProcessor.processMessage();
	}
	
	@Test
	public void saleValidationTest() {
		String msg = "4 ADD app 4";
		assertEquals(Message.addNewMessage(msg),"ERROR");
	}

}
