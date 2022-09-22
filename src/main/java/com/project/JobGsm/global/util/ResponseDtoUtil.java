package com.project.JobGsm.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDtoUtil {

    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 정확히 필드명이 일치한것만 매치
    }

    public static <D, T> D map(final T entity, Class<D> dto) {
        return mapper.map(entity, dto);
    }
}
