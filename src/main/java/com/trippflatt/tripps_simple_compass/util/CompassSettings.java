package com.trippflatt.tripps_simple_compass.util;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class CompassSettings extends Screen {
    private static final Text TITLE = Text.translatable("gui.compass.title");

    protected CompassSettings() {
        super(TITLE);
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(
                        Text.translatable("gui.compass.move_button"),
                        button -> {
                            if (client != null) {
                                client.setScreen(new CompassMovingScreen());
                            }
                        })
                .position(this.width / 2 - 75, this.height / 2 - 20)
                .size(150, 20)
                .build());
        this.addDrawableChild(ButtonWidget.builder(
                        Text.translatable("gui.compass.reset_button"),
                        button -> {
                            if (client != null) {
                                HudRenderer.resetPosition();
                            }
                        })
                .position(this.width / 2 - 75, this.height / 2 + 5)
                .size(150, 20)
                .build());
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        super.render(drawContext, mouseX, mouseY, delta);
        drawContext.drawCenteredTextWithShadow(textRenderer, Text.translatable("settings.compass.title"), width / 2, height / 4, 0xFFFFFF);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}