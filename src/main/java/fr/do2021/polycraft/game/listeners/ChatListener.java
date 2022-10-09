package fr.do2021.polycraft.game.listeners;

import fr.do2021.polycraft.PolycraftPlugin;
import fr.do2021.polycraft.utils.ChatColor;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@RequiredArgsConstructor
public class ChatListener implements Listener {

    private final PolycraftPlugin plugin;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setFormat(ChatColor.GRAY + "%s : %s");
    }

}
