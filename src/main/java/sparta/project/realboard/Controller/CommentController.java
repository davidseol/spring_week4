package sparta.project.realboard.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.project.realboard.Dto.CommentRequestDto;
import sparta.project.realboard.Entity.Comment;
import sparta.project.realboard.Response.ListResponse;
import sparta.project.realboard.Response.SingleResponse;
import sparta.project.realboard.Security.UserDetailsImpl;
import sparta.project.realboard.Service.CommentService;
import sparta.project.realboard.Service.ResponseService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    final private ResponseService responseService;

    // 댓글 생성
    @PostMapping("/api/comment")
    public SingleResponse<Comment> writecomment(@RequestBody CommentRequestDto commentRequestDto,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        return responseService.getSingleResponse(commentService.writecomment(commentRequestDto, userDetails));
    }


    // 댓글 목록 조회
    @GetMapping("/api/comments")
    public ListResponse<Comment> commentlist(){
        return responseService.getListResponse(commentService.commentlist());
    }


    // 선택한 게시글에 있는 댓글 목록 조회
    // {id}는 등록된 게시글 id
    @GetMapping("/api/comments/{id}")
    public ListResponse<Comment> postincommentlist(@PathVariable Long id){
        return responseService.getListResponse(commentService.postincommentlist(id));
    }

    // 댓글 수정
    // {id}는 댓글 id, postid는 게시글 id
    @PutMapping("/api/comment/{id}")
    public SingleResponse<Comment> updatecomment(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return responseService.getSingleResponse(commentService.updatecomment(id, commentRequestDto, userDetails));
    }


    // 댓글 삭제
    // {id}는 댓글 id
    @DeleteMapping("/api/comment/{id}")
    public SingleResponse<String> deletecomment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return responseService.getSingleResponse(commentService.deletecomment(id, userDetails));
    }
}
