package fr.do2021.polycraft;

import fr.do2021.polycraft.game.handlers.PlayerHandler;
import fr.do2021.polycraft.game.handlers.VerificationHandler;
import fr.do2021.polycraft.game.listeners.ChatListener;
import fr.do2021.polycraft.game.listeners.ConnectionListener;
import fr.do2021.polycraft.game.managers.VerificationManager;
import fr.do2021.polycraft.game.runnables.VerificationRunnable;
import fr.do2021.polycraft.utils.EmailSender;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class PolycraftPlugin extends JavaPlugin {

    @Getter
    private VerificationManager verificationManager;

    @Getter
    private PlayerHandler playerHandler;

    @Getter
    private VerificationHandler verificationHandler;

    @Getter
    private EmailSender emailSender;

    @Getter
    private final HashMap<UUID, String> waitingRegistrationPlayers = new HashMap<>();

    @Override
    public void onLoad() {
        this.getLogger().info("Loading config ...");
        this.saveDefaultConfig();
        this.getLogger().info("Initialize email sender ...");
        this.emailSender = new EmailSender(this);
        this.emailSender.init();
    }

    @Override
    public void onEnable() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        // set variables
        this.verificationManager = new VerificationManager();
        this.playerHandler = new PlayerHandler(this);
        this.verificationHandler = new VerificationHandler(this);

        // register listeners
        pluginManager.registerEvents(new ConnectionListener(this), this);
        pluginManager.registerEvents(new ChatListener(this), this);

        // run tasks
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new VerificationRunnable(this), 0, 200L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
