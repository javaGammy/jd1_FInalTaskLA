package by.htp.library.service.impl;

import java.util.Collections;
import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.bean.User;
import by.htp.library.dao.DaoException;
import by.htp.library.dao.DaoProvider;
import by.htp.library.dao.UserDao;

import by.htp.library.service.ServiceException;
import by.htp.library.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean authorization(String login, String password) throws ServiceException {
		if (login == null || login.isEmpty()) {
			throw new RuntimeException("login note find");
		}
		
		boolean result;

		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDao = provider.getUserDao();
		try {
			result = userDao.authorization(login, password);

		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return result;

	}

	@Override
	public void registration(User user) throws ServiceException {

		
		if (user.getLogin() == null || user.getLogin().isEmpty() || user.getPassword() == null
				|| user.getPassword().isEmpty()) {
			throw new RuntimeException("login not find");
		}

		boolean result;

		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDao = provider.getUserDao();

		try {
			result = userDao.registration(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void addnewBook(Book book) throws ServiceException {

		if (book.getAuthor() == null || book.getAuthor().isEmpty() ) {
			throw new RuntimeException("someError");// stub
		}

		boolean result;

		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDao = provider.getUserDao();

		try {
			result = userDao.addNewBook(book);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void changeUserRights(int id, String userRights) throws ServiceException {

				if (userRights==null ) {
					throw new RuntimeException("Some error");
				}

				boolean result;

				DaoProvider provider = DaoProvider.getInstance();
				UserDao userDao = provider.getUserDao();

				try {
					result = userDao.changeUserRights(id, userRights);
				} catch (DaoException e) {
					throw new ServiceException(e);
				}

			}

	@Override
	public String SortedBooks() throws ServiceException {
		DaoProvider provider = DaoProvider.getInstance();
		UserDao userDao = provider.getUserDao();
		
		List<Book> books;
		try {
			books = userDao.SortedBooks();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		Collections.sort(books);
		String s = "";
		for(Book b:books) {
			
			s=s + b.toString() +  "\n";
		}
		
		
		
		
		return s;
	}

}

