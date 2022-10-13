package sparta.project.realboard.Dto;

import lombok.*;
import sparta.project.realboard.Entity.RealBoard;

@Setter
@Getter
@NoArgsConstructor
public class RealBoardDto {

    // 게시글 생성 / 게시글 수정에서 이용
    private String title;
    private String content;



}
