package cipherallies.fabricmc.greetings;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class Greetings {
	@Inject(at = @At("RETURN"), method = "onPlayerConnect")
	private void Greeting(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info) {
		player.server.getPlayerManager().broadcastChatMessage(
			new LiteralText(
				String.format("Welcome, §l§e%s.", player.getGameProfile().getName())
				+ String.format("\n§r§fCurrent server time is §a%s.", LocalDateTime.now().format(
					DateTimeFormatter.ofPattern("hh:mm:ss a")
				))
				+ String.format("\n§r§fYour current coordinate is §b%s %s %s.", (long)player.getX(), (long)player.getY(), (long)player.getZ())
				+ "\n§fEnjoy your stay."
			),
			MessageType.SYSTEM, Util.NIL_UUID
		);
	}
}