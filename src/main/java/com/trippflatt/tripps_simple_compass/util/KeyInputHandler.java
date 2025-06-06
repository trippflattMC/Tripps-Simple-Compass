package com.trippflatt.tripps_simple_compass.util;

import com.trippflatt.tripps_simple_compass.keys.SendLocationKey;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyBinding TOGGLE_COMPASS = new KeyBinding(
            "key.toggle_compass",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "category.tripps_simple_compass"
    );

    public static final KeyBinding OPEN_SETTINGS = new KeyBinding(
            "key.compass.open_settings",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_J,
            "category.tripps_simple_compass"
    );

    public static final KeyBinding SEND_LOCATION = new KeyBinding(
            "key.compass.send_location",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            "category.tripps_simple_compass"
    );

    public static void register() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_COMPASS);
        KeyBindingHelper.registerKeyBinding(OPEN_SETTINGS);
        KeyBindingHelper.registerKeyBinding(SEND_LOCATION);
    }

    public static void checkInput(MinecraftClient client) {
        if (TOGGLE_COMPASS.wasPressed()) {
            HudRenderer.toggleCompass();
        }

        if (OPEN_SETTINGS.wasPressed()) {
            client.setScreen(new CompassSettings());
        }

        if (SEND_LOCATION.wasPressed()) {
            SendLocationKey.sendLocation();
        }
    }
}