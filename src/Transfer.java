
public class Transfer {
	static boolean transfer(Book targetBook, User buyer) {
		System.out.println(targetBook.name + "å���� �Ϸ�!");
		System.out.println("Both " + targetBook.user.userID + ", " + buyer.userID + " will get email");
		System.out.println("seller email : " + targetBook.user.email);
		System.out.println("buyer email : " + buyer.email);
				
		return true;
	}
	
	
}
