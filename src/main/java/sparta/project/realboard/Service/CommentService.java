package sparta.project.realboard.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import sparta.project.realboard.Dto.CommentRequestDto;
import sparta.project.realboard.Entity.Comment;
import sparta.project.realboard.Entity.RealBoard;
import sparta.project.realboard.Repository.CommentRepository;
import sparta.project.realboard.Repository.RealBoardRepository;
import sparta.project.realboard.Security.UserDetailsImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RealBoardRepository realBoardRepository;

    // 댓글 작성
    public Comment writecomment(CommentRequestDto commentRequestDto,
                                UserDetailsImpl userDetails) {

        RealBoard realBoard = realBoardRepository.findById(commentRequestDto.getPostid()).orElseThrow(
                () -> new NullPointerException("게시물이 존재하지 않습니다.")
        );

        Comment comment = new Comment(
                commentRequestDto.getContent(),
                userDetails.getUser().getUsername(),
                realBoard, // realBoard
                userDetails.getUser() // user
        );

        commentRepository.save(comment);
        return comment;
    }


    // 댓글 목록 조히
    public List<Comment> commentlist(){
        List<Comment> commentlist = commentRepository.findAll();
        return commentlist;
    }

    // 선택한 게시글에 있는 댓글 목록 조회
    public List<Comment> postincommentlist(Long id){
        List<Comment> commentlist = commentRepository.findAll();
        List<Comment> comments = new ArrayList<>();

        for(Comment comment : commentlist){
            if(comment.getRealBoard().getId().equals(id)){ // getRealboard().getId();
                comments.add(comment);
            }
        }
        return comments;
    }

    // 댓글 수정
    @Transactional
    public Comment updatecomment(Long id, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails){
        List<Comment> commentlist = commentRepository.findByRealBoard_Id(commentRequestDto.getPostid());

        if(commentlist == null){
            throw new NullPointerException("등록되지 않은 게시글입니다.");
        }

        Comment updatedcomment = new Comment();

        for(Comment comment : commentlist){
            if(comment.getId().equals(id)){
                if(!userDetails.getUser().getUsername().equals(comment.getAuthor())) {
                    throw new IllegalArgumentException("댓글 작성자가 아니기 때문에 수정할 수 없습니다.");
                }

                comment.update(commentRequestDto.getContent());
                updatedcomment = comment;
            }
        }

        return updatedcomment;
    }


    // 지정 댓글 삭제
    public String deletecomment(Long id, UserDetailsImpl userDetails){
        // 댓글 id 로 해당 댓글 불러오기
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 계정으로 작성한 댓글이 존재하지 않습니다.")
        );

        if(!comment.getUser().getId().equals(userDetails.getUser().getId())){
            throw new IllegalArgumentException("해당 댓글의 작성자가 아니기 때문에 삭제할 수 없습니다.");
        }

        commentRepository.deleteById(id);
        return "success";
    }

}
