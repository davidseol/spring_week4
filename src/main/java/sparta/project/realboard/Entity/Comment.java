package sparta.project.realboard.Entity;

import lombok.*;
import net.bytebuddy.asm.Advice;
import net.minidev.json.annotate.JsonIgnore;
import sparta.project.realboard.Dto.CommentRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @Id
    private Long id;

    @Column
    private String author;

    @Column
    private String content;

    @Column
    private LocalDateTime createcommenttime = LocalDateTime.now();

    // 수정 시간은 초기에 없도록 설정
    @Column
    private LocalDateTime updatecommenttime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="boardid")
    private RealBoard realBoard;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userid")
    private User user;

    // 예시 형식에 맞게 게시글, 유저 정보는 안나오고 작성자만 추가되어 댓글 정보 출력
    public Comment(String content, String author){
        this.content = content;
        this.author = author;
    }


    // 댓글, 게시글, 회원 정보 모두 출력
    public Comment(String content, String author, RealBoard realBoard, User user){
        this.content = content;
        this.author = author;
        this.realBoard = realBoard;
        this.user = user;
    }


    public void update(String content){
        this.content = content;
        this.updatecommenttime = LocalDateTime.now();
    }

}
