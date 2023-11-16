package com.github.anopensaucedev.archbtw.mixin;

import com.github.anopensaucedev.archbtw.client.ArchbtwClient;
import com.github.anopensaucedev.libmcdevfabric.OSUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientNetworkHandlerMixin {

    @Inject(method = "onChatMessage", at = @At("HEAD"))
    public void messageListener(ChatMessageS2CPacket packet, CallbackInfo ci){
        if(packet.body().content().contains("Windows".toLowerCase()) || packet.body().content().contains("OS".toLowerCase())){ // if anyone mentions OS'es...
            flexOS(); // flex your superior operating system.
        }
    }

    public void flexOS(){

        if(OSUtils.IS_UNIX_LIKE){ // check if the user is on BSD/Linux (chances are, it's linux)
            if(OSUtils.fetchOSName().matches("Arch Linux")){
                MinecraftClient.getInstance().player.networkHandler.sendChatMessage("arch btw"); // if they run arch.
            }else{
                MinecraftClient.getInstance().player.networkHandler.sendChatMessage("I run linux"); // if they run any non-arch distro. (including arch bases.)
            }
        }

    }



}
