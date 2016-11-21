/************************************************
* Developed By: Anshul Khantwal, MT16010
* Tested By:	Mohd Saquib, MT16034 
*************************************************/
package Controller;
import Model.Model;
/*
 * Abstract Class Controller which acts as super class of all the other Controller modules
 */
public abstract class Controller {
	
	protected Model db;
	public Controller(){
		db = new Model();
	}
	public Model getDb() {
		return db;
	}
}