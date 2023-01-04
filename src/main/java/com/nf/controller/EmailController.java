package com.nf.controller;

import com.nf.dao.defination.AddCustomerDaoDefinition;
import com.nf.dto.MailDto;
import com.nf.service.MailServiceImple;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {


    @Autowired
    private MailServiceImple mailServiceImple;

    @Autowired
    private AddCustomerDaoDefinition addCustomerDaoDefinition;



    /*@RequestMapping("/writeEmail")*/
     @RequestMapping("/writeEmail/{customerId}")
    public String createEmail(Model model, @PathVariable("customerId")Long customerId){

        String mailId= addCustomerDaoDefinition.getEmail(customerId);

         model.addAttribute("mailId",mailId);
        model.addAttribute("mailDto", new MailDto());
        return "/writeEmail";
    }


    @PostMapping("views/writeEmail")
    public String sendMail(@ModelAttribute("mailDto")MailDto mailDto){

//        mailServiceImple.sendEmail(mailDto.getUserEmail(),mailDto.getSubject(),mailDto.getMessage());
        mailServiceImple.sendEmail(mailDto);
        return "Sent Successful";
    }

}
