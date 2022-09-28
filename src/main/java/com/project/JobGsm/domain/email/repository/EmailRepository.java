package com.project.JobGsm.domain.email.repository;

import com.project.JobGsm.domain.email.EmailCertification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmailRepository extends CrudRepository<EmailCertification, String> {
    Optional<EmailCertification> findByAuthKey(String authKey);
}
