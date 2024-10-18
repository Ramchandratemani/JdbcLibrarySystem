package libraryManagementWithJDBC;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {
	
	private Connection con;
	
	public MemberDAO() throws SQLException{
		con = DbConnection.getConnection();
	}
	
	//add a member
	public void addMember(String name, String email, String phone) throws SQLException{
		
		String query = "insert into members (name, email, phone) values (?,?,?)";
		
		try(PreparedStatement pstmt = con.prepareStatement(query)){
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			
			pstmt.executeUpdate();
			System.out.println("Member added successfully");
		}
	}
	
	
	//update member details
	public void updateMember(int memberid, String name, String email, String phone) throws SQLException{
		
		String query = "update members set name = ?, email = ?, phone = ? where memberid = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(query)){
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setInt(4, memberid);
			
			int rows =  pstmt.executeUpdate();
			
			if(rows > 0) {
				System.out.println("");
				System.out.println("Member details updated successfully");
			}
			else {
				System.out.println("");
				System.out.println("Member was not found");
			}
		}
	}
	
	
	//delete a member
	public void deleteMember(int memberid) throws SQLException{
		String query = "delete from members where memeberid = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(query)){
			
			pstmt.setInt(1, memberid);
			int rows = pstmt.executeUpdate();
			
			if(rows > 0) {
				System.out.println("");
				System.out.println("Member deleted successfully!");
			}
			else {
				System.out.println("");
				System.out.println("Member was not found");
			}
		}
	}
	
	
	//view all members
	public void viewMember() throws SQLException{
		
		String query = "Select * from members";
		
		try(Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next()){
				System.out.println("Member ID: " + rs.getInt("memberid") + ", Name: " + rs.getString("name") + ", Email: " + rs.getString("email") +
                          ", Phone: " + rs.getString("phone"));
			}
		}
	}
}
