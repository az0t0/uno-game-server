package com.hgdroplet.unoserver.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hgdroplet.unoserver.domain.User;
import com.hgdroplet.unoserver.repository.UserRepository;
import com.hgdroplet.unoserver.service.UserService;

@Controller
// @RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("user")
    @ResponseBody
    public User addUser(@RequestParam("nickname") String nickname,
                        @RequestParam("password") String pw) {
        User user = new User();
        user.setNickname(nickname);
        user.setPassword(pw);
        userRepository.save(user);
        
        return user;
    }

    @GetMapping("user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("user/nickname/{nickname}")
    @ResponseBody
    public User getUserByNickname(@PathVariable("nickname") String nickname) {
        return userRepository.findByNickname(nickname).get();
    }

    @GetMapping("user")
    @ResponseBody
    public List<User> getUsers() {
        return userRepository.findAll();
    }

//////

    @GetMapping(value = "/users")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";
    }

    @GetMapping(value = "/users/new")
    public String createForm() {
        return "users/createUserForm";
    }

    @PostMapping(value = "/users/new")
    public String create(UserForm form) {
        User user = new User();
        user.setNickname(form.getName());
        userService.join(user);
        return "redirect:/";
    }
}
