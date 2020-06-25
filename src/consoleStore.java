import java.util.Scanner;

import java.util.InputMismatchException;

public class consoleStore {
	static Store store;
	static Scanner scan;

	static public void showBookInfo(SearchBookList BookLists) {
		System.out.println("no\t                ISBN\t        NAME\t publisher\t    writer\tyear\t price\t  sellerID\tbookCondition");
		int i =1;
		for (Book itBook : BookLists.getBookLists()) {
			System.out.printf("%2d\t%20s\t%12s\t%10s\t%10s\t%4d\t%6d\t%10s\t%13s\n", i, itBook.ISBN, itBook.name, itBook.publisher, itBook.writer, itBook.year, itBook.price, itBook.user.userID, itBook.bookCondition);
			i ++;
		}
	}
	
	static public void showBookInfo(Book itBook) {
		System.out.println("                ISBN\t        NAME\t publisher\t    writer\tyear\t price\t  sellerID\tbookCondition");
		System.out.printf("%20s\t%12s\t%10s\t%10s\t%4d\t%6d\t%10s\t%13s\n", itBook.ISBN, itBook.name, itBook.publisher, itBook.writer, itBook.year, itBook.price, itBook.user.userID, itBook.bookCondition);

	}
	
	static public void showUserList() {

		System.out.println("<유저리스트>");
		System.out.println("          id\t      name\t          tel\t               email\t    status");
		for (User user : store.UserLists.values()) {
			System.out.printf("%12s\t%10s\t%13s\t%20s\t%10s\t\n", user.userID, user.name, user.tel, user.email, user.status);
		}
		
	}
	
	static public void manageUser() {
		System.out.println("어떤 유저를 관리할 건가요?");
		String userID = scan.next();
		
		System.out.println("[1] deactivate  [2] delete  [3] activate");
		int sel = scan.nextInt();
		try {
			switch(sel) {
			case 1:
				store.deactivateUser(userID);
				break;
			case 2:
				try {
					store.deleteUser(userID);
				} catch (RuntimeException re) {
					System.out.println(re.getMessage());
				}
					break;
			case 3:
				store.activateUser(userID);
				break;
			default:
				System.out.println("잘못된 선택입니다.");
			}
		} catch (Exception e) {
			System.out.println("해당하는 유저가 없습니다.");
		}
		showUserList();
	}
	
	static public Book searchBook(){
		int sel;
		System.out.println("검색옵션\n[1] 책의 제목  [2] ISBN  [3] 저자  [4] 판매자id");
		sel = scan.nextInt();
		System.out.println("검색어");
		String word = scan.next();
		SearchBookList searchBookList = new SearchBookList();
		
		switch (sel) {
		case 1:
			searchBookList = store.BookController.Search.searchByName(word);
			break;
		case 2:
			searchBookList = store.BookController.Search.searchByISBN(word);
			break;
		case 3:
			searchBookList = store.BookController.Search.searchByWriter(word);
			break;
		case 4:
			searchBookList = store.BookController.Search.searchByID(word);
			break;
		}
		showBookInfo(searchBookList);
		
		Book targetBook = selectBook(searchBookList);
		
		return targetBook;
	}
	
	static int stringToInt(String str) {
		int number = 0;
		try{
		    if(str != null)
		      number = Integer.parseInt(str);
		    return number;
		}
		catch (NumberFormatException e){
		    return number;
		}
	}
	
