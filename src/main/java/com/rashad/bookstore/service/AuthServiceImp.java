package com.rashad.bookstore.service;

import com.rashad.bookstore.entity.ERole;
import com.rashad.bookstore.entity.Role;
import com.rashad.bookstore.entity.User;
import com.rashad.bookstore.exception.DuplicateUsernameException;
import com.rashad.bookstore.repository.RoleRepository;
import com.rashad.bookstore.repository.UserRepository;
import com.rashad.bookstore.responses.JwtResponse;
import com.rashad.bookstore.responses.LoginRequest;
import com.rashad.bookstore.responses.Message;
import com.rashad.bookstore.responses.RegisterRequest;
import com.rashad.bookstore.security.details.UserDetailsImpl;
import com.rashad.bookstore.security.jwt.JwtUtils;
import com.sun.xml.bind.marshaller.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication= authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtResponse response = new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
        return  response;
    }

    @Override
    public User registerAsPublisher(RegisterRequest request) {
        if (userRepository.existsByUserName(request.getUsername())){
            throw new DuplicateUsernameException(Message.DUPLICATE_USERNAME);

        }

        User user = User.builder()
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Role rolePublisher = roleRepository.findByName(ERole.ROLE_PUBLISHER)
                .orElse(Role.builder().name(ERole.ROLE_PUBLISHER).build());

        Set<Role> roles = new HashSet<>();
        roles.add(rolePublisher);
        roleRepository.saveAll(roles);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User registerAsUser(RegisterRequest request) {
        if (userRepository.existsByUserName(request.getUsername())){
            throw new DuplicateUsernameException(Message.DUPLICATE_USERNAME);
        }

        User user = User.builder()
                .userName(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Role roleUser = roleRepository.findByName(ERole.ROLE_USER)
                .orElse(Role.builder().name(ERole.ROLE_USER).build());

        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        roleRepository.saveAll(roles);
        user.setRoles(roles);
        return userRepository.save(user);

    }
}
