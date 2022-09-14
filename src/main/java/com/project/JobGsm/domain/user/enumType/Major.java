package com.project.JobGsm.domain.user.enumType;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Major {

    BACK_END("back-end"),
    FRONT_END("front-end"),
    DESIGNER("disigner"),
    PROJECT_MANAGER("project_manager"),
    ANDROID("android"),
    IOS("ios"),
    DATABASE_ADMINISTATOR("database_administator");

    private final String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Major findByCode(String value) {
        return Stream.of(Major.values())
                .filter(c -> c.value.equals(value))
                .findFirst()
                .orElse(null);
    }
}
