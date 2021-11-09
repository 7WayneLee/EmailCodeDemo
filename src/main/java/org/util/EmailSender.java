package org.util;

import org.domian.User;

import java.util.*;
import java.io.ObjectInputStream.GetField;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.MinimalHTMLWriter;

public class EmailSender {
    //发件人地址
    public static String senderAddress = "";
    //收件人地址
    //发件人账户名
    public static String senderAccount = "JpetStore";
    //发件人账户密码
    public static String senderPassword = "";
    public static String host = "smtp.qq.com";

    //    //发件人地址
//    public static String senderAddress = "3601677347@qq.com";
//    //收件人地址
//    public static String recipientAddress = "lumusen8209180538@163.com";
//    //发件人账户名
//    public static String senderAccount = "Lumusen";
//    //发件人账户密码
//    public static String senderPassword = "pdtzakwaeukecidb";
    public static String send(String email, String emailCode) {
        String recipientAddress = email;

        Properties props = new Properties();
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderAddress, senderPassword);
            }
        });

        // Set response content type
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(senderAddress));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));

            // Set Subject: header field
            message.setSubject("Welcome Pet Store!");

            // Now set the actual message
            message.setText("Verification code: "+emailCode);

            // Send message
            Transport.send(message);
            System.out.println("send");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    return emailCode;
    }
}
