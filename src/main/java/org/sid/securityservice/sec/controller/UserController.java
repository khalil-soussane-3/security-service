package org.sid.securityservice.sec.controller;

import org.sid.securityservice.sec.dto.UserDto;
import org.sid.securityservice.sec.entities.AppUser;
import org.sid.securityservice.sec.service.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AccountService accountService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/addNewUser")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String saveUser (@RequestBody UserDto userDto){
        if(userDto.getPassword().equals(userDto.getConfirmpassword())) {
            AppUser appUser1 = new AppUser(0L, userDto.getUsername(), userDto.getPassword(),  new ArrayList<>());
            accountService.addNewUser(appUser1);
            if(accountService.isExistByUsername(appUser1.getUsername())){
                accountService.addRoleToUser(appUser1.getUsername(), userDto.getRole());
            }
            return "success";
        }else return "error" ;
    }


}
