package com.sprintforge.identity.permission.domain;

import com.sprintforge.identity.permission.domain.valueobject.PermissionId;
import com.sprintforge.identity.permission.domain.valueobject.PermissionCode;
import com.sprintforge.identity.permission.domain.valueobject.PermissionName;
import com.sprintforge.identity.permission.domain.valueobject.PermissionDescription;
import com.sprintforge.identity.permission.domain.valueobject.PermissionCategory;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Permission {

    private final PermissionId id;
    private final PermissionCode code;
    private final PermissionName name;
    private final PermissionDescription description;
    private final PermissionCategory category;

    public Permission(
            UUID id,
            String code,
            String name,
            String description,
            String category
    ) {
        this.id = new PermissionId(id);
        this.code = new PermissionCode(code);
        this.name = new PermissionName(name);
        this.description = new PermissionDescription(description);
        this.category = new PermissionCategory(category);
    }
}

