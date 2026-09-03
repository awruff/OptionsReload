package com.awakenedredstone.optionsreload;

import net.minecraft.client.Minecraft;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.LiteralText;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;
import net.ornithemc.osl.keybinds.api.KeybindEvents;
import net.ornithemc.osl.keybinds.api.KeybindRegistry;
import net.ornithemc.osl.lifecycle.api.client.ClientWorldEvents;
import org.lwjgl.input.Keyboard;

public class OptionsReload implements ClientModInitializer {

    static KeyBinding RELOAD_KEY;

    static {
        KeybindEvents.REGISTER_KEYBINDS.register(() -> {
            RELOAD_KEY = KeybindRegistry.register("Reload", Keyboard.KEY_O, "Options Reload");
        });
    }


    @Override
    public void initClient() {
        ClientWorldEvents.TICK_END.register(it -> {
            if (RELOAD_KEY.consumeClick() && Keyboard.isKeyDown(Keyboard.KEY_F3)) {
                final Minecraft client = Minecraft.getInstance();

                client.options.load();

                client.player.sendMessage(new LiteralText("Reloaded Options"));
            }
        });
    }
}
