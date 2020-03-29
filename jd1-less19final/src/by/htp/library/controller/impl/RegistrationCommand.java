package by.htp.library.controller.impl;

import by.htp.library.bean.User;
import by.htp.library.controller.Command;
import by.htp.library.dao.DaoException;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.UserService;

public class RegistrationCommand implements Command {

	@Override
	public String execute(String request) { 
		String[] params;
		String response = "";

		UserService service = ServiceProvider.getInstance().getUserService();
		params = request.split(" ", 3);

		String login = params[0].substring(params[0].indexOf('=') + 1);
		String password = params[1].substring(params[1].indexOf('=') + 1);
		String userRights = params[2].substring(params[2].indexOf('=') + 1);
		
		User user = new User(-1, login, password, userRights);

		try {
			service.registration(user);
			response = "registration completed successfully";					
		} catch (ServiceException e) { 
			response = "Some error";
		}
		return response;

	}
}
