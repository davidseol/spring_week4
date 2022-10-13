package sparta.project.realboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.project.realboard.Entity.RealBoard;
public interface RealBoardRepository extends JpaRepository<RealBoard, Long> {
//    RealBoard findByUser_id(Long id);
}
