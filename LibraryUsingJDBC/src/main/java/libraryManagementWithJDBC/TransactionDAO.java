package libraryManagementWithJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionDAO {
	
	private Connection con;
	
	public TransactionDAO() throws SQLException {
		con = DbConnection.getConnection();	
	}
	
	//issuing a book
	public void issueBook(int memberid, int bookid) throws SQLException{
		
		//checking if the book is available
		String checkquery = "select quantity from books where bookid = ?";
		
		try(PreparedStatement checkpstmt = con.prepareStatement(checkquery)){
			checkpstmt.setInt(1, bookid);
			ResultSet rs = checkpstmt.executeQuery();
			
			if(rs.next()) {
				int quantity = rs.getInt("quantity");
				if(quantity > 0) {
					//book is available
					String issuequery = "insert into transactions(memberid, bookid, issuedate) value (?, ?, Curdate())";
					try(PreparedStatement issuepstmt = con.prepareStatement(issuequery)){
						issuepstmt.setInt(1, memberid);
						issuepstmt.setInt(2, bookid);
						issuepstmt.executeUpdate();
						
						String updatequery = "update books set quantity = quantity - 1 where bookid = ?";
						try(PreparedStatement updatepstmt = con.prepareStatement(updatequery)){
							updatepstmt.setInt(1, bookid);
							updatepstmt.executeUpdate();
							System.out.println("");
							System.out.println("Book successfully issued!");
						}
					}
				}
				else {
					System.out.println("");
					System.out.println("Sorry, the book is out of stock!");
				}
			}
			else {
				System.out.println("");
				System.out.println("Book not found!");
			}
		}
	}
	
	
	//returning book
	public void returnBook(int memberid, int bookid) throws SQLException{
		
		String query = "update transactions set returnstatus = true where memberid = ? and bookid = ? and returnstatus = false";
		try(PreparedStatement pstmt = con.prepareStatement(query)){
			pstmt.setInt(1, memberid);
			pstmt.setInt(2,  bookid);
			int rows  = pstmt.executeUpdate();
			if(rows > 0) {
				String udpatequery = "update books set quantity = quantity + 1 where bookid = ?";
				try(PreparedStatement updatepstmt = con.prepareStatement(udpatequery)){
					updatepstmt.setInt(1, bookid);
					updatepstmt.executeUpdate();
					System.out.println("");
					System.out.println("Book returned successfully");
				}
			}
			else {
				System.out.println("");
				System.out.println("No such Transaction found!");
			}
		}
	}
	
	
	//view borrow history of member
	public void borrowHistory(int memberid) throws SQLException{
		
		String query = "select b.title, t.issuedate, t.returnstatus from transactions t join books b on t.bookid = b.bookid where t.memberid = ?";
		try(PreparedStatement pstmt = con.prepareStatement(query)){
			pstmt.setInt(1, memberid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("Book: " + rs.getString("title") + ", Issue Date: " + rs.getDate("issuedate") + 
                        ", Returned: " + (rs.getBoolean("returnstatus") ? "Yes" : "No"));
			}
		}
	}
}
