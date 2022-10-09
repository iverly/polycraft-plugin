package fr.do2021.polycraft.utils;

public class I18N {

    public static class Error {
        public static final String INCORRECT_EMAIL = ChatColor.RED + "L'adresse email reseignée est incorrecte. Merci de renseigner votre adresse email universitaire.";

        public static final String INCORRECT_CODE = ChatColor.RED + "Le code de vérification n'est pas correct.";

        public static final String ERROR_WHILE_SENDING_EMAIL = ChatColor.RED + "Une erreur est survenue lors de l'envoi du email.";
    }

    public static class Success {
        public static final String VERIFICATION_CODE_SENT = ChatColor.GREEN + "Ton code de vérification vient d'être envoyé à ton email universitaire.";

        public static final String VERIFICATION_CODE_SUCCESS = ChatColor.GREEN + "Merci d'avoir validé ton compte. Tu peux maintenant jouer sans restriction sur le serveur.";
    }

    public static class CallToAction {
        public static final String EXPLANATION_MESSAGE = ChatColor.WHITE + "Bienvenue sur " + ChatColor.GOLD + "Polycraft !\n\n" + ChatColor.WHITE + "Afin de s'assurer que tous les joueurs viennent de Polytech Montpellier, nous allons t'envoyer un email contenant un code de vérification sur ton email universitaire (format: prenom.nom@etu.umontpellier.fr). Tu as juste à la renseigner dans le chat et de suivre les instructions. À très vite :)\n\nVos DO préférés.";

        public static final String INPUT_VERIFICATION_CODE = ChatColor.WHITE + "Renseigne le code de vérification reçu sur ton adresse email universitaire dans le chat pour jouer";
    }

}
