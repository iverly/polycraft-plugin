package fr.do2021.polycraft.game.listeners;

import fr.do2021.polycraft.PolycraftPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class ConnectionListener implements Listener {

    private final PolycraftPlugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();

        // init player
        this.plugin.getPlayerHandler().initPlayer(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Player player = e.getPlayer();

        // init player
        this.plugin.getPlayerHandler().unloadPlayer(player);
    }

}
