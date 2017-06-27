package message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* <h1>Message</h1>
* All message records are stored in this class. Message is validated according to message type.
*
* @author  Tadas Poskus
* @version 1.0
* @since   2017-06-25
*/

public final class Message {
	
	private static ArrayList<Record> messages = new ArrayList<Record>();
	
	private static class Record {
		
		private int id;
		private String message;
		private Status messageStatus;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Status getMessageStatus() {
			return messageStatus;
		}

		public void setMessageStatus(Status messageStatus) {
			this.messageStatus = messageStatus;
		}

	}
	
	private enum Status {
		NEW, 
		PROCESSED; 
	}
	
	private static Boolean validateMessage(String message){
		try{
			List<String> list = new ArrayList<String>(Arrays.asList(message.split(" ")));
			switch (list.get(0)){
		 	case "1":
		 		if (list.size() != 3){
		 			return false;
		 		}
		 		Integer.parseInt(list.get(2));
		 		break;
		 	case "2":
		 		if (list.size() != 4){
		 			return false;
		 		}
		 		Integer.parseInt(list.get(1));
		 		Integer.parseInt(list.get(3));
		 		break;
		 	case "3":
		 		if (!Arrays.stream(OperationsEnum.values()).anyMatch(e -> e.name().equals(list.get(1).toUpperCase()))){
		 			return false;
		 		}
		 				
		 		if (list.size() != 4){
		 			return false;
		 		}
		 		Integer.parseInt(list.get(3));
		 		break;
		 	default: 
		 		return false;	
		 }
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static String addNewMessage(String Message){
		if (validateMessage(Message)) {
			Record record = new Record();
			record.setId(IdGenerator.getNextId());
			record.setMessage(Message);
			record.setMessageStatus(Status.NEW);
			messages.add(record);
			return "OK";
		}
		else return "ERROR";
	}
	
	public static int getNextMessageId(){
		return messages.stream()
				.filter(m -> m.getMessageStatus() == Status.NEW)
				.findFirst()
				.get()
				.getId();
	}
	
	public static String getMessage(int id){
		return messages.stream()
				.filter(m -> m.getId() == id)
				.findFirst()
				.get()
				.getMessage();
	}
	
	public static void setMessageProcessed(int id){
		messages.stream()
				.filter(m -> m.getId() == id)
				.findFirst()
				.get()
				.setMessageStatus(Status.PROCESSED);
	}
	
	public static int getMessageCount(){
		return messages.size();
	}
	
}
