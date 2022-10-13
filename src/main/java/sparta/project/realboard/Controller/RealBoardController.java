package sparta.project.realboard.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.project.realboard.Dto.RealBoardDto;
import sparta.project.realboard.Dto.RealBoardResponseDto;
import sparta.project.realboard.Entity.RealBoard;
import sparta.project.realboard.Repository.RealBoardRepository;
import sparta.project.realboard.Response.ListResponse;
import sparta.project.realboard.Response.SingleResponse;
import sparta.project.realboard.Security.UserDetailsImpl;
import sparta.project.realboard.Service.RealBoardService;
import sparta.project.realboard.Service.ResponseService;

@RequiredArgsConstructor
@RestController
public class RealBoardController {

    final private RealBoardRepository realBoardRepository;
    final private RealBoardService realBoardService;
    final private ResponseService responseService;

    //조회
    // Token 필요없음
    @GetMapping("/api/boards")
    public ListResponse<RealBoardResponseDto> list() {
        return responseService.getListResponse(realBoardService.contentlist());
    }

    // 게시글 하나 조회
    // Token 필요없음
    @GetMapping("/api/board/{id}")
    public SingleResponse<RealBoard> viewonecontent(@PathVariable Long id){
        return responseService.getSingleResponse(realBoardService.viewcontent(id));
    }

    // 게시글 생성 (로그인된 유저정보만 가져올 수 있으면 될듯, )
    // Token 필요함
    // 포스트맨에서 security 로그인 후 나온 토큰값을 가지고, 다시 포스트맨에서 "/api/boards" 경로로 타고 들어가서
    // realBoardDto에 해당되는 입력값들을 json 형식으로 넣은 뒤, headers에 authirization 키를 추가하고 토큰 값 넣고 send!
    @PostMapping("/api/board")
    public RealBoard writecontent(@RequestBody RealBoardDto realBoardDto,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails
                                  ){
        System.out.println("token 여부 : " + userDetails);

        return realBoardService.writecontent(realBoardDto, userDetails);
    }

    // 수정
    // Token 필요함
    // 파라미터 값으로 타이틀이름을 넣어서 해당 타이틀이름을 가진 게시글 작성자와 현재 로그인된 작성자명을 비교하려했으나
    // 타이틀은 중복해서 쓸 수 있기 때문에 id 값으로 한다.
    @PutMapping("/api/board/{id}")
    public SingleResponse<RealBoard> updatecontent(@PathVariable Long id,
                              @RequestBody RealBoardDto realBoardDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return responseService.getSingleResponse(realBoardService.updatecontent(id, realBoardDto, userDetails));
    }


    // 비밀번호 체크
//    @GetMapping("/api/board/{id}")
//    public RealBoard pwdcheck(@PathVariable Long id, String password){
//        return realBoardService.pwdcheck(id, password);
//    }

    // 삭제
    @DeleteMapping("/api/board/{id}")
    public ListResponse<RealBoard> deletecontent(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return responseService.getListResponse(realBoardService.deletecontent(id,userDetails));
    }

}
