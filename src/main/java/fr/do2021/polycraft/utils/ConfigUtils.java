package fr.do2021.polycraft.utils;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.Configuration;

import javax.mail.PasswordAuthentication;
import java.util.Objects;
import java.util.Properties;

@RequiredArgsConstructor
public class ConfigUtils {

    public static Properties getSMTPProperties(Configuration configuration) {
        String rootKey = "smtp";
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", configuration.getString(rootKey + ".secure"));
        properties.put("mail.smtp.host", configuration.getString(rootKey + ".host"));
        properties.put("mail.smtp.port", configuration.getString(rootKey + ".port"));
        properties.put("mail.smtp.ssl.trust", configuration.getString(rootKey + ".host"));

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
