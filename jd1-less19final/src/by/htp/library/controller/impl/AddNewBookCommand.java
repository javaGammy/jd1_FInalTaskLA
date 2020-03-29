package by.htp.library.controller.impl;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.UserService;

public class AddNewBookCommand implements Command {

	@Override
	public String execute(String request) {
		String[] params;
		String response = "";

		UserService service = ServiceProvider.getInstance().getUserService();
		params = request.split(" ", 4);

		String author = params[0].substring(params[0].indexOf('=') + 1);
		
		String title = params[1].substring(params[1].indexOf('=') + 1);		
		
		int price = Integer.parseInt(params[3].substring(params[3].indexOf('=') + 1));
		

		Book book = new Book(-1, author, title, price);

		try {
			service.addnewBook(book);
			response = "Book successfully added";
		} catch (ServiceException e) { 
			response = "Some error";
		}
		return response;

	}
}