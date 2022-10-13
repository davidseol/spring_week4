package sparta.project.realboard.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import sparta.project.realboard.Dto.LoginRequestDto;
import sparta.project.realboard.Dto.UserRequestDto;
import sparta.project.realboard.Entity.User;
import sparta.project.realboard.Entity.UserRoleEnum;
import sparta.project.realboard.Jwt.JwtProperties;
import sparta.project.realboard.Repository.UserRepository;
import sparta.project.realboard.Security.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AOKWKK2/2KDOOGLLS/WPPLDOOSIFISKDLL";


    // 회원가입
    public User registeraccount(UserRequestDto userRequestDto){
        // 아이디 중복확인
        Pattern pattern1 = Pattern.compile("^([a-zA-Z0-9]*)$");
        String username = userRequestDto.getUsername();

        if(userRepository.findByUsername(userRequestDto.getUsername()) != null){
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }else if(!pattern1.matcher(userRequestDto.getUsername()).matches() || userRequestDto.getUsername().length() < 4 || userRequestDto.getUsername().length() > 12){
            throw new IllegalArgumentException("형식에 맞지않는 닉네임입니다.");
        }

        String password = userRequestDto.getPassword();
        Pattern pattern2 = Pattern.compile("^([a-z0-9]*)$");

        // 비밇번호 정규식 구성
        if(password.length() < 4 || password.length() > 32 || !pattern2.matcher(password).matches()){
            throw new IllegalArgumentException("형식에 맞지않는 비밀번호입니다.");
        }else if(!password.equals(userRequestDto.getPasswordConfirm())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        password = passwordEncoder.encode(password);

        if (userRequestDto.isAdmin()) {
            if (!userRequestDto.getAdminToken().equals(ADMIN_TOKEN)) throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);

        return user;

    }

}
