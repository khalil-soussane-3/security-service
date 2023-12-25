package org.sid.securityservice.sec.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.securityservice.sec.dto.BaseResponse;
import org.sid.securityservice.sec.dto.ChangePasswordDto;
import org.sid.securityservice.sec.entities.AppRole;
import org.sid.securityservice.sec.entities.AppUser;
import org.sid.securityservice.sec.exception.RecordNotFoundException;
import org.sid.securityservice.sec.repository.AppRoleRepository;
import org.sid.securityservice.sec.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountService implements IAccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pwd = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pwd));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        Optional<AppUser> appUserOptional = Optional.ofNullable(appUserRepository.findByUsername(username));
        if (appUserOptional.isPresent()){
            return appUserOptional.get();
        }
        log.warn("Le User avec le username : " + username + " n'existe pas");
        throw new RecordNotFoundException("Le User avec le username : " + username + " n'existe pas");
    }


    @Override
    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }

    public boolean isExistByUsername(String username){
        return  appUserRepository.existsAppUserByUsername(username);
    }

    @Override
    public BaseResponse changePassword(ChangePasswordDto changePasswordDto){
        AppUser appUser = this.loadUserByUsername(changePasswordDto.getUsername());
        boolean passwordsMatch = passwordEncoder.matches(changePasswordDto.getOldPassword(), appUser.getPassword());
        if (Boolean.FALSE.equals(passwordsMatch)){
            return new BaseResponse(false,"L'ancien mot de passe que vous avez saisie n'est pas correcte !");
        }

        appUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        appUserRepository.save(appUser);
        return BaseResponse.success();

    }

    @Override
    public BaseResponse newPassword(ChangePasswordDto changePasswordDto) {
        AppUser appUser = this.loadUserByUsername(changePasswordDto.getUsername());
        appUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        appUserRepository.save(appUser);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse deleteUserByUsername(String username) {
        AppUser appUser = this.loadUserByUsername(username);
        appUserRepository.delete(appUser);
        return BaseResponse.success();
    }
}
