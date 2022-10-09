package fr.do2021.polycraft.game.listeners;

import fr.do2021.polycraft.PolycraftPlugin;
import fr.do2021.polycraft.utils.ChatColor;
import fr.do2021.polycraft.utils.I18N;
import fr.do2021.polycraft.utils.StringUtils;
import fr.do2021.polycraft.verification.PlayerVerification;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@RequiredArgsConstructor
public class ChatListener implements Listener {

    private final PolycraftPlugin plugin;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        PlayerVerification playerVerification = this.plugin.getVerificationManager().getPlayerVerification(player.getUniqueId());

        switch (playerVerification.getState()) {
            case UNREGISTERED -> {
                e.setCancelled(true);
                String email = e.getMessage().trim();

                if (email.isEmpty() || email.isBlank()) {
                    player.sendMessage(I18N.Error.INCORRECT_EMAIL);
                    return;
                }

                if (!StringUtils.isUniversityEmail(email)) {
                    player.sendMessage(I18N.Error.INCORRECT_EMAIL);
                    return;
                }

                String verificationCode = this.plugin.getVerificationHandler().generateRandomVerificationCode();

                try {
                    this.plugin.getEmailSender().sendVerificationCodeEmail(email ,verificationCode);
                } catch (Exception ex) {
                    this.plugin.getLogger().severe("Unable to send email to player: " + ex.getMessage());
                    player.sendMessage(I18N.Error.ERROR_WHILE_SENDING_EMAIL);
                    return;
                }

                this.plugin.getVerificationHandler().setPlayerWaitingVerificationCode(player, email, verificationCode);
                player.sendMessage(I18N.Success.VERIFICATION_CODE_SENT);
            }
            case WAITING_VERIFICATION_CODE -> {
                e.setCancelled(true);
                String code = e.getMessage().trim();

                if (code.isEmpty() || code.isBlank()) {
                    player.sendMessage(I18N.Error.INCORRECT_EMAIL);
                    return;
                }

                if (!code.equals(playerVerification.getVerificationCode())) {
                    player.sendMessage(I18N.Error.INCORRECT_CODE);
                    return;
                }

                Bukkit.getScheduler().runTask(this.plugin, () -> {
                    this.plugin.getVerificationHandler().setPlayerRegistered(player);
                    player.sendMessage(I18N.Success.VERIFICATION_CODE_SUCCESS);
                });
            }
        }

        e.setFormat(ChatColor.GRAY + "%s : %s");
    }

}
