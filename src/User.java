
public class User {
	String userID;
	String passWD;
	
	String name;
	String tel;
	String email;
	UserStatus status;
	
	public User(String id, String pw, String name, String tel, String email) {
		this.userID = id;
		this.passWD = pw;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.status = UserStatus.ACTIVATE;
	}
}
