package com.spring.security.login;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication{

    public static void main(String[] args) {

        SpringApplication.run(LoginApplication.class, args);

    }


//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        User user = new User();
//        user.setUsername("trinhvandat");
//        user.setPassword(passwordEncoder.encode("trinhvandat"));
//        userRepository.save(user);
//        System.out.println(user);
//
//    }
}
