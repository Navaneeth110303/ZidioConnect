package com.zidio.connect.dto;

public class AuthDtos {
    public static class LoginRequest { public String email; public String password; }
    public static class LoginResponse { public String token; public String role; public String email; }
    public static class RegisterRequest { public String email; public String password; public String fullName; public String role; }
}
