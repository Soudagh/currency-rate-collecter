package org.currency.service;

import lombok.RequiredArgsConstructor;
import org.currency.data.dto.SignUpRequest;
import org.currency.data.entity.User;
import org.currency.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public User signUp(SignUpRequest signUpRequest) {
    var createdUser = new User()
        .setEmail(signUpRequest.getEmail())
        .setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    return userRepository.save(createdUser);
  }

  public User getCurrentUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
