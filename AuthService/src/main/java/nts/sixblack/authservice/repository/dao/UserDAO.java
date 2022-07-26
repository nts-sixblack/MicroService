package nts.sixblack.authservice.repository.dao;

import nts.sixblack.authservice.form.LoginForm;
import nts.sixblack.authservice.mocel.User;
import nts.sixblack.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    @Autowired
    UserRepository userRepository;

    public void newUser(User user){
        userRepository.save(user);
    }

    public User findByEmail(String email){
        try {
            List<User> user = userRepository.findUserByEmail(email);
            if (user == null) {
                return null;
            }
            return user.get(0);
        } catch (Exception e){
            return null;
        }
    }

    public User findById(long userId){
        try {
            User user = userRepository.findUserByUserId(userId);
            if (user == null) {
                return null;
            }
            return user;
        } catch (Exception e){
            return null;
        }
    }
}
