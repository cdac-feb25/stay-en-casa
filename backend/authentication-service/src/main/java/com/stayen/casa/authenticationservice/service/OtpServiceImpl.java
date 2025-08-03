package com.stayen.casa.authenticationservice.service;

import com.stayen.casa.authenticationservice.dto.OtpPasswordDTO;
import com.stayen.casa.authenticationservice.entity.Otp;
import com.stayen.casa.authenticationservice.enums.AuthError;
import com.stayen.casa.authenticationservice.exception.AuthException;
import com.stayen.casa.authenticationservice.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;

    private static final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public OtpServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public String generateSave6DigitOtp(String email) {
        int generatedOtp = secureRandom.nextInt(900000) + 100000;

        Otp otp = new Otp(email, String.valueOf(generatedOtp), LocalDateTime.now());
        otpRepository.save(otp);

        return String.valueOf(generatedOtp);
    }

    @Override
    public boolean verifyAndDeleteOtp(OtpPasswordDTO otpPasswordDTO) {
        Otp otp = otpRepository
                .findById(otpPasswordDTO.getEmail())
                .orElseThrow(() -> new AuthException(AuthError.OTP_VERIFICATION_FAILED));

        if((otp.getOtp().equals(otpPasswordDTO.getOtp())) == false) {
            throw new AuthException(AuthError.OTP_VERIFICATION_FAILED);
        }

        otpRepository.delete(otp);
        return true;
    }
}
