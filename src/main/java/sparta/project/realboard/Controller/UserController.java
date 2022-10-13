package sparta.project.realboard.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.project.realboard.Dto.LoginRequestDto;
import sparta.project.realboard.Dto.UserRequestDto;
import sparta.project.realboard.Entity.User;
import sparta.project.realboard.Jwt.JwtProperties;
import sparta.project.realboard.Repository.UserRepository;
import sparta.project.realboard.Response.SingleResponse;
import sparta.project.realboard.Security.UserDetailsImpl;
import sparta.project.realboard.Service.ResponseService;
import sparta.project.realboard.Service.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ResponseService responseService;


    // 회원가입
    @PostMapping("/user/register")
    public SingleResponse<User> registeraccount (@RequestBody UserRequestDto userRequestDto) {
        return responseService.getSingleResponse(userService.registeraccount(userRequestDto));
    }

    // 임시 회원 조회
    @GetMapping("/user/{id}")
    public SingleResponse<Optional<User>> userfind(@PathVariable Long id) {
        return responseService.getSingleResponse(userRepository.findById(id));
    }


}
