package com.ecom.amazon.Authentication.AuthDTO;

import jakarta.validation.constraints.*;

public class SignUpVendorDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "at least 3 letters are required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "email can't be blank")
    private String email;

    @NotNull(message = "Mobile is mandatory")
    @Min(value = 6000000000L, message = "first number should not less than 6 with 10 digits")
    @Max(value = 9999999999L, message = "last number should not more than 9 with 10 digits")
    private Long mobile; //don't use primitive 'long', because primitive long default value if 0, so it can't be null, if it can't be null then @NotNull will not work in it.

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "password must contain at least 8 character")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @Pattern(regexp = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}", message = "Invalid GST format")
    @NotBlank(message = "GST is mandatory")
    private String gst;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    @Override
    public String toString() {
        return "SignUpVendorDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", password='" + password + '\'' +
                ", gst='" + gst + '\'' +
                '}';
    }
}
