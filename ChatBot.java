package Api;

import org.jibble.pircbot.*;

public class ChatBot {
	public static void main(String[] args) throws Exception {

		bot ChatBot = new bot();

		ChatBot.setVerbose(true);

		// connect to freenode
		ChatBot.connect("irc.freenode.net");

		// channel name
		ChatBot.joinChannel("#LetsChat");

	}
}
