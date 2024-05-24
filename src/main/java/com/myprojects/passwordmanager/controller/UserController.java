package com.myprojects.passwordmanager.controller;

import com.myprojects.passwordmanager.dto.AuthDto;
import com.myprojects.passwordmanager.dto.JwtDto;
import com.myprojects.passwordmanager.dto.UserDto;
import com.myprojects.passwordmanager.security.CustomUserDetailsService;
import com.myprojects.passwordmanager.service.UserService;
import com.myprojects.passwordmanager.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    public UserController(UserService userService,
                          CustomUserDetailsService customUserDetailsService,
                          JwtTokenUtil jwtTokenUtil,
                          AuthenticationManager authenticationManager){
       this.userService = userService;
       this.userDetailsService = customUserDetailsService;
       this.jwtTokenUtil = jwtTokenUtil;
       this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody AuthDto authModel) throws Exception {

        authenticate(authModel.getEmail(), authModel.getPassword());

        //we need to generate the jwt token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new JwtDto(token), HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }

    }

    @PostMapping("/signUp")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return ResponseEntity.ok("user cretaed");
    }

}
