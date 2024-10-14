package com.matthewperiut.retrocommands.command.server;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;

import java.util.logging.Logger;

public class ServerUtil {
    public static Logger LOGGER = Logger.getLogger("Minecraft");

    public static MinecraftServer getServer() {
        return (MinecraftServer) FabricLoader.getInstance().getGameInstance();
    }

    public static PlayerManager getConnectionManager() {
        return getServer().playerManager;
    }

    public static void sendFeedbackAndLog(String user, String message) {
        String str = user + ": " + message;
        ServerUtil.getConnectionManager().broadcast("§7(" + str + ")");
        LOGGER.info(str);
    }

    public static String appendEnd(int start, String[] parameters) {
        StringBuilder joinedString = new StringBuilder();
        for (int i = start; i < parameters.length; i++) {
            joinedString.append(parameters[i]).append(" ");
        }

        return joinedString.toString();
    }

    public static boolean isOp(String name) {
        return getServer().playerManager.isOperator(name);
    }
}
