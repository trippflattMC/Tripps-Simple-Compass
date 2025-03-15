package com.trippflatt.tripps_simple_compass.keys;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.Objects;

public class SendLocationKey {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static void sendLocation() {
        if (client.player == null || client.world == null) return;
        float yaw = client.player.getYaw();
        float northAngle = (yaw % 360 + 360 + 180) % 360;
        String angleText = String.format("%.0f°", northAngle);

        Vec3d start = client.player.getCameraPosVec(client.getRenderTickCounter().getTickDelta(true));

        Vec3d direction = client.player.getRotationVec(client.getRenderTickCounter().getTickDelta(true));

        Vec3d end = start.add(direction.multiply(400));

        BlockHitResult result = client.world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, client.player));

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = result.getBlockPos();
            String pos = blockPos.getX() + " " + blockPos.getY() + " " + blockPos.getZ();
            String message = angleText + " | " + pos;
            sendTeamMsg(message);
        } else  {
            client.player.sendMessage(Text.translatable("message.compass.error_message"), false);
        }
    }

    public static void sendTeamMsg(String text) {
        if (client.player != null) {
            String command = "teammsg " + text;

            Objects.requireNonNull(client.getNetworkHandler()).sendChatCommand(command);
        }
    }
}
