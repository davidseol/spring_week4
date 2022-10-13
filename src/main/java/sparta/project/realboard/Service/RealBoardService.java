package sparta.project.realboard.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sparta.project.realboard.Dto.RealBoardDto;
import sparta.project.realboard.Dto.RealBoardResponseDto;
import sparta.project.realboard.Entity.RealBoard;
import sparta.project.realboard.Entity.User;
import sparta.project.realboard.Repository.RealBoardRepository;
import sparta.project.realboard.Security.UserDetailsImpl;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RealBoardService {

    private final RealBoardRepository realBoardRepository;

    // 전체 조회
    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public List<RealBoardResponseDto> contentlist() {
        Sort sort = Sort.by(Sort.Direction.DESC,  "createdDate");
        List<RealBoard> list = realBoardRepository.findAll(sort);
        return list.stream().map(RealBoardResponseDto::new).collect(Collectors.toList());
    }

    // 글 한 개 조회
    @Transactional
    public RealBoard viewcontent(Long id){
        RealBoard realBoard = realBoardRepository.findById(id).orElseThrow();
        return realBoard;
    }

    // 게시글 생성
    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public RealBoard writecontent(RealBoardDto realBoardDto, UserDetailsImpl userDetails) {

        RealBoard realBoard = new RealBoard(
                realBoardDto.getTitle(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                realBoardDto.getContent(),
                userDetails.getUser());


        realBoardRepository.save(realBoard);

        return realBoard;
    }

    // 글 수정
    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public RealBoard updatecontent(Long id, RealBoardDto realBoardDto, UserDetailsImpl userDetails) {

        RealBoard realBoard = realBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        if(!userDetails.getUser().getUsername().equals(realBoard.getWriter())){
            throw new IllegalArgumentException("해당 게시글 작성자가 아닙니다.");
        }

        realBoard.update(realBoardDto);
        return realBoard;
    }


    // 글 삭제
    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public List<RealBoard> deletecontent(Long id, UserDetailsImpl userDetails) {

        RealBoard realBoard = realBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        if(!userDetails.getUser().getUsername().equals(realBoard.getWriter())){
            throw new IllegalArgumentException("삭제는 해당 게시글 작성자만이 할 수 있습니다.");
        }

        realBoardRepository.deleteById(id);

        return realBoardRepository.findAll();
    }

    // 비밀번호 체크
    @Transactional
    public RealBoard pwdcheck(Long id, String password) {
        RealBoard realBoard = realBoardRepository.findById(id).orElseThrow();

        if(realBoard.getPassword().equals(password)){
            System.out.println("일치하는 비밀번호입니다.");
            return realBoard;
        }else{

            return realBoard;
        }

    }

}
