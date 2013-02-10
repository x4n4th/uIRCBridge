package leetclan.plugins.uIRCBridge;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

import com.dthielke.herochat.ChannelChatEvent;
import com.dthielke.herochat.Chatter;
import com.dthielke.herochat.Chatter.Result;
import com.dthielke.herochat.ConversationChannel;

public class EventListener implements Listener {

	
	@EventHandler
	public void onServerCommand(ServerCommandEvent event) {
		if (event.getCommand().startsWith("say")) {
			String message = event.getCommand().substring(4, event.getCommand().length() - 1);
			// broadcast
			for (Bridge b : BridgeManager.bridges) {
				b.endPoint.messageOut(message, "Server", b.craftIRCTag, b.GameChannel.getName(), b.GameChannel.getNick());
			}
		}
	}
	
	@EventHandler
	public void onChannelChat(ChannelChatEvent e) {
		if (e.getResult() == Result.ALLOWED) {
			String message = e.getMessage();
			String c = null;
			String n = null;
			
			if (e.getChannel() instanceof ConversationChannel) {
				ConversationChannel cc = (ConversationChannel) e.getChannel();
				c = "-> ";
				for (Chatter ch : cc.getMembers()) {
					if (ch == e.getSender()) continue;
					c = c.concat(ch.getName());
				}
				n = c;
			} else {
				c = (e.getChannel().getName() == null) ? "" : e.getChannel().getName();
				n = (e.getChannel().getNick() == null) ? "" : e.getChannel().getNick();				
			}
			 
			for (Bridge b : BridgeManager.getBridges(e.getChannel())) {
				b.endPoint.messageOut(message, e.getSender().getName(), b.craftIRCTag, c, n);
			}
		}
	}
	
}

