package by.htp.library.controller;

public class Controller {
	public String action(String request)   {// registration login=aaa password=bbb name=Ivan
		String[] params;
		Command command;

		params = request.split(" ", 2);

		command = provider.getCommand(params[0]);// RegistrationCommand
		

		String response;
		response = command.execute(params[1]);

		return response;
	}

}
