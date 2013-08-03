package ch.genidea.geniweb.base.web.controller;

import ch.genidea.geniweb.base.component.UserSessionComponent;
import ch.genidea.geniweb.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AccessController {

    @Autowired
    private UserSessionComponent userSessionComponent;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/base/login")
    public String login(Model model, @RequestParam(required=false) String message){
        model.addAttribute("message", message);
        return "base/access/login";
    }

    @RequestMapping("/base/login/success")
    public String loginSuccess(){
        userSessionComponent.setCurrentUser(userRepository.findUserByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        return "redirect:/base/user/profile";
    }

    @RequestMapping(value = "/base/denied")
    public String denied() {
        return "/base/access/denied";
    }
    @RequestMapping(value = "/base/login/failure")
    public String loginFailure() {
        String message = "Login Failure!";
        return "redirect:/base/login?message="+message;
    }
    @RequestMapping(value = "/base/logout/success")
    public String logoutSuccess() {
        return "/base/static/logout";
    }

    /** Login form with error. */
    @RequestMapping("/base/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/base/access/login";
    }

    @RequestMapping("/base/error/error")
    public String loginError() {

        return "/base/error/error";
    }

}
