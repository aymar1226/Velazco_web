package com.utp.spring.controllers;

import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Controller
public class ContactoController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/contacto")
    public String showContactForm(Model modelo, HttpSession session) {
        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));
        return "contacto_form";
    }


    @PostMapping("/contacto")
    public String submitContact(HttpServletRequest request,@RequestParam("attachment") MultipartFile multipartFile)
            throws MessagingException, UnsupportedEncodingException {


        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        String mailSubject = fullname + " has sent a message";
        String mailContent = "<p><b>Sender Name:</b> " + fullname + "</p>";
        mailContent += "<p><b>Sender E-mail:</b>  " + email + "</p>";
        mailContent += "<p><b>Subject:</b>  " + subject + "</p>";
        mailContent += "<p><b>Content:</b>  " + content + "</p>";
        mailContent += "<hr><img src='cid:logoImage' />";


        helper.setFrom("bonihd48@gmail.com", "Probando el java-meil");
        helper.setTo("jackaymar1226@gmail.com");

        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            InputStreamSource source = new InputStreamSource() {
                @Override
                public InputStream getInputStream() throws IOException {
                    return multipartFile.getInputStream();
                }
            };
            helper.addAttachment(fileName, source);
        }

        mailSender.send(message);

        return "redirect:/inicio";
    }
}
