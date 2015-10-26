package net.binaryaura.stamina.common;

import net.binaryaura.stamina.entity.player.StaminaPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BAEventHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if(event.entity instanceof EntityPlayer && StaminaPlayer.get((EntityPlayer)event.entity) == null) {
			StaminaPlayer.register((EntityPlayer)event.entity);
		}
	}
	
	@SubscribeEvent
	public void onLivingJumpEvent(LivingJumpEvent event) {
		
	}
	
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		
	}
	
	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		
	}
	
	@SubscribeEvent
	public void onPlayerUseItemEvent(PlayerUseItemEvent event) {
		if(event instanceof PlayerUseItemEvent.Finish) {
			
		}
	}
}
