package com.project.JobGsm.domain.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "EmailCertification", timeToLive = 60 * 5)
public class EmailCertification {

    @Id
    private String email;

    @Length(min = 5, max = 5)
    @Column
    private String authKey;

}
