package fr.do2021.polycraft.utils;

import fr.do2021.polycraft.PolycraftPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@RequiredArgsConstructor
public class EmailSender {

    private final PolycraftPlugin plugin;

    private Session session;

    public void init() {
        Configuration configuration = this.plugin.getConfig();

        this.session = Session.getInstance(ConfigUtils.getSMTPProperties(configuration), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return ConfigUtils.getSMTPAuthentication(configuration);
            }
        });
    }

    public void sendVerificationCodeEmail(String to, String verificationCode) throws Exception {
        Message message = new MimeMessage(this.session);
        message.setFrom(new InternetAddress(ConfigUtils.getSMTPFrom(this.plugin.getConfig())));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Ton code de vérification - Polycraft");

        String msg = "Bienvenue sur Polycraft !\n\nTon code de vérification : " + verificationCode + "\n\nÀ bientôt sur Polycraft !";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/plain; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

}
