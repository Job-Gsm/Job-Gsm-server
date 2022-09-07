package com.project.JobGsm.domain.board.repository;

import com.project.JobGsm.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    Page<Board> findAll(Pageable pageable);

    long updateViewBoard(Long board_id);

}
