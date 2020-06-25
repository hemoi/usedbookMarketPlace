import java.util.HashMap;


public class Store {
	HashMap<String, User> UserLists;
	BookListClass BookController; 
	
	public Store() {
		UserLists = new HashMap<>();
		// add Admin ID
		newUser("admin", "nayana", null, null, null);
		// new Book List
		BookController = new BookListClass();
	}
	
	// make newUser
	public User newUser(String id, String pw, String name, String tel, String email) {
		// throw exception if there is already same ID.
		if (UserLists.get(id) != null) {
			System.out.println();
			throw new RuntimeException("(console) overlap ID");
		}
		User newUser = new User(id, pw, name, tel, email);
		UserLists.put(newUser.userID, newUser);
		return newUser;
	}
	
	// login, return User
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
	
	// deactivate User
	// user can't login if id is deactivated
	public void deactivateUser(String userID) {
		try {
			User targetUser = UserLists.get(userID);
			targetUser.status = UserStatus.DEACTIVATE;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	// activate User
	public void activateUser(String userID) {
		try {
		User targetUser = UserLists.get(userID);
		targetUser.status = UserStatus.ACTIVATE;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	// delete User. (User must be deactivated to delete)
	// also delete their book, by searching their book
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
