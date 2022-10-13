package sparta.project.realboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sparta.project.realboard.Entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
//    Comment findByBoardid(Long id);

    List<Comment> findByRealBoard_Id(Long id);

}
