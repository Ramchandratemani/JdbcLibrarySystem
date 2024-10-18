package libraryManagementWithJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookDAO {
	
	private Connection con;
	
	public BookDAO() throws SQLException{
		con = DbConnection.getConnection();
	}
	
	// Adding a book
	public void addBook(String title, String author, String genre, int quantity) throws SQLException{
		
		String query = "Insert into books (title, author, genre, quantity) value (?,?,?,?)";
		
		try (PreparedStatement pstmt = con.prepareStatement(query)){
			
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setString(3, genre);
			pstmt.setInt(4, quantity);
			pstmt.executeUpdate();
			
			System.out.println("");
			System.out.println("Book added successfully!");
		}
	}
	
	
	//update book
	public void updateBook(int bookId, String title, String author, String genre, int quantity) throws SQLException{
		
		String query = "update books set title = ?, author = ?, genre = ?, quantity = ? where bookid = ?";
		
		try (PreparedStatement pstmt = con.prepareStatement(query)){
			
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setString(3, genre);
			pstmt.setInt(4, quantity);
			pstmt.setInt(5, bookId);
			int rows = pstmt.executeUpdate();
			
			if(rows > 0) {
				System.out.println("");
				System.out.println("Book updated successfully!");
			}
			else {
				System.out.println("");
				System.out.println("Book not found");
			}
		}	
	}
	
	
	//Delete a book
	public void deleteBook(int bookid) throws SQLException{
		
		String query = "delete from books where bookid = ?";
		try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, bookid);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
            	System.out.println("");
                System.out.println("Book deleted successfully!");
            } else {
            	System.out.println("");
                System.out.println("Book not found.");
            }
        }
	}
	
	
	//view all books
	public void viewBooks() throws SQLException{
		
		String query = "select * from books";
		
		try(Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next()) {
				
				System.out.println("Book ID: " + rs.getInt("bookid") + ", Title: " + rs.getString("title") + ", Author: " + rs.getString("author") +
                        ", Genre: " + rs.getString("genre") + ", Quantity: " + rs.getInt("quantity"));
			}
		}
	}
}
