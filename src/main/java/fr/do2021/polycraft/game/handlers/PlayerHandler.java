package fr.do2021.polycraft.game.handlers;

import fr.do2021.polycraft.PolycraftPlugin;
import fr.do2021.polycraft.utils.ChatColor;
import fr.do2021.polycraft.utils.I18N;
import fr.do2021.polycraft.verification.PlayerVerification;
import fr.do2021.polycraft.verification.VerificationState;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class PlayerHandler {

    private final PolycraftPlugin plugin;

    public void initPlayer(Player player) {
        this.broadcast(ChatColor.GRAY + player.getName() + " a rejoint le serveur");
    }

    public void unloadPlayer(Player player) {
        this.broadcast(ChatColor.GRAY + player.getName() + " a quitté le serveur");
    }

    public void setRegisteredProperties(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setSaturation(20f);
        player.setFoodLevel(20);
        player.setHealth(player.getMaxHealth());
        player.setExp(0);
        player.setFlying(false);
        player.setWalkSpeed(0.2f);
        player.setFlySpeed(0.2f);
        player.removePotionEffect(PotionEffectType.DARKNESS);
    }

    public void teleportToSpawn(Player player) {
        player.teleport(player.getWorld().getSpawnLocation());
    }

    private void setUnregisteredProperties(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.setFoodLevel(20);
        player.setHealth(player.getMaxHealth());
        player.setExp(0);
        player.setFlying(true);
        player.setFlySpeed(0f);
        player.setWalkSpeed(0f);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 99999999, 100));
    }

    private void teleportToVoid(Player player) {
        Location location = new Location(player.getWorld(), 0f, -100f, 0f);
        player.teleport(location);
    }

    public void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.GOLD + "[Polycraft] " + ChatColor.RESET + message);
    }

}
