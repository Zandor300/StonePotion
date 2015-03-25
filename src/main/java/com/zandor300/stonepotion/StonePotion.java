package com.zandor300.stonepotion;

import com.zandor300.stonepotion.commands.StonePotionCommand;
import com.zandor300.stonepotion.listeners.PlayerListener;
import com.zandor300.zsutilities.commandsystem.CommandManager;
import com.zandor300.zsutilities.config.Config;
import com.zandor300.zsutilities.utilities.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Zandor on 3/23/15.
 */
public class StonePotion extends JavaPlugin {

	private static Chat chat = new Chat("StonePotion", ChatColor.GREEN);
	private static StonePotion plugin;
	private static Config config;

	private static ArrayList<PotionEffectType> types = new ArrayList<PotionEffectType>();

	public static Chat getChat() {
		return chat;
	}

	public static StonePotion getPlugin() {
		return plugin;
	}

	public static ArrayList<PotionEffectType> getTypes() {
		return types;
	}

	@Override
	public void onEnable() {
		chat.sendConsoleMessage("Setting things up...");

		config = new Config(this, "config.yml", true);
		plugin = this;
		PluginManager pm = Bukkit.getPluginManager();

		chat.sendConsoleMessage("Starting metrics...");
		try {
			new Metrics(this).start();
			chat.sendConsoleMessage("Submitted stats to MCStats.org.");
		} catch (IOException e) {
			chat.sendConsoleMessage("Couldn't submit stats to MCStats.org...");
		}

		chat.sendConsoleMessage("Registering effects...");
		for(Object effect : config.getConfig().getList("effects")) {
			String effect1 = effect.toString();
			PotionEffectType type = PotionEffectType.getByName(effect1);
			if(type == null)
				chat.sendConsoleMessage("ERROR: Effect " + effect1 + " doesn't exist in Bukkit. It will be skipped.");
			else
				types.add(type);
		}
		chat.sendConsoleMessage("Registered effects.");

		chat.sendConsoleMessage("Registering events...");
		pm.registerEvents(new PlayerListener(this), this);
		chat.sendConsoleMessage("Registered events.");

		new CommandManager().registerCommand(new StonePotionCommand(), this);

		chat.sendConsoleMessage("Everything is setup!");
		chat.sendConsoleMessage("Enabled.");
	}
}
