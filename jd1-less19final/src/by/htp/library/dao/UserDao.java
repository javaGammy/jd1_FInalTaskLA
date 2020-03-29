package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.bean.User;

public interface UserDao {
	
	boolean authorization(String login, String password) throws  DaoException ;
	boolean registration(User newUser) throws  DaoException ;
	boolean changeUserRights(int idUser, String newUserRights) throws  DaoException ;
	boolean addNewBook(Book book) throws  DaoException ;
	List<Book> SortedBooks() throws DaoException;

}

