package by.htp.library.controller.impl;

import by.htp.library.controller.Command;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.UserService;

public class SortedByPriceCommand implements Command {
	    @Override
		public String execute(String request) {
			String response = "";
			UserService service = ServiceProvider.getInstance().getUserService();
			try {
				response = service.SortedBooks();
			} catch (ServiceException e) { 
				response = "error";
			}
			return response;
		}

	}


