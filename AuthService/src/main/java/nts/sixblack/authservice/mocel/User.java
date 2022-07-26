package nts.sixblack.authservice.mocel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;
    private String name;
//    private String role;

    public User(String email, String password, String name, String role) {
        this.email = email;
        this.password = password;
        this.name = name;

    }
}
