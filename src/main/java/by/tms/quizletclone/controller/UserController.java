package by.tms.quizletclone.controller;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getMain(Model model, @AuthenticationPrincipal User user) {
        String substring = user.getEmail().substring(0, user.getEmail().indexOf('@'));
        model.addAttribute("username", substring);
        model.addAttribute("folder", new Folder());
        return "index";
    }

    @GetMapping(path = "/login")
    public String getAuthorizationPage(Model model) {
        model.addAttribute("user", new User());
        return "authorization/login";
    }

    @GetMapping(path = "/reg")
    public String getRegistrationPage(Model model) {
        model.addAttribute("dto", new UserRegDTO());
        return "authorization/registration";
    }

    @PostMapping("/reg")
    public String postRegistrationUser(@ModelAttribute("dto")
                                       @Valid UserRegDTO dto,
                                       BindingResult bindingResult,
                                       Model model) {

        if (bindingResult.hasErrors()) {
            return "authorization/registration";
        }

        try {
            userService.registration(dto);
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return "authorization/registration";
        }

        return "authorization/login";

    }

    @GetMapping("activate/{code}")
    public String activate(@PathVariable String code) {
        userService.activateUser(code);
        return "redirect:/auth";
    }

}