	static public void addNewBook(User nowUser) {
		String ISBN, name, publisher, writer, bookCondition, price, year;
		
		System.out.println("책 등록 메뉴입니다.");
		
		System.out.println("책 ISBN을 입력하세요");
		scan.nextLine();
		ISBN = scan.nextLine();
		System.out.println("책 제목을 입력하세요");
		name = scan.nextLine();
		System.out.println("작가 이름을 입력하세요");
		writer = scan.nextLine();
		System.out.println("출판사 이름을 입력하세요");
		publisher = scan.nextLine();
		System.out.println("년도를 입력하세요");
		year = scan.nextLine();
		System.out.println("가격을 입력하세요");
		price = scan.nextLine();
		
		System.out.println("책 상태를 입력하세요\n[1] EXCELLENT [2] GOOD [3] FAIR [4] NONE");
		try {
			int sel = scan.nextInt();
			switch (sel){
			case 1:
				bookCondition = "EXCELLENT";
				break;
			case 2:
				bookCondition = "GOOD";
				break;
			case 3:
				bookCondition = "FAIR";
				break;
			default:
				bookCondition = "NONE";
				break;
			}
			
			try {
				Book newBook = store.BookController.newBook(ISBN, name, publisher, writer, stringToInt(year), stringToInt(price), bookCondition, nowUser);
				System.out.println("새로운 책이 생성되었습니다.");
				showBookInfo(newBook);
			} catch (Exception e) {
				System.out.println("인자 값에 문제가 있습니다");
			}
			
		} catch(InputMismatchException e) {
			throw e;
		}
		
		
	}
	
	static void printLine() {
		System.out.println();
	}
	
	static public User login() {
		int sel =0;
		User nowUser = null;
		do {
			System.out.println("[1] 로그인   [2] 회원가입");
			try {
				sel = scan.nextInt();
			} catch (InputMismatchException e) {
				scan = new Scanner(System.in);
			} finally {
				if (sel == 1) {
						System.out.println("ID를 입력하세요.");
						String id = scan.next();
						
						System.out.println("PW를 입력하세요.");
						String pw = scan.next();
						try {
							nowUser = store.login(id, pw);
						} catch (Exception e) {
//							System.out.println(e.getMessage());
							continue;
						}
	
					
				} else if(sel == 2) {
					System.out.println("ID를 입력하세요.");
					String userID = scan.next();
					System.out.println("PW를 입력하세요.");
					String pw = scan.next();
					System.out.println("이름 입력하세요.");
					String name = scan.next();
					System.out.println("전화번호를 입력하세요.");
					String tel = scan.next();
					System.out.println("이메일을 입력하세요.");
					String email = scan.next();
					try {
						nowUser = store.newUser(userID, pw, name, tel, email);
						System.out.println(userID + "로 로그인하셨습니다.");

					} catch (RuntimeException re) {
						System.out.println(re.getMessage());
					}
				} else {
					System.out.println("올바른 숫자를 입력하세요.");
				}
			}
		} while (nowUser == null);
		
		return nowUser;
	}
	
	public static Book selectBook(SearchBookList BookList) {
		System.out.println("몇번째 책을 선택하시겠습니까?");
		int sel = scan.nextInt();
		try {
			Book targetBook = BookList.getBook(sel-1);
			return targetBook;

		} catch(Exception e){
			System.out.println("invalid index");
			throw e;
		}
	}
	
	public static void deleteBook(Book targetBook) {
		showBookInfo(targetBook);
		try {
			store.BookController.deleteBook(targetBook);
			System.out.println("해당 책을 삭제했습니다.");

		} catch(Exception e) {
			System.out.println("책을 삭제하는데 실패했습니다.");
			deleteBook(targetBook);
		} 
	}
	
	public static void changeBook(SearchBookList BookLists) {
		Book targetBook;
		showBookInfo(BookLists);
		int sel;
		String target;
		
		try {
			targetBook = selectBook(BookLists);
			System.out.println("무엇을 변경할 건가요?");
			System.out.println("[1] ISBN [2] name [3] publishser [4] writer [5] year [6] bookCondition");
			sel = scan.nextInt();
			System.out.println("무엇으로 변경할 건가요?");
			target = scan.next();
			
			switch (sel) {
			case 1:
				targetBook.changeISBN(target);
				break;
			case 2:
				targetBook.changeName(target);
				break;
			case 3:
				targetBook.changePublisher(target);
				break;
			case 4:
				targetBook.changeWriter(target);
				break;
			case 5:
				int year = Integer.parseInt(target);
				targetBook.changeYear(year);
				break;
			case 6:
				targetBook.changeBookCondition(target);
				break;
			default:
				System.out.println("invalid selection");
			}
//			String ISBN, String name, String publisher, 
//			String writer, int year, String bookCondition, User user
		} catch(Exception e) {
			System.out.println("index error!");
		}
		
	}
	
