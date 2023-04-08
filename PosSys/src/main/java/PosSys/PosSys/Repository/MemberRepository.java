package PosSys.PosSys.Repository;

import PosSys.PosSys.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Member findByUsername(String username);

    Member findByIdAndPassword(String id, String password);

    Member findById(String Id);

}
