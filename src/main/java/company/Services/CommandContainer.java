package company.Services;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.<br/>
 *
 */

public class CommandContainer {

	private static final Logger log = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new TreeMap<>();

	static {
		// common commands
		commands.put("login", new Login());
		commands.put("logout", new Logout());
		commands.put("toRegister",new ToRegister());
		commands.put("toLogin",new ToLogin());
		commands.put("register", new Register());
		commands.put("userAccount",new UserProfile());
		commands.put("updateProfile",new UpdateProfile());
		commands.put("addAccount",new AddAccount());
		commands.put("payments",new Payments());
		commands.put("lock",new Lock());
		commands.put("unlock",new Unlock());
		commands.put("pdf",new Pdf());
		commands.put("makePayment",new MakePayment());
		commands.put("toMakePayment",new ToMakePayment());
		commands.put("userData", new UserData());
		commands.put("listUsers",new ListUsers());

		// client commands
	//	commands.put("listMenu", new ListMenuCommand());

		// admin commands
		//commands.put("listOrders", new ListOrdersCommand());

		log.debug("Command container was successfully initialized");
		log.trace("Number of commands --> " + commands.size());
	}

/**
 * Returns command object with the given name.
 *
 * @param commandName
 *            Name of the command.
 * @return Command object.
 */

	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			log.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}

}
