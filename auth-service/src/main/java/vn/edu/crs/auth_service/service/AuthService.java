package vn.edu.crs.auth_service.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.crs.auth_service.config.JwtUtil;
import vn.edu.crs.auth_service.dto.request.LoginRequestDTO;
import vn.edu.crs.auth_service.dto.response.LoginResponseDTO;
import vn.edu.crs.auth_service.entity.User;
import vn.edu.crs.auth_service.handler.InvalidCredentialsException;
import vn.edu.crs.auth_service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Sai username hoac password"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword()))
        {
            throw new InvalidCredentialsException("Sai username hoac password");
        }
        String token = jwtUtil.generateToken(user.getUsername(),
                user.getRole());
        return new LoginResponseDTO(token, user.getUsername(),
                user.getRole());
    }
}