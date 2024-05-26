package com.myprojects.passwordmanager.controller;

import com.myprojects.passwordmanager.dto.PasswordDto;
import com.myprojects.passwordmanager.service.PassWordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PassWordController {

    private final PassWordService passWordService;
    public PassWordController(PassWordService passWordService){
        this.passWordService = passWordService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> savePassWord(@RequestBody PasswordDto passwordDto){
        passWordService.savePassWord(passwordDto);

        return ResponseEntity.ok().body("PassWord Saved Successfully");
    }
    @GetMapping("/getPass/{appName}")
    public ResponseEntity<PasswordDto> getPassWord(@PathVariable String appName){
        PasswordDto passWord = passWordService.getPassWord(appName);
        return ResponseEntity.ok(passWord);
    }
}
