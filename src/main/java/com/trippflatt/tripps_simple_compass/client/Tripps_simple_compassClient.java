package com.trippflatt.tripps_simple_compass.client;

import com.trippflatt.tripps_simple_compass.util.HudRenderer;
import com.trippflatt.tripps_simple_compass.util.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class Tripps_simple_compassClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> HudRenderer.render(drawContext));
        ClientTickEvents.END_CLIENT_TICK.register(KeyInputHandler::checkInput);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                double mouseX = client.mouse.getX() * (double) client.getWindow().getScaledWidth() / (double) client.getWindow().getWidth();
                double mouseY = client.mouse.getY() * (double) client.getWindow().getScaledHeight() / (double) client.getWindow().getHeight();

                HudRenderer.handleMouseMove(mouseX, mouseY);
            }
        });
    }
}
