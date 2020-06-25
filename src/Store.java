import java.util.HashMap;
//import java.util.Set;
//import java.util.Iterator;

public class Store {
	HashMap<String, User> UserLists;
	BookListClass BookController; 
	
	public Store() {
		UserLists = new HashMap<>();
		newUser("admin", "nayana", null, null, null);
		BookController = new BookListClass();
	}
	
	public User newUser(String id, String pw, String name, String tel, String email) {
		if (UserLists.get(id) != null) {
			System.out.println();
			throw new RuntimeException("(console) overlap ID");
		}
		User newUser = new User(id, pw, name, tel, email);
		UserLists.put(newUser.userID, newUser);
		return newUser;
	}
	
	public User login(String id, String pw) {
		try {
			User tmpUser = UserLists.get(id);
			if (tmpUser.status == UserStatus.DEACTIVATE) {
				throw new RuntimeException("user deactivated");
			}	
			if (pw.equals(tmpUser.passWD) != true) {
				throw new RuntimeException("incorrect passwd");
			}

			return tmpUser;
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
	}
		
	public void deactivateUser(String userID) {
		try {
			User targetUser = UserLists.get(userID);
			targetUser.status = UserStatus.DEACTIVATE;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void activateUser(String userID) {
		try {
		User targetUser = UserLists.get(userID);
		targetUser.status = UserStatus.ACTIVATE;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public String deleteUser(String userID) {
		User targetUser = UserLists.get(userID);
		
		if (targetUser.status == UserStatus.DEACTIVATE) {
			UserLists.remove(userID);
			SearchBookList tmpBookLists = this.BookController.Search.searchByID(userID);
			this.BookController.deleteBook(tmpBookLists);
		}
		else {
			throw new RuntimeException("user must be deactivated");
		}
		
		return userID;
	}
}
