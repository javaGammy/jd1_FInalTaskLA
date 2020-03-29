package by.htp.library.dao.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.bean.User;
import by.htp.library.dao.DaoException;
import by.htp.library.dao.UserDao;

public class FileUserDao implements UserDao {

	@Override
	public boolean authorization(String login, String password) throws DaoException {
		String line;
		List<User> users = new ArrayList<User>();

		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/users.txt"))) {
			while ((line = br.readLine()) != null) {
				String[] lineVariables = line.split(" ");

				int id = Integer.parseInt(lineVariables[0].substring(lineVariables[0].indexOf('=') + 1));
				String login = lineVariables[1].substring(lineVariables[1].indexOf('=') + 1);
				String password = lineVariables[2].substring(lineVariables[2].indexOf('=') + 1);
				String status = lineVariables[3].substring(lineVariables[3].indexOf('=') + 1);

				User user = new User(id, login, password, status);
				users.add(user);

			}
		} catch (FileNotFoundException e) {
			throw new DaoException(e);

		} catch (IOException e1) {
			throw new DaoException(e1);
		}		
		
		for (User u : users) {

			if (u.getLogin().equals(login) && (u.getPassword().equals(password))) {
				return true;
			}
		}

		return false;

	}

	@Override
	public boolean registration(User newUser) throws DaoException {
		String line;
		List<User> users = new ArrayList<User>();

		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/users.txt"))) {
			while ((line = br.readLine()) != null) {
				String[] lineVariables = line.split(" ");

				int id = Integer.parseInt(lineVariables[0].substring(lineVariables[0].indexOf('=') + 1));
				String login = lineVariables[1].substring(lineVariables[1].indexOf('=') + 1);
				String password = lineVariables[2].substring(lineVariables[2].indexOf('=') + 1);
				String status = lineVariables[3].substring(lineVariables[3].indexOf('=') + 1);

				User user = new User(id, login, password, status);
				users.add(user);

			}
		} catch (FileNotFoundException e) {
			throw new DaoException(e);

		} catch (IOException e1) {
			throw new DaoException(e1);
		}		
		
		int maxId = users.get(0).getId();
		for (User us : users) {
			if (us.getId() > maxId) {
				maxId = us.getId();
			}
		}

		try (FileWriter writer = new FileWriter("src/resources/users.txt", true)) {

			String login = newUser.getLogin();
			String password = newUser.getPassword();
			String userRights = newUser.getUserRights();
			
			writer.append('\n');
			String s =  "id=" + (maxId + 1) + " " + "login=" + login + " " + "password=" + password + " "
					+ "status=" + userRights  ;
			writer.write(s);
			writer.flush();

		} catch (IOException e) {
			throw new DaoException(e);
		}

		return false;
	}

	
	@Override
	public boolean addNewBook(Book book) throws DaoException {
		List<Book> books = getBooksFromFile();

		int maxId = books.get(0).getId();
		for (Book b : books) {
			if (b.getId() > maxId) {
				maxId = b.getId();
			}
		}

		try (FileWriter writer = new FileWriter("src/resources/books.txt", true)) {

			String author = book.getAuthor();
			String title = book.getTitle();			
			int price = book.getPrice();

			
			String lineSeparator = System.getProperty("line.separator");
			String s = lineSeparator + "id=" + (maxId + 1) + " " + "author=" + author + " " + "title=" + title + " " + "price=" + price;
			writer.write(s);
			writer.flush();

		} catch (IOException e) {
			throw new DaoException(e);
		}

		return false;
	}

	@Override
	public boolean changeUserRights(int idUser, String newUserRights) throws DaoException {

		String line;
		List<User> users = new ArrayList<User>();

		try (BufferedReader br = new BufferedReader(new FileReader("src/resources/users.txt"))) {
			while ((line = br.readLine()) != null) {
				String[] lineVariables = line.split(" ");

				int id = Integer.parseInt(lineVariables[0].substring(lineVariables[0].indexOf('=') + 1));
				String login = lineVariables[1].substring(lineVariables[1].indexOf('=') + 1);
				String password = lineVariables[2].substring(lineVariables[2].indexOf('=') + 1);
				String status = lineVariables[3].substring(lineVariables[3].indexOf('=') + 1);

				User user = new User(id, login, password, status);
				users.add(user);

			}
		} catch (FileNotFoundException e) {
			throw new DaoException(e);

		} catch (IOException e1) {
			throw new DaoException(e1);
		}		
		for (User u : users) {
			if (u.getId() == idUser) {
				u.setUserRights(newUserRights);

			}
		}

		try (FileWriter writer = new FileWriter("src/resources/users.txt")) {

			for (User u : users) {
				String lineSeparator = System.getProperty("line.separator");

				String s = "id=" + String.valueOf(u.getId()) + " " + "login=" + u.getLogin() + " " + "password="
						+ u.getPassword() + " " + "status=" + u.getUserRights() + lineSeparator;
				writer.write(s);
				writer.flush();
			}

		} catch (IOException e) {
			throw new DaoException(e);
		}

		return false;
	}

	@Override
	public List<Book> SortedBooks() throws DaoException {
		List<Book> users = getBooksFromFile();
		return users;
	}
}
