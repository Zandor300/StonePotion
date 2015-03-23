package com.zandor300.stonepotion.listeners;

import com.zandor300.stonepotion.StonePotion;
import org.bukkit.Material;
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
		int rand = random.nextInt(3);
		int time = 60 + random.nextInt(60);
		if(rand == 0) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, time * 20, 1));
			StonePotion.getChat().sendMessage(player, "You have been rewarded with SPEED for " + time + " seconds.");
		} else if(rand == 1) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, time * 20, 1));
			StonePotion.getChat().sendMessage(player, "You have been rewarded with NIGHT VISION for " + time + " seconds.");
		} else if(rand == 2) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, time * 20, 1));
			StonePotion.getChat().sendMessage(player, "You have been rewarded with FIRE RESISTANCE for " + time + " seconds.");
		}
	}
}
