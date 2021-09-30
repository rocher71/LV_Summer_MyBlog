package com.example.myblog.user;

import com.example.myblog.user.form.SignUpForm;
import com.example.myblog.user.form.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(Long userId, UserForm userForm) {
        User user = findById(userId);
        user.setName(userForm.getName());
        user.setType(userForm.getType());
    }

    //암호화 필요
    public User createUser(SignUpForm signUpForm) {
        String encodedPassword = passwordEncoder.encode(signUpForm.getPassword());

        User user = User.builder()
                .userId(signUpForm.getUserId())
                .password(encodedPassword)
                .name(signUpForm.getName())
                .type("USER")
                .build();
        
        save(user);
        
        return null;
    }

    public void login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( //토큰 생성
                new UserAccount(user), //유저 찾기
                passwordEncoder.encode(user.getPassword()),
                List.of(new SimpleGrantedAuthority(user.getType()))
        );
        SecurityContextHolder.getContext().setAuthentication(token); //로그인 정보 넣어줌
        //모든 과정을 securitycontextholder가 다 해줌
    }

    public User findByUserId(String userId){
        return userRepository.findByUserId(userId).orElseThrow(RuntimeException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = findByUserId(userId);
        return new UserAccount(user);
    }
}
