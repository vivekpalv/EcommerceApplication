package com.ecom.amazon.Entity;

import com.ecom.amazon.Enum.OtpType;
import jakarta.persistence.*;

@Entity
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long mobile;

    private String email;
    private String otp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OtpType otpType;

    public long getId() {
        return id;
    }

    public long getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getOtp() {
        return otp;
    }

    public OtpType getOtpType() {
        return otpType;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setOtpType(OtpType otpType) {
        this.otpType = otpType;
    }
}
