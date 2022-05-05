package com.ofrs.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofrs.model.RegisterUser;
import com.ofrs.repository.RegisterUserRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString; 

import java.io.UnsupportedEncodingException;
import java.util.List;

//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofrs.model.RegisterUser;
import com.ofrs.repository.RegisterUserRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString; 

@Service
@Slf4j
@Transactional
public class RegisterUserService {
	
	Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(RegisterUserService.class);
	
	@Autowired
	RegisterUserRepository registerUserRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public RegisterUser addUser(RegisterUser user) {
		logger.info("***Add user service is called***");
		return registerUserRepository.save(user);
	}
	
	public List<RegisterUser> getAllUser() {
		logger.info("***Get all user service is called***");
		return registerUserRepository.findAll();
	}
	
	public RegisterUser getUser(int userId) {
		logger.info("***Get user service is called***");
		return registerUserRepository.findById(userId).orElse(null);
	}

	public void deleteUser(int userId) {
		logger.info("***Delete user service is called***");
		registerUserRepository.deleteById(userId);
	}
	
	public RegisterUser updateUser(int userId, RegisterUser user) {
		logger.info("***Update user service is called***");
		return registerUserRepository.save(user);
	}
	
	public RegisterUser findByuserEmail(String userEmail) {
		logger.info("***Find by user email service is called***");
		return registerUserRepository.findByUserEmail(userEmail);
	}
	
	public RegisterUser findByUserName(String userName) {
		logger.info("***Find by username service is called***");
		return registerUserRepository.findByUserName(userName);
	}
	
	
//--------------------------------------------------------------------------------------------------------------------------
	
	public void registerUser(RegisterUser user,String siteURL) {
        
        user.setRole("USER");
        String randomCode = RandomString.make(64);
      user.setVerificationCode(randomCode);
      user.setEnabled(false); 
      user.setAttempts(0);
      try {
             sendVerificationEmail(user, siteURL);
        } catch (MessagingException e) {
             e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
        }
        registerUserRepository.save(user);
        
  }



private void sendVerificationEmail(RegisterUser user, String siteURL) throws MessagingException, UnsupportedEncodingException {
          String toAddress = user.getUserEmail();
                 String content = "Dear [[name]],<br>"
                         + "Please click the link below to verify your registration:<br>"
                         + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                         + "Thank you,<br>"
                         + "Your company name.";
                  
                 MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      String subject = "Verify your account";
      helper.setFrom("Trng1@evolvingsols.com", "AirlineBookingSystem");
            helper.setTo(toAddress);
            helper.setSubject(subject);
             
            content = content.replace("[[name]]", user.getUserName());
      String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
                  
      content = content.replace("[[URL]]", verifyURL);
             
            helper.setText(content, true);
             
            javaMailSender.send(message);


        }
  //this method will verify the verification code link
  public boolean verify(String verificationCode) {
            RegisterUser user = registerUserRepository.findByVerificationCode(verificationCode);
             
            if (user == null || user.isEnabled()) {
                return false;
            } else {
                user.setVerificationCode(null);
                user.setEnabled(true);
                registerUserRepository.save(user);
                 
                return true;
            }
        }

	
//--------------------------------------------------------------------------------------------------------------------------


  public List<RegisterUser> getAllLockedUsers() {
		return registerUserRepository.findAllLockedusers();
	}
	
	
	public void unlockuserAccount(int id) {
		registerUserRepository.unlockUserAccount(id);
	}

}
