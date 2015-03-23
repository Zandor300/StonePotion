package com.zandor300.stonepotion;

import com.zandor300.stonepotion.commands.StonePotionCommand;
import com.zandor300.stonepotion.listeners.PlayerListener;
import com.zandor300.zsutilities.commandsystem.CommandManager;
import com.zandor300.zsutilities.utilities.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;

/**
 * Created by Zandor on 3/23/15.
 */
public class StonePotion extends JavaPlugin {

	private static Chat chat = new Chat("StonePotion", ChatColor.GREEN);
	private static StonePotion plugin;

	public static Chat getChat() {
		return chat;
	}

	public static StonePotion getPlugin() {
		return plugin;
	}

	@Override
	public void onEnable() {
		chat.sendConsoleMessage("Setting things up...");

		plugin = this;
		PluginManager pm = Bukkit.getPluginManager();

		chat.sendConsoleMessage("Starting metrics...");
		try {
			new Metrics(this).start();
			chat.sendConsoleMessage("Submitted stats to MCStats.org.");
		} catch (IOException e) {
			chat.sendConsoleMessage("Couldn't submit stats to MCStats.org...");
		}

		chat.sendConsoleMessage("Registering events...");
		pm.registerEvents(new PlayerListener(this), this);
		chat.sendConsoleMessage("Registered events.");

		new CommandManager().registerCommand(new StonePotionCommand(), this);

		chat.sendConsoleMessage("Everything is setup!");
		chat.sendConsoleMessage("Enabled.");
	}
}
