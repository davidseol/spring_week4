package sparta.project.realboard.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import sparta.project.realboard.Dto.LoginRequestDto;
import sparta.project.realboard.Dto.UserRequestDto;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="userid")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime modifiedAt;


    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
    public User(String username, String password, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public User(LoginRequestDto loginRequestDto){
        this.username = loginRequestDto.getUsername();
        this.password = loginRequestDto.getPassword();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }


}
