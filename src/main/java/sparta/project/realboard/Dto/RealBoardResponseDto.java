package sparta.project.realboard.Dto;

import lombok.*;
import sparta.project.realboard.Entity.RealBoard;
import sparta.project.realboard.Entity.User;
import sparta.project.realboard.Repository.UserRepository;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class RealBoardResponseDto {

    private UserRepository userRepository;

    private Long id;
    private String title;

    private String writer;
//    private User writer;
//    private String password;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public RealBoardResponseDto (RealBoard realBoard){
        this.id = realBoard.getId();
        this.title = realBoard.getTitle();
        this.writer = realBoard.getWriter();
//        this.writer = userRepository.findByUsername(realBoard.getWriter());
//        this.password = realBoard.getPassword();
        this.content = realBoard.getContent();
        this.createdDate = realBoard.getCreatedDate();
        this.modifiedDate = realBoard.getModifiedDate();

        System.out.println("id = " + id);
        System.out.println("title = " + title);
        System.out.println("writer = " + writer);
//        System.out.println("작성 유저 : " + userRepository.findByUsername(realBoard.getWriter()));
        System.out.println("content = " + content);
        System.out.println("createdDate = " + createdDate);
        System.out.println("modifiedDate = " + modifiedDate);
    }

}
