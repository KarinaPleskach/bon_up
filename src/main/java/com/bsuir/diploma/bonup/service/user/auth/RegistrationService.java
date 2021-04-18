package com.bsuir.diploma.bonup.service.user.auth;

import com.bsuir.diploma.bonup.dto.model.user.auth.EmailCodeDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.EmailDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.NewPasswordDto;
import com.bsuir.diploma.bonup.dto.model.user.auth.RegUserDto;

public interface RegistrationService {
    void createNewUser(RegUserDto regUserDto, String lang);
    void checkMailCode(EmailCodeDto emailCodeDto, String lang);
    String generateNewToken(String email, String lang);
    void resendCodeAndNullToken(EmailDto emailDto, String lang);
    void newPassword(NewPasswordDto newPassword, String lang);
    String generateNewTokenWithToken(String token, String lang);
    String login(RegUserDto userDto, String lang);
}
