package nts.sixblack.authservice.controller;

import nts.sixblack.authservice.form.LoginForm;
import nts.sixblack.authservice.form.RegisterForm;
import nts.sixblack.authservice.jwt.JwtTokenProvider;
import nts.sixblack.authservice.mocel.User;
import nts.sixblack.authservice.modelResponse.ResponseObject;
import nts.sixblack.authservice.service.UserService;
import nts.sixblack.authservice.util.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginForm loginForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getEmail(),
                        loginForm.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(customUserDetail);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "login success", "Token: Bearer "+token)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegisterForm registerForm){
        if (userService.newUser(registerForm)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Register success","")
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject(400, "Register fail", "This email already exist")
        );
    }

    @GetMapping("/")
    public String home(){
        return "home auth controller, port 8080";
    }
}
