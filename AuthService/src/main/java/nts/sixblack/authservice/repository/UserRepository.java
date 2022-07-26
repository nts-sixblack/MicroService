package nts.sixblack.authservice.repository;

import nts.sixblack.authservice.mocel.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByEmail(String email);
    User findUserByUserId(long userId);
}