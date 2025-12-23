package ma.zakaria.backend.services;

import ma.zakaria.backend.dtos.LoginRequest;
import ma.zakaria.backend.dtos.LoginResponse;
import ma.zakaria.backend.entities.User;
import ma.zakaria.backend.repositories.UserRepository;
import ma.zakaria.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Generate JWT token
            String token = jwtUtil.generateToken(loginRequest.getEmail());

            // Get user details
            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create and return response
            return new LoginResponse(
                    token,
                    user.getEmail(),
                    user.getUserName(),
                    "Login successful"
            );

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}