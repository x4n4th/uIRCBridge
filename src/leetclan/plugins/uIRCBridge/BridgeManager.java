package leetclan.plugins.uIRCBridge;

import java.util.HashSet;
import java.util.Set;

import com.dthielke.herochat.Channel;

public class BridgeManager {

	public static Set<Bridge> bridges = new HashSet<Bridge>();
	
	public static void add(String IRCChannel, String GameChannel) {
		bridges.add(new Bridge(IRCChannel, GameChannel));
	}
	
	public static Set<Bridge> getBridges(Channel channel) {
		Set<Bridge> ret = new HashSet<Bridge>();
		for (Bridge b : bridges) {
			if (b.GameChannel == channel || b.getAll) {
				ret.add(b);
			}
		}
		return ret;
	}
	
	public static Set<Bridge> getBridges(String tag) {
		Set<Bridge> ret = new HashSet<Bridge>();
		for (Bridge b : bridges) {
			if (b.craftIRCTag == tag) {
				ret.add(b);
			}
		}
		return ret;
	}
}
