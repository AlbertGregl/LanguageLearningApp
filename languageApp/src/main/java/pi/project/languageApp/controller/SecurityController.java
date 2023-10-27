package pi.project.languageApp.controller;

import algebra.hr.bll.serviceImplementation.UserServiceImpl;
import algebra.hr.dal.entity.Authority;
import algebra.hr.dal.entity.User;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("security")
public class SecurityController {
    private final UserServiceImpl _userService;
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    public SecurityController(UserServiceImpl userService) {
        _userService = userService;
        //_userService.createAdminUser("ante", "admin");
    }

    @GetMapping("/loginUser")
    public String showMyLoginPage() {
        List<User> users = _userService.findAll();

        String usersCount = Integer.toString(users.size());
        logger.info("Created users: " + usersCount);
        for (User user:
             users) {
            logger.info("Password" + user.getPassword());
            logger.info("Username" + user.getUsername());

            for (Authority r:
                 user.getAuthorities()) {
                logger.info(r.getAuthority().toString());
            }
        }
        return "security/security-login";
    }

    @PostMapping("/manualLogout")
    public String manualLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/security/security-login?logoutManual=true";
    }

    @GetMapping("/showFormCreateUser")
    public String showFormCreateUser(Model theModel){

        //create the model attribute to bind form data
        User user = new User();
        theModel.addAttribute("user", user);

        return "security/security-registration";
    }


    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "security/security-registration";
        }

        try {
            // Try to find a user with the provided username
            User existingUserByUsername = _userService.findByUsername(user.getUsername().trim());

            // If we're here, it means a user with that username already exists
            model.addAttribute("errorMessage", "User with that username already exists!");
            return "security/security-registration";
        } catch (CustomNotFoundException e) {
            // This is expected as we're in the process of creating a new user. No action is needed.
        }

        _userService.createStudentUser(user.getUsername(),user.getPassword());

        return "redirect:/security/loginUser";
    }
}