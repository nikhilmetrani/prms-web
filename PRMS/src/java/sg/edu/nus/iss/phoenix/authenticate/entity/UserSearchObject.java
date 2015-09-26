/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.entity;

/**
 *
 * @author Jeremy
 */
public class UserSearchObject {
	  private String name;
	    private String id;
		public UserSearchObject() {
			super();
		}
		public UserSearchObject(String id, String name) {
			super();
                        this.id = id;
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
}