package fr.do2021.polycraft.game.managers;

import fr.do2021.polycraft.verification.PlayerVerification;
import fr.do2021.polycraft.verification.VerificationState;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class VerificationManager {

    private final HashMap<UUID, PlayerVerification> playerVerificationHashMap = new HashMap<>();

    public void setPlayerVerification(PlayerVerification playerVerification) {
        this.playerVerificationHashMap.put(playerVerification.getUniqueId(), playerVerification);
    }

    public void removePlayerVerification(UUID uniqueId) {
        this.playerVerificationHashMap.remove(uniqueId);
    }

    public PlayerVerification getPlayerVerification(UUID uniqueId) {
        return this.playerVerificationHashMap.get(uniqueId);
    }

    public Set<PlayerVerification> getUnregisteredPlayers() {
        return this.playerVerificationHashMap
                .values()
                .stream()
                .filter(v -> v.getState() == VerificationState.UNREGISTERED)
                .collect(Collectors.toSet());
    }

    public Set<PlayerVerification> getWaitingVerificationCodePlayers() {
        return this.playerVerificationHashMap
                .values()
                .stream()
                .filter(v -> v.getState() == VerificationState.WAITING_VERIFICATION_CODE)
                .collect(Collectors.toSet());
    }

}
