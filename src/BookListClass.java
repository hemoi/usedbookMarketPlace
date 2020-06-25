import java.util.Iterator;
import java.util.ArrayList;

public class BookListClass {
	public ArrayList<Book> BookLists;
	public static Iterator<Integer> it;
	public Search Search;
	
	public BookListClass() {
		BookLists = new ArrayList<Book>();
		Search = new Search(BookLists);
	}
	
	public ArrayList<Book> getBookLists() {
		return BookLists;
	}
	
	public Book newBook(String ISBN, String name, String publisher, 
			String writer, int year, int price, String bookCondition, User user) {
		try {
			// name cannot be null
			if (name.equals("")) {
				throw new RuntimeException("name can't be null");
			}
			
			Book newBook = new Book(ISBN, name, publisher, writer, year, price, bookCondition, user);
			BookLists.add(newBook);
			return newBook;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	

	public void deleteBook(Book targetBook) {
		
		BookLists.remove(targetBook);
		targetBook = null;
	}
	
	// delete SearchBookList
	// for delete user's books
	public void deleteBook(SearchBookList tmpBookLists) {
		Iterator<Book> it = tmpBookLists.getBookLists().iterator();
		
		while(it.hasNext()) {
			Book tmpBook = it.next();
			deleteBook(tmpBook);
		}
		
		System.out.println("(console) finish deleteBookLists");
		
	}
}
