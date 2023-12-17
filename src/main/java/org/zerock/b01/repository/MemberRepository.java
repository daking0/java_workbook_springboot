package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Members;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Members, Long> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Members m where m.mid = :memberId and m.social = false")
    Optional<Members> getWithRoles(@Param("memberId") String memberId);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Members> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Members m set m.mpw = :mpw where m.mid = :mid")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);
}
