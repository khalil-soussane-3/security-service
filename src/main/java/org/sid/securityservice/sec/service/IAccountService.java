package org.sid.securityservice.sec.service;

import org.sid.securityservice.sec.dto.BaseResponse;
import org.sid.securityservice.sec.dto.ChangePasswordDto;
import org.sid.securityservice.sec.entities.AppRole;
import org.sid.securityservice.sec.entities.AppUser;

import java.util.List;

public interface IAccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
    boolean isExistByUsername(String username);
    BaseResponse changePassword(ChangePasswordDto changePasswordDto);
    BaseResponse newPassword(ChangePasswordDto changePasswordDto);

    BaseResponse deleteUserByUsername(String username);
}
