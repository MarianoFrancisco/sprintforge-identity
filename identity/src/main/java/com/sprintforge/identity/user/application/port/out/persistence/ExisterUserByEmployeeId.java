package com.sprintforge.identity.user.application.port.out.persistence;

import java.util.UUID;

public interface ExisterUserByEmployeeId {
    boolean existsByEmployeeId(UUID employeeId);
}
