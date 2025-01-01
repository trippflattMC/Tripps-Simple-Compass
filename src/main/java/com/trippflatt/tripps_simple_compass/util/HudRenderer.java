package com.trippflatt.tripps_simple_compass.util;

import com.trippflatt.tripps_simple_compass.Tripps_simple_compass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class HudRenderer {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static boolean isCompassEnabled = true;
    private static int x = 0;
    private static int y = 0;
    private static boolean isDragging = false;
    private static final int COMPASS_WIDTH = 48;
    private static final int COMPASS_HEIGHT = 24;


    public static void toggleCompass() {
        isCompassEnabled = !isCompassEnabled;
    }

    public static void render(DrawContext drawContext) {
        if (!isCompassEnabled) return;

        MatrixStack matrices = drawContext.getMatrices();
        if (client.player == null) return;

        float yaw = client.player.getYaw();
        float northAngle = (yaw % 360 + 360) % 360;

        String arr = "^";
        String angleText = String.format("%.0f°", northAngle);

        TextRenderer renderer = client.textRenderer;

        matrices.push();

        matrices.translate(x + 12, y + 12, 0);

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(northAngle));

        drawContext.drawText(renderer, arr, -renderer.getWidth(arr) / 2, -renderer.fontHeight / 2, 0x373737, true);

        matrices.pop();

        drawContext.drawText(renderer, angleText, x + 20, y + 8, 0x373737, true);

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        if (x == 0 && y == 0) {
            Identifier texture = Identifier.of(Tripps_simple_compass.MOD_ID, "textures/gui/compass_background_00.png");
            drawContext.drawTexture(texture, x, y, 0, 0, COMPASS_WIDTH, COMPASS_HEIGHT, COMPASS_WIDTH, COMPASS_HEIGHT);
        } else if (x == screenWidth - COMPASS_WIDTH && y == screenHeight - COMPASS_HEIGHT) {
            Identifier texture = Identifier.of(Tripps_simple_compass.MOD_ID, "textures/gui/compass_background_03.png");
            drawContext.drawTexture(texture, x, y, 0, 0, COMPASS_WIDTH, COMPASS_HEIGHT, COMPASS_WIDTH, COMPASS_HEIGHT);
        } else if (x == 0 && y == screenHeight - COMPASS_HEIGHT) {
            Identifier texture = Identifier.of(Tripps_simple_compass.MOD_ID, "textures/gui/compass_background_02.png");
            drawContext.drawTexture(texture, x, y, 0, 0, COMPASS_WIDTH, COMPASS_HEIGHT, COMPASS_WIDTH, COMPASS_HEIGHT);
        } else if (x == screenWidth - COMPASS_WIDTH && y == 0) {
            Identifier texture = Identifier.of(Tripps_simple_compass.MOD_ID, "textures/gui/compass_background_01.png");
            drawContext.drawTexture(texture, x, y, 0, 0, COMPASS_WIDTH, COMPASS_HEIGHT, COMPASS_WIDTH, COMPASS_HEIGHT);
        } else {
            Identifier texture = Identifier.of(Tripps_simple_compass.MOD_ID, "textures/gui/compass_background_04.png");
            drawContext.drawTexture(texture, x, y, 0, 0, COMPASS_WIDTH, COMPASS_HEIGHT, COMPASS_WIDTH, COMPASS_HEIGHT);
        }
    }

    public static void startDragging(double mouseX, double mouseY) {
        isDragging = true;
    }

    public static void stopDragging() {
        isDragging = false;
    }

    public static void handleMouseMove(double mouseX, double mouseY) {
        if (isDragging) {
            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();

            x = (int) mouseX;
            y = (int) mouseY;

            x = Math.max(0, Math.min(x, screenWidth - COMPASS_WIDTH));
            y = Math.max(0, Math.min(y, screenHeight - COMPASS_HEIGHT));
        }
    }

    public static void resetPosition() {
        x = 0;
        y = 0;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void setCompassPosition(int setX, int setY) {
        x = setX;
        y = setY;
    }

    public static boolean isDragging() {
        return isDragging;
    }
}
