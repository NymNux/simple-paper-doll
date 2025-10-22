package dev.nymnux.simplepaperdoll.config.screen;

import dev.isxander.yacl3.api.ButtonOption;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.nymnux.simplepaperdoll.gui.ScreenPaperDollConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreen {

    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.empty())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Config"))
                        .option(ButtonOption.createBuilder()
                                .name(Text.literal("Open"))
                                .action((yaclScreen, buttonOption) -> {
                                    MinecraftClient client = MinecraftClient.getInstance();
                                    if (client.world != null) client.setScreen(new ScreenPaperDollConfig());
                                })
                                .text(Text.literal("Open in game"))
                                .build())
                        .build()
                )
                .build().generateScreen(parent);
    }

}
