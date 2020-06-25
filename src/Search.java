import java.util.ArrayList;
import java.util.Iterator;

public class Search {
	static ArrayList<Book> BookLists;
	
	
	public Search(ArrayList<Book> bookLists) {
		BookLists = bookLists;
		
	}
	
	public SearchBookList searchByISBN(String ISBN) {
		SearchBookList searchBookList = new SearchBookList();

		Iterator<Book> it = BookLists.iterator();
		while(it.hasNext()) {
			Book tmpBook = it.next();
			if(ISBN.equals(tmpBook.ISBN)) {
				searchBookList.addBook(tmpBook);
			}
		}
		
		return searchBookList;
	}
	
	public SearchBookList searchByName(String name) {
		SearchBookList searchBookList = new SearchBookList();

		Iterator<Book> it = BookLists.iterator();
		while(it.hasNext()) {
			Book tmpBook = it.next();
			if(name.equals(tmpBook.name)) {
				searchBookList.addBook(tmpBook);
			}
		}
		
		return searchBookList;
	}
	
	public SearchBookList searchByID(String userID) {
		SearchBookList searchBookList = new SearchBookList();

		Iterator<Book> it = BookLists.iterator();
		while(it.hasNext()) {
			Book tmpBook = it.next();
			if(userID.equals(tmpBook.user.userID)) {
				searchBookList.addBook(tmpBook);
			}
		}
		
		return searchBookList;
	}
	
	public SearchBookList searchByWriter(String writer) {
		SearchBookList searchBookList = new SearchBookList();

		Iterator<Book> it = BookLists.iterator();
		while(it.hasNext()) {
			Book tmpBook = it.next();
			if(writer.equals(tmpBook.writer)) {
				searchBookList.addBook(tmpBook);
			}
		}
		
		System.out.println("finish searching");
		
		return searchBookList;
	}

}
