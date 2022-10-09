package fr.do2021.polycraft.game.handlers;

import fr.do2021.polycraft.PolycraftPlugin;
import fr.do2021.polycraft.utils.ConfigUtils;
import fr.do2021.polycraft.utils.StringUtils;
import fr.do2021.polycraft.verification.PlayerVerification;
import fr.do2021.polycraft.verification.VerificationState;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class VerificationHandler {

    private final PolycraftPlugin plugin;

    public void initPlayer(Player player) {
        PlayerVerification playerVerification = ConfigUtils.getPlayerVerification(this.plugin.getConfig(), player);
        this.plugin.getVerificationManager().setPlayerVerification(playerVerification);
    }

    public void unloadPlayer(Player player) {
        this.plugin.getVerificationManager().removePlayerVerification(player.getUniqueId());
    }

    public void setPlayerWaitingVerificationCode(Player player, String email, String verificationCode) {
        PlayerVerification playerVerification = this.plugin.getVerificationManager().getPlayerVerification(player.getUniqueId());
        playerVerification.setEmail(email);
        playerVerification.setState(VerificationState.WAITING_VERIFICATION_CODE);
        playerVerification.setVerificationCode(verificationCode);
        ConfigUtils.savePlayerVerification(this.plugin.getConfig(), playerVerification);
    }

    public void setPlayerRegistered(Player player) {
        PlayerVerification playerVerification = this.plugin.getVerificationManager().getPlayerVerification(player.getUniqueId());
        playerVerification.setState(VerificationState.REGISTERED);
        ConfigUtils.savePlayerVerification(this.plugin.getConfig(), playerVerification);

        this.plugin.getPlayerHandler().setRegisteredProperties(player);
        this.plugin.getPlayerHandler().teleportToSpawn(player);
    }

    public String generateRandomVerificationCode() {
        return StringUtils.generateRandom(4) + "-" + StringUtils.generateRandom(4);
    }

}
