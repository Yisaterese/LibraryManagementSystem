package com.example.library_management_system_app.services;
import com.example.library_management_system_app.data.model.Author;
import com.example.library_management_system_app.data.model.Book;
import com.example.library_management_system_app.data.model.User;
import com.example.library_management_system_app.dto.*;
import com.example.library_management_system_app.dto.utility.Response.*;
import com.example.library_management_system_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LibraryServicesImpl implements LibraryServices{
    @Autowired
    private UserServices userServices;
    @Autowired
    private LibrarianServices librarianServices;
    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        return userServices.registerUser(registerRequest);
    }
    @Override
    public  RegisterResponse registerLibrarian(RegisterRequest registerRequest){
       return librarianServices.registerLibrarian(registerRequest);
    }
    @Override
    public int getNumberOfUsers() {return userServices.getNumberOfUsers();}
    @Override
    public void deleteUserBy(String username) {userServices.removeByUsername(username);}
    @Override
    public List<User> getUsers() {return userServices.getUser();}
    @Override
    public void deleteByUsername(String username) {
        librarianServices.deleteByUsername(username);
    }
    @Override
    public int getNumberOfBooks() {
        return librarianServices.getNumberOfBooks();
    }
    @Override
    public int getNumberOfLibrarians() {
        return librarianServices.getNumberOfLibrarians();
    }
@Override
public Book findBookByIsbn(FindBookRequest findBookRequest){
        return librarianServices.findBookByIsbn(findBookRequest);
}
    @Override
    public Book findBookByAuthorAndTitle(Author author, String title) {
           return userServices.userFindBookByAuthorAndTitle(author, title);
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest){
        return userServices.login(loginRequest);
    }
    @Override
    public Book findBookByAuthorAndTitle(String authorName, String bookTitle) {
            return userServices.userFindBookByAuthorAndTitle(authorName, bookTitle);
    }
    @Override
    public AddBookResponse addBookToLibrary(BookRequest bookRequest){
        return librarianServices.addBookToLibrary(bookRequest);
    }
    @Override
    public DeleteBookResponse deleteBookByTitle(DeleteBookRequest deletebookRequest) {
        return librarianServices.deleteBookByTitle(deletebookRequest);
    }

    @Override
    public void recordBookBorrower(String username) {
        User foundUser = userServices.findByUserName(username);
        validations(foundUser);
    }

    private void validations(User foundUser) {
        validateNullUser(foundUser);
        isBorrowedUser(foundUser);
        userServices.save(foundUser);
    }

    private static void isBorrowedUser(User foundUser) {
       if(foundUser.isBorrowBook()) foundUser.setBorrowBook(true);
    }

    private static void validateNullUser(User foundUser) {
        if (foundUser == null) throw new UserNotFoundException("user not found");
    }

    @Override
    public List<Book> getBorrowedBooks(){
        return librarianServices.getBooksBorrowed();
    }
    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest bookRequest){
       return userServices.borrowBook(bookRequest);
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        return userServices.logout(logoutRequest);
    }

    @Override
    public List<Book> returnedBorrowedBooks() {
        return librarianServices.returnedBorrowedBooks();
    }
    @Override
    public ReturnBorrowedBookResponse returnBorrowedBookResponse(ReturnedBorrowedBookRequest request){
        return userServices.returnBorrowedBook(request);
    }

}

