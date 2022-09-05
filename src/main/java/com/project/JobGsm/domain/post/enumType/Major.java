package com.project.JobGsm.domain.post.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

}
