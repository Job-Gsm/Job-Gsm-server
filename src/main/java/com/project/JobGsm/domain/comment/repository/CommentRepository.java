package com.project.JobGsm.domain.comment.repository;

import com.project.JobGsm.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
