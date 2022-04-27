package com.rashad.bookstore.security.details;

import com.rashad.bookstore.repository.UserRepository;
import com.rashad.bookstore.entity.User;
import com.rashad.bookstore.responses.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException(Message.BAD_CREDENTIAL));
        return UserDetailsImpl.build(user);
    }
}

