public class Book {
	String ISBN;
	String name;
	String publisher;
	String writer;
	int year;
	int price;
	
	User user;
	
	BookCondition bookCondition;

	
	public Book(String ISBN, String name, String publisher, 
			String writer, int year, int price, String bookCondition, User user) {
		this.ISBN = ISBN;
		this.name = name;
		this.publisher = publisher;
		this.writer = writer;
		this.year = year;
		this.bookCondition = BookCondition.valueOf(bookCondition);
		this.price = price;
		this.user = user;
	}
	
	public void changeISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public void changeName(String name) {
		this.name = name;
	}
	
	public void changePublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void changeWriter(String writer) {
		this.writer = writer;
	}
	
	public void changeYear(int year) {
		this.year = year;
	}
	
	public void changeBookCondition(String condition) {
		this.bookCondition = BookCondition.valueOf(condition);
	}
	
}
