package dev.nymnux.simplepaperdoll;

import dev.nymnux.simplepaperdoll.config.ModConfig;
import dev.nymnux.simplepaperdoll.module.SimplePaperDoll;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class SimplePaperDollMod implements ClientModInitializer {

	public static final String MOD_ID = "simplepaperdoll";

	public static final SimplePaperDoll SIMPLE_PAPER_DOLL = new SimplePaperDoll();

	@Override
	public void onInitializeClient() {
		ModConfig.HOLDER.load();
		ClientTickEvents.START_CLIENT_TICK.register(SIMPLE_PAPER_DOLL::onClientTickStart);
	}

}