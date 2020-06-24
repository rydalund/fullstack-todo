package se.ecutb.fullstacktodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.ecutb.fullstacktodo.data.AppUserRoleRepository;
import se.ecutb.fullstacktodo.data.TodoItemRepository;
import se.ecutb.fullstacktodo.dto.CreateAppUserForm;
import se.ecutb.fullstacktodo.entity.AppUser;
import se.ecutb.fullstacktodo.service.AppUserService;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AppUserController {

    private AppUserService appUserService;
    private AppUserRoleRepository appUserRoleRepository;
    private TodoItemRepository todoItemRepository;

    @Autowired
    public AppUserController(AppUserService appUserService, AppUserRoleRepository appUserRoleRepository, TodoItemRepository todoItemRepository) {
        this.appUserService = appUserService;
        this.appUserRoleRepository = appUserRoleRepository;
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/frequently-asked-questions")
    public String getFAQ(){
        return "frequently-asked-questions";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "login-form";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("form" , new CreateAppUserForm());
        return "register";
    }
    @PostMapping("/register")
    public String processForm(@Valid @ModelAttribute(name = "form") CreateAppUserForm userForm, BindingResult bindingResult){
        if(appUserService.findByUsername(userForm.getUsername()).isPresent()){
            FieldError error = new FieldError("form","username","Username already taken " + userForm.getUsername());
            bindingResult.addError(error);
        }
        if(!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            FieldError error = new FieldError("form","passwordConfirm", "Confirmation doesn't match password");
            bindingResult.addError(error);
        }
        if(bindingResult.hasErrors()){
            return "/register";
        }
        appUserService.registerAppUser(userForm);
        return "redirect:/login";
    }

    @GetMapping("/denied")
    public String getDenied(){
        return "denied";
    }

    @GetMapping("user-details")
    public String getUser(@ModelAttribute("list") AppUser appUser, @AuthenticationPrincipal UserDetails user, Model model ) {
        if (user != null) {

            if (user.getAuthorities().stream().allMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
                List<AppUser> appUserList = appUserService.findAll();
                model.addAttribute("appUserList", appUserList);
                return "user-details";
            }
            else if (user.getAuthorities().stream().allMatch(auth -> auth.getAuthority().equals("USER"))) {
                Optional<AppUser> appUser1 = appUserService.findByUsername(appUser.getUsername());
                model.addAttribute("list", appUser);
                return "user-details";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("user-details/users")
    public String getAppUser(@ModelAttribute("appUser") AppUser appUser, @AuthenticationPrincipal UserDetails user, Model model){
        if(user != null){
            if(user.getAuthorities().stream().allMatch(authority -> authority.getAuthority().equals("ADMIN"))) {
                Optional<AppUser> optionalAppUser = appUserService.findByUsername(appUser.getUsername());
                if (optionalAppUser.isPresent()) {
                    AppUser thisAppUser = appUserService.findByUsername(appUser.getUsername()).get();
                    model.addAttribute("appUser", thisAppUser);
                    return "users";
                } else {
                    throw new IllegalArgumentException("User was not found");
                }
            }
            else if (user.getAuthorities().stream().allMatch(authority -> authority.getAuthority().equals("USER"))) {
                return "redirect:/denied";
            }

        }
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}