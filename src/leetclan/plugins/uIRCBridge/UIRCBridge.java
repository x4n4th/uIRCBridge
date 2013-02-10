package leetclan.plugins.uIRCBridge;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.ensifera.animosity.craftirc.CraftIRC;

public class UIRCBridge extends JavaPlugin {

	public static CraftIRC craftirc;
	
	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		System.out.println("[uIRCBridge] enabling...");
		VaultWrapper.INSTANCE.setupChat();
		VaultWrapper.INSTANCE.setupPermissions();
		craftirc = (CraftIRC) getServer().getPluginManager().getPlugin("CraftIRC");
		addBridges();
		getServer().getPluginManager().registerEvents(new EventListener(), this);
	}

	public void addBridges() {

		if ((new File("plugins" + File.separator + "uIRCBridge"
				+ File.separator + "config.yml").exists())) {
			System.out.println("[uIRCBridge] Parsing config file");

			java.util.Set<String> bridges = null;
			if (getConfig().contains("uIRCBridge.bridges")) {
				bridges = getConfig().getConfigurationSection("uIRCBridge.bridges").getKeys(false);
				if (bridges != null) {
					//if (getConfig().getConfigurationSection("uIRCBridge.bridges").getKeys(false).size() > 0) {
					for (String bridge: bridges) {
						BridgeManager.add(bridge, getConfig().getString("uIRCBridge.bridges." + bridge));
						System.out.println("[uIRCBridge] " + bridge + " added.");
					}
					return;
				}
			}
		}

		System.out.println("[uIRCBridge] Creating new config file");
		getConfig().set("uIRCBridge.bridges.CraftIRC-Tag-1", "HeroChat-Channel-1");
		getConfig().set("uIRCBridge.bridges.CraftIRC-Tag-2", "HeroChat-Channel-2");
		saveConfig();

	}

}
