package com.zandor300.stonepotion.commands;

import com.zandor300.stonepotion.StonePotion;
import com.zandor300.zsutilities.commandsystem.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Zandor on 3/23/15.
 */
public class StonePotionCommand extends Command {

	public StonePotionCommand() {
		super("stonepotion", "Get info");
	}

	@Override
	public void execute(CommandSender sender, String[] strings) {
		StonePotion.getChat().sendMessage(sender, "StonePotion 1.0.0 by Zandor300");
	}
}
