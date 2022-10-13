package sparta.project.realboard.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long postid;
    private String content;
}
