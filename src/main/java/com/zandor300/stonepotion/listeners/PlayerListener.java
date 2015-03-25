package com.zandor300.stonepotion.listeners;

import com.zandor300.stonepotion.StonePotion;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created by Zandor on 3/23/15.
 */
public class PlayerListener extends StonePotionListener {

	public PlayerListener(StonePotion plugin) {
		super(plugin);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		if(!event.getClickedBlock().getType().equals(Material.STONE))
			return;
		Player player = event.getPlayer();
		Random random = new Random();
		int rand = random.nextInt(StonePotion.getTypes().size());
		int time = 60 + random.nextInt(60);
		player.addPotionEffect(new PotionEffect(StonePotion.getTypes().get(rand), time * 20, 1));
		player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
		StonePotion.getChat().sendMessage(player, "You have been rewarded with " +
				StonePotion.getTypes().get(rand).getName().toLowerCase().replaceAll("_", " ") + " for " + time + " seconds.");
	}
}
