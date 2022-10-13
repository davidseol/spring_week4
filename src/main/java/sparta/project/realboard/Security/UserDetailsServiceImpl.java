package sparta.project.realboard.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sparta.project.realboard.Entity.User;
import sparta.project.realboard.Repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService : 진입");
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        // session.setAttribute("loginUser", user);
        // session 명은 자동으로 지어주는가?
        return new UserDetailsImpl(user);
    }
}