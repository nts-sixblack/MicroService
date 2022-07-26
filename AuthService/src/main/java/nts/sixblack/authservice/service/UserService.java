package nts.sixblack.authservice.service;

import nts.sixblack.authservice.form.LoginForm;
import nts.sixblack.authservice.form.RegisterForm;
import nts.sixblack.authservice.mocel.User;
import nts.sixblack.authservice.repository.dao.UserDAO;
import nts.sixblack.authservice.util.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDAO;

    public boolean newUser(RegisterForm registerForm){
        if (existEmail(registerForm.getEmail())) {
            return false;
        }
        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setName(registerForm.getName());
        userDAO.newUser(user);
        return true;
    }

    private boolean existEmail(String email){
        if (userDAO.findByEmail(email) == null) {
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (existEmail(username)){
            return new CustomUserDetail(userDAO.findByEmail(username));
        }
        throw new UsernameNotFoundException(username);
    }

    public UserDetails getUserDetailById(long userId){
        return new CustomUserDetail(userDAO.findById(userId));
    }
}
