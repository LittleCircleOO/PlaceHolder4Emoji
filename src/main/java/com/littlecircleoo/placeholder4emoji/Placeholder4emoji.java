package com.littlecircleoo.placeholder4emoji;

import eu.pb4.placeholders.api.PlaceholderResult;
import eu.pb4.placeholders.api.Placeholders;
import eu.pb4.placeholders.api.ServerPlaceholderContext;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;

public class Placeholder4emoji implements ModInitializer {
    @Override
    public void onInitialize() {
        Placeholders.registerServer(id("weather"), (context, argument) -> PlaceholderResult.value(weatherIcon(level(context))));
        Placeholders.registerServer(id("weather_cn"), (context, argument) -> PlaceholderResult.value(weatherText(level(context))));
        Placeholders.registerServer(id("moon_phase"), (context, argument) -> PlaceholderResult.value(moonPhaseEmoji(level(context))));
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath("p4e", path);
    }

    private static ServerLevel level(ServerPlaceholderContext context) {
        ServerLevel level = context.serverLevel();
        return level != null ? level : context.server().overworld();
    }

    private static String weatherIcon(ServerLevel level) {
        if (level.isThundering()) {
            return "\u26C8";
        } else if (level.isRaining()) {
            return "\uD83C\uDF27";
        }
        return "\u2600";
    }

    private static String weatherText(ServerLevel level) {
        if (level.isThundering()) {
            return "\u96F7\u96E8";
        } else if (level.isRaining()) {
            return "\u96E8\u5929";
        }
        return "\u6674\u5929";
    }

    private static String moonPhaseEmoji(ServerLevel level) {
        int phase = (int) ((level.getDefaultClockTime() / 24000L % 8L + 8L) % 8L);
        return switch (phase) {
            case 0 -> "\uD83C\uDF15";
            case 1 -> "\uD83C\uDF16";
            case 2 -> "\uD83C\uDF17";
            case 3 -> "\uD83C\uDF18";
            case 4 -> "\uD83C\uDF11";
            case 5 -> "\uD83C\uDF12";
            case 6 -> "\uD83C\uDF13";
            default -> "\uD83C\uDF14";
        };
    }
}
