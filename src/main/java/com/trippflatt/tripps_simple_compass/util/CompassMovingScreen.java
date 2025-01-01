package com.trippflatt.tripps_simple_compass.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class CompassMovingScreen extends Screen {
    private static final Text TITLE = Text.translatable("gui.compass.title2");

    protected CompassMovingScreen() {
        super(TITLE);
    }

    @Override
    protected void init() {
        GLFW.glfwSetCursorPos(client.getWindow().getHandle(), HudRenderer.getX() * 3, HudRenderer.getY() * 3);
        HudRenderer.startDragging((double) this.width / 2, (double) this.height / 2);
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && HudRenderer.isDragging() && client != null) {
            HudRenderer.stopDragging();
            client.setScreen(new CompassSettings());
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
