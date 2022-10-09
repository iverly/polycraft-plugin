package fr.do2021.polycraft.verification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class PlayerVerification {
        @Getter
        private UUID uniqueId;

        @Getter
        @Setter
        private VerificationState state;

        @Getter
        @Setter
        private String email;

        @Getter
        @Setter
        private String verificationCode;
}
