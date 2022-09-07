package com.project.JobGsm.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.project.JobGsm.domain.board.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long updateViewBoard(Long board_id) {

        return jpaQueryFactory
                .update(board)
                .set(board.view, board.view.add(1))
                .where(board.board_id.eq(board_id))
                .execute();
    }
}