	public static void main(String[] args) {
		store = new Store();
		scan = new Scanner(System.in);
		User nowUser = null;
		Book targetBook;
		int sel;
		
		// make dummy
		User dummy1 = store.newUser("dummy1", "passwd", "seller", "01012345678", "dummy@goggle.com");
		store.newUser("dummy2", "passwd", "buyer", "01091234243", "dummy@never.com");

		store.BookController.newBook("978-89-8392-811-5", "bookName123", "CAU", "superwri", 2019, 7000, "EXCELLENT", dummy1);
		store.BookController.newBook("970-45-6542-121-2", "hyperledgerGood", "CAU", "choijh", 2019, 9000, "GOOD", dummy1);
		store.BookController.newBook("452-00-9876-865-3", "harryPotter", "abc", "JKROLLING", 2017, 12000, "GOOD", dummy1);
		
		System.out.println("책 거래 시스템에 접속하신걸 환영합니다.");
		printLine();
		
		nowUser = login();
		
		
		while(true) {
		switch(nowUser.userID) {
		case("admin"):
			printLine();
			System.out.println("현재 관리자 권한으로 로그인한 상태입니다.");
			System.out.println("[1] 책관리  [2] 유저관리  [3] 다른 계정 로그인  [4] 시스템종료");
			
			sel = scan.nextInt();
			switch (sel) {
				case 1:
					printLine();
					try {
						targetBook = searchBook();
						store.BookController.deleteBook(targetBook);
						System.out.println(targetBook.name + "를 삭제했습니다");
					} catch (Exception e) {
						System.out.println("invalid index");
					}
					break;
					
				case 2:
					printLine();
					showUserList();
					manageUser();
					break;
					
				case 3:
					printLine();
					nowUser = login();
					break;
				
				case 4:
					printLine();
					scan.close();
					System.exit(0);
			}
			break;
		
		default:
			printLine();
			System.out.println("현재 " + nowUser.userID +"로 로그인 된 상태입니다");
			System.out.println("[1] 책구매   [2] 나의 책 관리   [3] 책 등록   [4] 다른 계정 로그인   [5] 시스템종료");
			sel = scan.nextInt();
			
			switch(sel){
			
			// buy book
			case 1:
				printLine();
				try {
					targetBook = searchBook();
					Transfer.transfer(targetBook, nowUser);
				} catch (Exception e) {
					System.out.println("제대로 된 선택을 해주세요");
					scan = new Scanner(System.in);
				}
				break;
			
			// manage my book
			case 2:
				printLine();
				SearchBookList BookLists = store.BookController.Search.searchByID(nowUser.userID);
				System.out.println("[1] 삭제\t[2] 수정");

				try {
					sel = scan.nextInt();
					switch (sel){
					case 1:
						try {
							showBookInfo(BookLists);
							targetBook = selectBook(BookLists);
							deleteBook(targetBook);
						} catch (Exception e) {
							System.out.println("index를 확인해 주세요");
						}
						break;
					case 2:
						changeBook(BookLists);
						break;
					default :
						System.out.println("잘못된 선택입니다.");
					}
				} catch (Exception e){
					System.out.println("invalid index");
				}
				break;
			
			// add new book
			case 3:
				printLine();
				addNewBook(nowUser);
				break;
			
			// login with another user
			case 4:
				printLine();
				nowUser = login();
				break;
			
			case 5:
				printLine();
				scan.close();
				System.exit(0);
			}
			
		}
		}
//		scan.close();
	}

}
