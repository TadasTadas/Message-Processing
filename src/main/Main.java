package main;
import java.util.Scanner;

import message.Message;
import sale.SaleProcessor;

/**
* <h1>Message Processing system</h1>
* System reads all input from console and prints all output to console.
* System accepts three type of messages. First digit in all messages indicates message type [1,2,3].
* Acceptable messages:
* 1 [product] [price]  e.g 1 apple 10
* 2 [occurrences count] [product] [price] e.g 2 5 apple 10
* 3 [operation{ADD,SUB,MULT}] [product] [price change] e.g 3 ADD apple 4
*
* @author  Tadas Poskus
* @version 1.0
* @since   2017-06-25
*/

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {
		
		SaleProcessor saleProcessor = new SaleProcessor();
		System.out.println("System accepts three type of messages. First digit indicates type of message [1,2,3]:");
		System.out.println("1 [product] [price]");
		System.out.println("2 [occurrences count] [product] [price]");
		System.out.println("3 [operation{ADD,SUB,MULT}] [product] [price change]");
		System.out.println("Please enter message:");
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		while (!line.equalsIgnoreCase("Stop")){
			if (Message.addNewMessage(line).equals("OK")){
				saleProcessor.processMessage();
				if (Message.getMessageCount() % 10 == 0){
					saleProcessor.printTempStatistic();
				}
				if (Message.getMessageCount() >= 50){
					
				}
			}
			else System.out.println("Incorrect message. Please provide correct message.");
			line = scanner.nextLine();
		}
		saleProcessor.printSales();
		saleProcessor.printSalesStatistic();
	}

}
