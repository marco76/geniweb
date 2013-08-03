package ch.genidea.geniweb.base.web.controller;


import ch.genidea.geniweb.base.domain.Role;
import ch.genidea.geniweb.base.domain.SecurityCode;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.repository.SecurityCodeRepository;
import ch.genidea.geniweb.base.repository.UserRepository;
import ch.genidea.geniweb.base.service.MailSenderService;
import ch.genidea.geniweb.base.service.MyUserDetailsService;
import ch.genidea.geniweb.base.utility.SecureUtility;
import ch.genidea.geniweb.base.utility.TypeActivationEnum;
import ch.genidea.geniweb.base.web.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityCodeRepository securityCodeRepository;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    UserDetailsService myUserDetailsService;



    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @RequestMapping("/base/public/signup")
    public String create(Model model) {
        model.addAttribute("user", new UserForm());

        return "/base/public/signup";
    }

    @RequestMapping(value = "/base/public/signup_confirm", method = RequestMethod.POST)
    @Transactional
    public String createUser(@ModelAttribute("user") @Valid UserForm form, BindingResult result) {
        if (!result.hasErrors()) {


            // check if email already exists
            if (userRepository.isEmailAlreadyExists(form.getUsername())){
                FieldError fieldError = new FieldError("user", "email", "email already exists");
                result.addError(fieldError);
                return "/base/public/signup";
            }
            User user = new User();
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            user.setScreeName(form.getScreenName());
            user.setUsername(form.getUsername());
            user.setEnabled(false);

            user.setPassword(encoder.encodePassword(form.getPassword(), user.getUsername()));
            Role role = new Role();
            role.setUser(user);
            role.setRole(2);

            SecurityCode securityCode = new SecurityCode();
            securityCode.setUser(user);
            securityCode.setTimeRequest(new Date());
            securityCode.setTypeActivationEnum(TypeActivationEnum.NEW_ACCOUNT);
            securityCode.setCode(SecureUtility.generateRandomCode());
            user.setRole(role);
            user.setSecurityCode(securityCode);

            userRepository.saveUser(user);
            //securityCodeRepository.persist(securityCode);
            mailSenderService.sendAuthorizationMail(user, user.getSecurityCode());



        } else {

            return "/base/public/signup";

        }

        return "redirect:/base/login";
    }

    @RequestMapping(value = "/base/user/profile")
    public String userProfile() {
        return "/base/user/profile";
    }

    @RequestMapping(value = "/base/user/searchContact")
    public String searchContact() {
        return "/base/user/searchContact";
    }

    @RequestMapping(value = "/base/user/get_contacts_list",
            method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Object[] getContactsList(@RequestParam("term") String query) {
        List<String> usernameList = userRepository.findUsername(query);

        return usernameList.toArray();
    }

    @RequestMapping(value = "/base/user/get",
            method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> get() {
        List<String> usernameList = userRepository.findUsername("");
        Map<String, String> result = new HashMap<String, String>();
        for (String username : usernameList) {
            result.put("label", username);

        }
        return result;
    }

    @RequestMapping(value = "/base/activation", method = RequestMethod.GET)
    @Transactional
    public String activation(@RequestParam String mail, @RequestParam String code ){
        if (userRepository.isSecurityCodeValid(mail, code)){
           User user = userRepository.findUserByEmail(mail);
            user.setEnabled(true);

            securityCodeRepository.deleteSecurityCode(user.getSecurityCode());
            user.setSecurityCode(null);
            userRepository.update(user);
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());

            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "/base/user/profile";
        }
        return "";

    }





}
