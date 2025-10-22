package dev.nymnux.simplepaperdoll.module;

import dev.nymnux.simplepaperdoll.config.CfgPaperDoll;
import dev.nymnux.simplepaperdoll.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SimplePaperDoll {

    private static final double YAW_BASE = 180.0;
    private static final double MAX_PITCH_LIMIT = 90.0;

    private static double yaw = YAW_BASE;
    private static double lastRealtimeYaw = YAW_BASE;
    private static boolean yawChanged = false;
    private static double yawSmooth = YAW_BASE;

    private static double pitch = 0.0;

    private static void tickRotationRestore() {
        CfgPaperDoll config = ModConfig.getInstance().cfgPaperDoll;
        if (!yawChanged) {
            yawSmooth = yaw;
            double dYaw = (1.0 + Math.sin((Math.PI / 2.0) * (Math.abs(YAW_BASE - yaw) / config.rotateLimitHDeg))) * config.yawRestoreSpeed;
            if (yaw > YAW_BASE) {
                yaw = Math.max(yaw - dYaw, YAW_BASE);
            } else if (yaw < YAW_BASE) {
                yaw = Math.min(yaw + dYaw, YAW_BASE);
            }
        }
    }

    private static void updateRotation(double realtimeYaw, double realtimePitch) {
        CfgPaperDoll config = ModConfig.getInstance().cfgPaperDoll;
        double yawLimit = config.rotateLimitHDeg;
        double pitchLimit = config.rotateLimitVDeg;
        yawChanged = Math.abs(lastRealtimeYaw - realtimeYaw) > config.yawChangeEpsilonDeg;
        if (yawChanged) {
            double dYaw = relativeAngleDiffDeg(realtimeYaw, lastRealtimeYaw) * config.yawChangeSpeed;
            yaw += dYaw;
            yaw = MathHelper.clamp(yaw, YAW_BASE - yawLimit, YAW_BASE + yawLimit);
            yawSmooth = yaw;
        }
        lastRealtimeYaw = realtimeYaw;
        if (config.mapPitchLimit) realtimePitch *= pitchLimit / MAX_PITCH_LIMIT;
        pitch = MathHelper.clamp(realtimePitch, -pitchLimit, pitchLimit);
    }

    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        CfgPaperDoll config = ModConfig.getInstance().cfgPaperDoll;
        if (!config.enable) return;
        double realtimeYaw = clampYaw(client.player.getYaw());
        double realtimePitch = clampPitch(client.player.getPitch());
        updateRotation(realtimeYaw, realtimePitch);
        int scaledWidth = client.getWindow().getScaledWidth();
        int scaledHeight = client.getWindow().getScaledHeight();
        int x = (int) (scaledWidth * config.xPercent);
        int y = (int) (scaledHeight * config.yPercent);
        int dx = config.dx;
        int dy = config.dy;
        drawPaperDoll(drawContext, x, y, dx, dy, config.offsetY, config.scale, config.cameraRotationHDeg, config.cameraRotationVDeg, tickDelta);
        if (!config.showBox) return;
        drawOutlineQuad(drawContext, x - dx, y - dy ,dx * 2, dy * 2, 0xFFFFFFFF);
    }

    private static void drawPaperDoll(DrawContext drawContext, int x, int y, int dx, int dy, double offsetY, double scale, double rotateHDeg, double rotateVDeg, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        int x1 = x - dx;
        int y1 = y - dy;
        int x2 = x + dx;
        int y2 = y + dy;
        LivingEntity player = client.player;
        Quaternionf rotation = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf overrideCameraAngle = new Quaternionf()
                .rotateY((float) Math.toRadians(rotateHDeg))
                .rotateX((float) Math.toRadians(rotateVDeg));
        rotation.mul(overrideCameraAngle);

        float yaw_ = player.getYaw();
        float pitch_ = player.getPitch();
        float lastPitch_ = player.lastPitch;
        float lastHeadYaw_ = player.lastHeadYaw;
        float headYaw_ = player.headYaw;
        float bodyYaw_ = player.bodyYaw;
        float lastBodyYaw_ = player.lastBodyYaw;

        float yawI = (float) MathHelper.lerp(tickDelta, yawSmooth, yaw);
        player.setYaw(yawI);
        player.setPitch((float) pitch);
        player.lastPitch = (float) pitch;
        player.lastHeadYaw = yawI;
        player.headYaw = yawI;
        player.bodyYaw = (float) YAW_BASE;
        player.lastBodyYaw = (float) YAW_BASE;

        Vector3f translation = new Vector3f(0.0F, (float) offsetY, 0.0F);

        EntityRenderDispatcher dispatcher = client.getEntityRenderDispatcher();
        EntityRenderer<? super LivingEntity, ?> renderer = dispatcher.getRenderer(player);
        EntityRenderState renderState = renderer.getAndUpdateRenderState(player, tickDelta);
        renderState.hitbox = null;
        drawContext.addEntity(renderState, (float) scale, translation, rotation, overrideCameraAngle, x1, y1, x2, y2);

        player.setYaw(yaw_);
        player.setPitch(pitch_);
        player.lastPitch = lastPitch_;
        player.lastHeadYaw = lastHeadYaw_;
        player.headYaw = headYaw_;
        player.bodyYaw = bodyYaw_;
        player.lastBodyYaw = lastBodyYaw_;
    }

    public void onClientTickStart(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        CfgPaperDoll config = ModConfig.getInstance().cfgPaperDoll;
        if (!config.enable) return;
        tickRotationRestore();
    }

    private static double relativeAngleDiffDeg(double degTo, double degFrom) {
        return ((degTo - degFrom) % 360 + 540) % 360 - 180;
    }

    private static double clampYaw(double yaw) {
        yaw %= 360.0;
        if (yaw < 0) yaw += 360.0;
        return yaw;
    }

    private static double clampPitch(double pitch) {
        pitch = Math.max(-90, Math.min(90, pitch));
        return pitch;
    }

    private static void drawOutlineQuad(DrawContext context, int x, int y, int width, int height, int color) {
        fill(context, x, y, width, 1, color);
        fill(context, x + width - 1, y, 1, height, color);
        fill(context, x, y + height - 1, width, 1, color);
        fill(context, x, y, 1, height, color);
    }

    private static void fill(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x, y, x + width, y + height, color);
    }

}
