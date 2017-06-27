package message;
/**
* <h1>New message id generator</h1>
* New incremented message id is returned
*
* @author  Tadas Poskus
* @version 1.0
* @since   2017-06-25
*/

public class IdGenerator {
	private static int id = 0;
	private IdGenerator(){}
	
	public static int getNextId(){
		id++;
        return id;
    }
	
}