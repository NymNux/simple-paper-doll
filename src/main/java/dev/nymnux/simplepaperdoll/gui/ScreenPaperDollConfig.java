package dev.nymnux.simplepaperdoll.gui;

import dev.nymnux.simplepaperdoll.config.CfgPaperDoll;
import dev.nymnux.simplepaperdoll.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ScreenPaperDollConfig extends Screen {

    public ScreenPaperDollConfig() {
        super(Text.empty());
    }

    @Override
    protected void init() {
        CfgPaperDoll config = ModConfig.getInstance().cfgPaperDoll;
        MinecraftClient client = MinecraftClient.getInstance();
        int btnWidth = 200;
        int btnHeight = 20;
        int gap = 1;
        int panelWidth = btnWidth * 2 + gap;
//        int panelHeight = btnHeight * 8 + gap * 7;
        int gridWidth = btnWidth + gap;
        int gridHeight = btnHeight + gap;
        int x = (client.getWindow().getScaledWidth() - panelWidth) / 2;
        int y = 0;

        addDrawableChild(ButtonWidget.builder(Text.literal(config.enable ? "On" : "Off"), button -> {
            config.enable = !config.enable;
            button.setMessage(Text.literal(config.enable ? "On" : "Off"));
        }).dimensions(x, y, btnWidth, btnHeight).build());
        addDrawableChild(ButtonWidget.builder(Text.literal(config.showBox ? "Show Box: On" : "Show Box: Off"), button -> {
            config.showBox = !config.showBox;
            button.setMessage(Text.literal(config.showBox ? "Show Box: On" : "Show Box: Off"));
        }).dimensions(x + gridWidth, y, btnWidth, btnHeight).build());
        addDrawableChild(new ModDoubleSlider(x, y + gridHeight, btnWidth, btnHeight, Text.literal("X Percent"), config.xPercent, 0.0, 1.0, mappedValue -> config.xPercent = mappedValue));
        addDrawableChild(new ModDoubleSlider(x + gridWidth, y + gridHeight, btnWidth, btnHeight, Text.literal("Y Percent"), config.yPercent, 0.0, 1.0, mappedValue -> config.yPercent = mappedValue));
        addDrawableChild(new ModIntegerSlider(x, y + gridHeight * 2, btnWidth, btnHeight, Text.literal("Region Width"), config.dx, 1, 256, mappedValue -> config.dx = mappedValue));
        addDrawableChild(new ModIntegerSlider(x + gridWidth, y + gridHeight * 2, btnWidth, btnHeight, Text.literal("Region Height"), config.dy, 1, 384, mappedValue -> config.dy = mappedValue));
        addDrawableChild(new ModDoubleSlider(x, y + gridHeight * 3, btnWidth, btnHeight, Text.literal("Horizontal Camera Rotation"), config.cameraRotationHDeg, -90.0, 90.0, mappedValue -> config.cameraRotationHDeg = mappedValue));
        addDrawableChild(new ModDoubleSlider(x + gridWidth, y + gridHeight * 3, btnWidth, btnHeight, Text.literal("Vertical Camera Rotation"), config.cameraRotationVDeg, -90.0, 90.0, mappedValue -> config.cameraRotationVDeg = mappedValue));
        addDrawableChild(new ModDoubleSlider(x, y + gridHeight * 4, btnWidth, btnHeight, Text.literal("Horizontal Rotation Limit"), config.rotateLimitHDeg, 1.0, 90.0, mappedValue -> config.rotateLimitHDeg = mappedValue));
        addDrawableChild(new ModDoubleSlider(x + gridWidth, y + gridHeight * 4, btnWidth, btnHeight, Text.literal("Vertical Rotation Limit"), config.rotateLimitVDeg, 1.0, 90.0, mappedValue -> config.rotateLimitVDeg = mappedValue));
        addDrawableChild(new ModDoubleSlider(x, y + gridHeight * 5, btnWidth, btnHeight, Text.literal("Y Offset"), config.offsetY, -8.0, 8.0, mappedValue -> config.offsetY = mappedValue));
        addDrawableChild(new ModDoubleSlider(x + gridWidth, y + gridHeight * 5, btnWidth, btnHeight, Text.literal("Scale"), config.scale, 1.0, 200.0, mappedValue -> config.scale = mappedValue));
        addDrawableChild(new ModDoubleSlider(x, y + gridHeight * 6, btnWidth, btnHeight, Text.literal("Yaw Change Speed"), config.yawChangeSpeed, 0.01, 5.0, mappedValue -> config.yawChangeSpeed = mappedValue));
        addDrawableChild(new ModDoubleSlider(x + gridWidth, y + gridHeight * 6, btnWidth, btnHeight, Text.literal("Yaw Restore Speed"), config.yawRestoreSpeed, 0.01, 60.0, mappedValue -> config.yawRestoreSpeed = mappedValue));
        addDrawableChild(new ModDoubleSlider(x, y + gridHeight * 7, btnWidth, btnHeight, Text.literal("Yaw Change Epsilon"), config.yawChangeEpsilonDeg, 0.0, 5.0, mappedValue -> config.yawChangeEpsilonDeg = mappedValue));
        addDrawableChild(ButtonWidget.builder(Text.literal(config.mapPitchLimit ? "Map Pitch Limit: On" : "Map Pitch Limit: Off"), button -> {
            config.mapPitchLimit = !config.mapPitchLimit;
            button.setMessage(Text.literal(config.mapPitchLimit ? "Map Pitch Limit: On" : "Map Pitch Limit: Off"));
        }).dimensions(x + gridWidth, y + gridHeight * 7, btnWidth, btnHeight).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Reset"), button -> {
            config.reset();
            clearAndInit();
        }).dimensions(0, 0, 60, 20).build());

        super.init();
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        // cancel
    }
}
