package entity;

import javax.mail.MessagingException;

import manager.MailHelper;

public class MailTest {

	public static void main(String[] args) {
		try {
			MailHelper.sendMail("gidon9123@gmail.com", "Hey", "BLALA");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
