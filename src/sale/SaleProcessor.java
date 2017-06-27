package sale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import message.Message;

/**
* <h1>Sale processor</h1>
* This class reads new messages and processes them. It prints sales processing statistics.
*
* @author  Tadas Poskus
* @version 1.0
* @since   2017-06-25
*/

public class SaleProcessor {
	
	private void processModification(List<String> list){
		switch (list.get(1).toUpperCase()){
			case "ADD":
				Sales.addToCurrentSale(list.get(2), Integer.parseInt(list.get(3)));
				break;
			case "SUB":
				Sales.subtractCurrentSale(list.get(2), Integer.parseInt(list.get(3)));
				break;
			case "MULT":
				Sales.multiplyCurrentSale(list.get(2), Integer.parseInt(list.get(3)));
				break;
		}
	}
	
	public void processMessage(){
		int messageId = Message.getNextMessageId();
		List<String> list = new ArrayList<String>(Arrays.asList(Message.getMessage(messageId).split(" ")));
		 switch (list.get(0)){
		 	case "1":
		 		Sales.addSale(list.get(1), Integer.parseInt(list.get(2)));
		 		break;
		 	case "2":
		 		for (int i = 1; i <= Integer.parseInt(list.get(1));i++){
		 			Sales.addSale(list.get(2), Integer.parseInt(list.get(3)));
		 		}
		 		break;
		 	case "3":
		 		processModification(list);
		 		break;
		 }
		 Message.setMessageProcessed(messageId);
	}
	
	public void printSales(){
		Sales.printSales();
	}
	
	public void printSalesStatistic(){
		Sales.printSalesStatistic();
	}
	
	public void printTempStatistic(){
		Sales.printTempStatistic();
	}
}
