package sparta.project.realboard.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.project.realboard.Dto.RealBoardDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class RealBoard{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="boardid")
    @Id
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition="TEXT",nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false, name="userid")
    private User user;

    @Column
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column
    public LocalDateTime modifiedDate;

    public RealBoard(String title,String writer, String password, String content, User user){
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
        this.user = user;
    }

    public void update(RealBoardDto boarddto){
        this.title = boarddto.getTitle();
        this.content = boarddto.getContent();
        this.modifiedDate = LocalDateTime.now();
    }


}