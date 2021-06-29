package me.pioula111.craftingandstats.itemy.komendy.komendyNapoje;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.jetbrains.annotations.NotNull;

public class CommandlistaEfektow implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;

        sender.sendMessage(PotionEffectTypeWrapper.ABSORPTION.getName());
        sender.sendMessage(PotionEffectTypeWrapper.BLINDNESS.getName());
        sender.sendMessage(PotionEffectTypeWrapper.CONFUSION.getName());
        sender.sendMessage(PotionEffectTypeWrapper.DAMAGE_RESISTANCE.getName());
        sender.sendMessage(PotionEffectTypeWrapper.FIRE_RESISTANCE.getName());
        sender.sendMessage(PotionEffectTypeWrapper.HARM.getName());
        sender.sendMessage(PotionEffectTypeWrapper.HEAL.getName());
        sender.sendMessage(PotionEffectTypeWrapper.HEALTH_BOOST.getName());
        sender.sendMessage(PotionEffectTypeWrapper.HUNGER.getName());
        sender.sendMessage(PotionEffectTypeWrapper.INCREASE_DAMAGE.getName());
        sender.sendMessage(PotionEffectTypeWrapper.INVISIBILITY.getName());
        sender.sendMessage(PotionEffectTypeWrapper.JUMP.getName());
        sender.sendMessage(PotionEffectTypeWrapper.LEVITATION.getName());
        sender.sendMessage(PotionEffectTypeWrapper.LUCK.getName());
        sender.sendMessage(PotionEffectTypeWrapper.NIGHT_VISION.getName());
        sender.sendMessage(PotionEffectTypeWrapper.POISON.getName());
        sender.sendMessage(PotionEffectTypeWrapper.REGENERATION.getName());
        sender.sendMessage(PotionEffectTypeWrapper.SATURATION.getName());
        sender.sendMessage(PotionEffectTypeWrapper.SLOW.getName());
        sender.sendMessage(PotionEffectTypeWrapper.SLOW_FALLING.getName());
        sender.sendMessage(PotionEffectTypeWrapper.SPEED.getName());
        sender.sendMessage(PotionEffectTypeWrapper.UNLUCK.getName());
        sender.sendMessage(PotionEffectTypeWrapper.WATER_BREATHING.getName());
        sender.sendMessage(PotionEffectTypeWrapper.WEAKNESS.getName());
        sender.sendMessage(PotionEffectTypeWrapper.WITHER.getName());
        return true;
    }
}
