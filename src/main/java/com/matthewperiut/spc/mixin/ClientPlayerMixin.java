package com.matthewperiut.spc.mixin;

import com.matthewperiut.spc.SPC;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.MultiplayerClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerClientPlayerEntity.class)
public class ClientPlayerMixin {
    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendFeedbackInMP(String par1, CallbackInfo ci) {
        if (par1.startsWith("/clear")) {
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).inGameHud.clearChat();
            ci.cancel();
        }
        if (par1.startsWith("/perm")) {
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).inGameHud.addChatMessage("mp_op: " + SPC.mp_op);
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).inGameHud.addChatMessage("mp_spc: " + SPC.mp_op);
            ci.cancel();
        }
    }
}
