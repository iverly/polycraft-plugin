package fr.do2021.polycraft.game.runnables;

import fr.do2021.polycraft.PolycraftPlugin;
import fr.do2021.polycraft.utils.I18N;
import fr.do2021.polycraft.verification.PlayerVerification;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class VerificationRunnable implements Runnable {

    private final PolycraftPlugin plugin;

    @Override
    public void run() {
        for (PlayerVerification unregisteredPlayer : this.plugin.getVerificationManager().getUnregisteredPlayers()) {
            Player player = Bukkit.getPlayer(unregisteredPlayer.getUniqueId());
            if (player != null) {
                player.sendMessage(I18N.CallToAction.EXPLANATION_MESSAGE);
            }
        }

        for (PlayerVerification waitingVerificationCodePlayer : this.plugin.getVerificationManager().getWaitingVerificationCodePlayers()) {
            Player player = Bukkit.getPlayer(waitingVerificationCodePlayer.getUniqueId());
            if (player != null) {
                player.sendMessage(I18N.CallToAction.INPUT_VERIFICATION_CODE);
            }
        }
    }
}
