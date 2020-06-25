import junit.framework.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


public class UnitTest extends TestCase{
	static Store store;
	int beforeSize;
	int afterSize;

	static User dummyUser;
	static Book dummyBook1;
	static Book dummyBook2;
	
	// before test. initialize Store instance, add dummies.
	@Before public void setUp() {

		store = new Store();
		dummyUser = store.newUser("dummy1", "passwd", "seller", "01012345678", "dummy@goggle.com");
		
		dummyBook1 = store.BookController.newBook("797-19-8192-151-3", "bible", "publi", "hand", 2019, 7000, "EXCELLENT", dummyUser);
		dummyBook2 = store.BookController.newBook("452-00-9876-865-3", "harryPotter", "def", "JKROLLING", 2017, 12000, "GOOD", dummyUser);

	}
	
	@After protected void tearDown() {

		System.gc();
	}
	
	// test addBook. check booklist's size
	@Test
	public void testAddBook() {
		beforeSize = store.BookController.BookLists.size();
		
		store.BookController.newBook("978-89-8392-811-5", "bible", "publi", "hand", 2019, 8000, "EXCELLENT", dummyUser);
		afterSize = store.BookController.BookLists.size();
		assertEquals(beforeSize+1,afterSize);
		beforeSize = afterSize;
		
		store.BookController.newBook("ISB001", "happy", "abc", "choijh", 2019, 7000, "GOOD", dummyUser);
		afterSize = store.BookController.BookLists.size();
		assertEquals(beforeSize+1,afterSize);
		beforeSize = afterSize;
		
	}
	
	// test addUser. check userlist's size
	@Test
	public void testAddUser() {
		beforeSize = store.UserLists.size();

		store.newUser("dummy2", "passwd", "buyer", "01091234243", "dummy@never.com");
		afterSize = store.UserLists.size();
		assertEquals(beforeSize+1,afterSize);

	}
	
	// test deactivate, delete user
	// also delete user's books
	@Test
	public void testDeleteUser() {
		beforeSize = store.UserLists.size();
		store.deactivateUser("dummy1");		
		assertEquals(dummyUser.status, UserStatus.DEACTIVATE);
		
		store.deleteUser("dummy1");
		afterSize = store.UserLists.size();
		assertEquals(beforeSize -1, afterSize);
		
		SearchBookList tmp = store.BookController.Search.searchByID("dummy1");
		assertEquals(tmp.getBookLists().size(), 0);
	}
	
	// test searching by using dummies.
	@Test
	public void testSearch() {
//		Book("452-00-9876-865-3", "harryPotter", "def", "JKROLLING", 2017, 12000, "GOOD", dummyUser)

		SearchBookList sbl;
		sbl = store.BookController.Search.searchByID("dummy1");
		assertEquals(2, sbl.getBookLists().size());
		
		sbl = store.BookController.Search.searchByName("harryPotter");
		assertEquals(1, sbl.getBookLists().size());
		
		sbl = store.BookController.Search.searchByWriter("JKROLLING");
		assertEquals(1, sbl.getBookLists().size());
		
		sbl = store.BookController.Search.searchByISBN("452-00-9876-865-3");
		assertEquals(1, sbl.getBookLists().size());
		
	}
	
	// test login(Search)
	@Test
	public void testLogin() {
		User user = store.login("dummy1", "passwd");
		System.out.println(user.userID);
	}
	
	// test login(Error)
	@Test(expected=RuntimeException.class)
	public void testLoginError() {
		try {
			store.login("dummy1", "1");
		} catch (RuntimeException re) {
			assertNotNull(re.getMessage());
		}
	}
	
	// test delete activate User
	@Test
	public void testDeleteActiveUser() {
		try {
			store.deleteUser("dummy1");
		} catch (RuntimeException re) {
			assertNotNull(re.getMessage());
		}
	}
}
