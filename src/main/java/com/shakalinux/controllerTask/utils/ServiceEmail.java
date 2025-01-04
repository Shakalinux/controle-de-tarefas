package com.shakalinux.controllerTask.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

import java.io.File;

public class ServiceEmail {

    public void servicoEmail(String email, String nomeUsuario, String nomeTarefa, String dataLembrete) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("assistente.tarefas@gmail.com", "spmnhxrvmjkebpcf");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("assistente.tarefas@gmail.com"));


            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));


            message.setSubject("Lembrete: Tarefa Pendente - " + nomeTarefa);


            String emailBody = "Olá " + nomeUsuario + ",\n\n"
                    + "Este é um lembrete para a seguinte tarefa:\n\n"
                    + "Tarefa: " + nomeTarefa + "\n"
                    + "Data do Lembrete: " + dataLembrete + "\n\n"
                    + "Não se esqueça de realizar a tarefa até o prazo.\n"
                    + "Atenciosamente,\n"
                    + "Assistente de Tarefas";

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailBody);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);


            message.setContent(multipart);
            Transport.send(message);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
