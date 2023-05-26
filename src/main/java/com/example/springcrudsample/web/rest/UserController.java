package com.example.springcrudsample.web.rest;

import com.example.springcrudsample.config.Constants;
import com.example.springcrudsample.domain.dto.UserDTO;
import com.example.springcrudsample.domain.entity.User;
import com.example.springcrudsample.repository.UserRepository;
import com.example.springcrudsample.security.Roles;
import com.example.springcrudsample.service.UserService;
import com.example.springcrudsample.exceptions.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final UserRepository userRepository;
    

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/register")
    @PreAuthorize("hasAuthority(\"" + Roles.ADMIN + "\")")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws BadRequestAlertException {
        log.debug("درخواست تعریف کاربر: {}", userDTO);

        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("این کاربر نامعتبر است", "userManagement", Constants.ID_NULL);
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new BadRequestAlertException("این نام کاربری قبلا در سیستم ثبت شده");
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new BadRequestAlertException("این ایمیل قبلا در سیستم ثبت شده");
        } else {
            User newUser = userService.createUser(userDTO);
            return ResponseEntity.ok(newUser);
        }
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAuthority(\"" + Roles.ADMIN + "\")")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) throws BadRequestAlertException {
        log.debug("درخواست ویرایش اطلاعات کاربر: {}", userDTO);
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new BadRequestAlertException("این ایمیل قبلا در سیستم ثبت شده");
        }
        existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new BadRequestAlertException("این نام کاربری قبلا در سیستم ثبت شده");
        }
        UserDTO updatedUser = userService.updateUser(userDTO).orElse(null);

        return ResponseEntity.ok(updatedUser);
    }


    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority(\"" + Roles.ADMIN + "\")")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        log.debug("درخواست لیست کاربران");
        final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/login/{login}")
    @PreAuthorize("hasAuthority(\"" + Roles.ADMIN + "\")")
    public ResponseEntity<UserDTO> getUser(@PathVariable @Pattern(regexp = Constants.LOGIN_REGEX) String login) throws BadRequestAlertException {
        log.debug("درخواست ورود به حساب کاربری: {}", login);
        UserDTO userDTO = userService.getUserWithAuthoritiesByLogin(login).map(UserDTO::new).orElse(null);
        if (userDTO == null)
            throw new BadRequestAlertException("اطلاعات ارسالی نامعتبر است");
        return ResponseEntity.ok(userDTO);
    }


    @DeleteMapping("/users/{login}")
    @PreAuthorize("hasAuthority(\"" + Roles.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@PathVariable @Pattern(regexp = Constants.LOGIN_REGEX) String login) {
        log.debug("درخواست حذف کاربر: {}", login);
        userService.deleteUser(login);
        return ResponseEntity.ok(null);
    }
}
