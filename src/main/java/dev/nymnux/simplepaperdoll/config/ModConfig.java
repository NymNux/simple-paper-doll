package dev.nymnux.simplepaperdoll.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.nymnux.simplepaperdoll.SimplePaperDollMod;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class ModConfig {

    public static final ConfigClassHandler<ModConfig> HOLDER = ConfigClassHandler.createBuilder(ModConfig.class)
            .id(Identifier.of(SimplePaperDollMod.MOD_ID, SimplePaperDollMod.MOD_ID + "_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve(SimplePaperDollMod.MOD_ID + ".json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(true)
                    .build())
            .build();

    static {
        HOLDER.load();
    }

    public static ModConfig getInstance() {
        return HOLDER.instance();
    }

    public static void save() {
        HOLDER.save();
    }

    @SerialEntry
    public CfgPaperDoll cfgPaperDoll = new CfgPaperDoll();

}
