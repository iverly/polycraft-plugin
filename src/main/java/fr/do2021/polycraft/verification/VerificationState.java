package fr.do2021.polycraft.verification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VerificationState {

    REGISTERED,
    WAITING_VERIFICATION_CODE,
    UNREGISTERED;

}
