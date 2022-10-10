package fr.do2021.polycraft.utils;

import fr.do2021.polycraft.verification.PlayerVerification;
import fr.do2021.polycraft.verification.VerificationState;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import javax.mail.PasswordAuthentication;
import java.util.Objects;
import java.util.Properties;

@RequiredArgsConstructor
public class ConfigUtils {

    public static PlayerVerification getPlayerVerification(Configuration configuration, Player player) {
        String rootKey = "player-data." + player.getUniqueId();
        if (!configuration.contains(rootKey)) {
            PlayerVerification playerVerification = new PlayerVerification(
                    player.getUniqueId(),
                    VerificationState.UNREGISTERED,
                    "",
                    ""
            );

            ConfigUtils.savePlayerVerification(configuration, playerVerification);
            return playerVerification;
        }

        return new PlayerVerification(
            player.getUniqueId(),
            VerificationState.valueOf(configuration.getString(rootKey + ".verification.state")),
            configuration.getString(rootKey + ".verification.email"),
            configuration.getString(rootKey + ".verification.verification-code")
        );
    }

    public static void savePlayerVerification(Configuration config, PlayerVerification playerVerification) {
        String rootKey = "player-data." + playerVerification.getUniqueId();
        config.set(rootKey + ".verification.state", playerVerification.getState().toString());
        config.set(rootKey + ".verification.email", playerVerification.getEmail());
        config.set(rootKey + ".verification.verification-code", playerVerification.getVerificationCode());
        ConfigUtils.saveConfig();
    }

    public static Properties getSMTPProperties(Configuration configuration) {
        String rootKey = "smtp";
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", configuration.getString(rootKey + ".secure"));
        properties.put("mail.smtp.host", configuration.getString(rootKey + ".host"));
        properties.put("mail.smtp.port", configuration.getString(rootKey + ".port"));
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        return properties;
    }

    public static PasswordAuthentication getSMTPAuthentication(Configuration configuration) {
        String rootKey = "smtp";

        return new PasswordAuthentication(
            configuration.getString(rootKey + ".username"),
            configuration.getString(rootKey + ".password")
        );
    }

    public static String getSMTPFrom(Configuration configuration) {
        return configuration.getString("smtp.from");
    }

    public static void saveConfig() {
        Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Polycraft")).saveConfig();
    }

}
