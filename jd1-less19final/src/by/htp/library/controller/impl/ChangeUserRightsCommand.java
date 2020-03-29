package by.htp.library.controller.impl;


	import by.htp.library.bean.User;
	import by.htp.library.controller.Command;
	import by.htp.library.service.ServiceException;
	import by.htp.library.service.ServiceProvider;
	import by.htp.library.service.UserService;

	public class ChangeUserRightsCommand implements Command {

		@Override
		public String execute(String request) {
			String[] params;
			String response = "";

			UserService service = ServiceProvider.getInstance().getUserService();
			params = request.split(" ", 3);

			int id = Integer.parseInt(params[0].substring(params[0].indexOf('=') + 1));

			String status = params[1].substring(params[1].indexOf('=') + 1);

			try {
				service.changeUserRights(id, userRights);
				response = "status was changed";
			} catch (ServiceException e) { 
				response = "error";
			}
			return response;

		}
	}

