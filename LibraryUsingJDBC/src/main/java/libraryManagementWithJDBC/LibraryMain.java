package libraryManagementWithJDBC;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryMain {

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in)){
			BookDAO bookDao = new BookDAO();
			MemberDAO memberDao = new MemberDAO();
			TransactionDAO transactionDao = new TransactionDAO();
			boolean running = true;
			
			while(running) {
                System.out.println("\nLibrary Management System");
                System.out.println("1. Add a new book");
                System.out.println("2. Update book details");
                System.out.println("3. Delete a book");
                System.out.println("4. View all books");
                System.out.println("5. Add a new member");
                System.out.println("6. Update member details");
                System.out.println("7. Delete a member");
                System.out.println("8. View all members");
                System.out.println("9. Issue a book to a member");
                System.out.println("10. Return a book");
                System.out.println("11. View member borrowing history");
                System.out.println("12. Exit");
                System.out.println("");
                System.out.print("Choose an option: ");
                System.out.println("");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice) {
                
	                case 1:
	                    System.out.print("Enter book title: ");
	                    String title = sc.nextLine();
	                    System.out.print("Enter book author: ");
	                    String author = sc.nextLine();
	                    System.out.print("Enter book genre: ");
	                    String genre = sc.nextLine();
	                    System.out.print("Enter book quantity: ");
	                    int quantity = sc.nextInt();
	                    bookDao.addBook(title, author, genre, quantity);
	                    break;
	                    
	                case 2:
	                    System.out.print("Enter book ID to update: ");
	                    int bookIdToUpdate = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Enter new title: ");
	                    String newTitle = sc.nextLine();
	                    System.out.print("Enter new author: ");
	                    String newAuthor = sc.nextLine();
	                    System.out.print("Enter new genre: ");
	                    String newGenre = sc.nextLine();
	                    System.out.print("Enter new quantity: ");
	                    int newQuantity = sc.nextInt();
	                    bookDao.updateBook(bookIdToUpdate, newTitle, newAuthor, newGenre, newQuantity);
	                    break;
	                    
	                case 3:
	                    System.out.print("Enter book ID to delete: ");
	                    int bookIdToDelete = sc.nextInt();
	                    bookDao.deleteBook(bookIdToDelete);
	                    break;
	                    
	                case 4:
	                    bookDao.viewBooks();
	                    break;
	                    
	                case 5:
	                    System.out.print("Enter member name: ");
	                    String name = sc.nextLine();
	                    System.out.print("Enter email: ");
	                    String email = sc.nextLine();
	                    System.out.print("Enter phone: ");
	                    String phone = sc.nextLine();
	                    memberDao.addMember(name, email, phone);
	                    break;
	                    
	                case 6:
	                    System.out.print("Enter member ID to update: ");
	                    int memberIdToUpdate = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Enter new name: ");
	                    String newName = sc.nextLine();
	                    System.out.print("Enter new email: ");
	                    String newEmail = sc.nextLine();
	                    System.out.print("Enter new phone: ");
	                    String newPhone = sc.nextLine();
	                    memberDao.updateMember(memberIdToUpdate, newName, newEmail, newPhone);
	                    break;
	                    
	                case 7:
	                    System.out.print("Enter member ID to delete: ");
	                    int memberIdToDelete = sc.nextInt();
	                    memberDao.deleteMember(memberIdToDelete);
	                    break;
	                    
	                case 8:
	                    memberDao.viewMember();
	                    break;
	                    
	                case 9:
	                    System.out.print("Enter member ID: ");
	                    int memberIdToIssue = sc.nextInt();
	                    System.out.print("Enter book ID: ");
	                    int bookIdToIssue = sc.nextInt();
	                    transactionDao.issueBook(memberIdToIssue, bookIdToIssue);
	                    break;
	                    
	                case 10:
	                    System.out.print("Enter member ID: ");
	                    int memberIdToReturn = sc.nextInt();
	                    System.out.print("Enter book ID: ");
	                    int bookIdToReturn = sc.nextInt();
	                    transactionDao.returnBook(memberIdToReturn, bookIdToReturn);
	                    break;
	                    
	                case 11:
	                    System.out.print("Enter member ID: ");
	                    int memberIdToView = sc.nextInt();
	                    transactionDao.borrowHistory(memberIdToView);
	                    break;
	                    
	                case 12:
	                    running = false;
	                    System.out.println("Exiting...");
	                    break;
	                    
	                default:
	                    System.out.println("Invalid option. Please try again.");
                }
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
