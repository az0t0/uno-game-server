package com.hgdroplet.unoserver.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.hgdroplet.unoserver.domain.User;
import com.hgdroplet.unoserver.repository.UserRepository;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

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
}
