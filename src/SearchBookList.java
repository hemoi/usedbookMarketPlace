import java.util.ArrayList;

public class SearchBookList {
	static ArrayList<Book> BookList;
	
	public Book getBook(int index) {
		return BookList.get(index);
	}
	
	public void addBook(Book book) {
		BookList.add(book);
	}
	
	public ArrayList<Book> getBookLists() {
		return BookList;
	}
	
	public SearchBookList() {
		BookList = new ArrayList<Book>();
	}
}
