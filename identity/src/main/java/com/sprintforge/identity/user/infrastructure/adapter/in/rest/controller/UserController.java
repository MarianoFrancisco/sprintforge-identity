package com.sprintforge.identity.user.infrastructure.adapter.in.rest.controller;

import com.sprintforge.identity.user.application.port.in.command.UpdateUserRole;
import com.sprintforge.identity.user.application.port.in.query.GetAllUsers;
import com.sprintforge.identity.user.application.port.in.query.GetUserById;
import com.sprintforge.identity.user.domain.User;
import com.sprintforge.identity.user.infrastructure.adapter.in.rest.dto.UpdateUserRoleRequestDTO;
import com.sprintforge.identity.user.infrastructure.adapter.in.rest.dto.UserResponseDTO;
import com.sprintforge.identity.user.infrastructure.adapter.in.rest.mapper.UserRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final GetAllUsers getAllUsers;
    private final GetUserById getUserById;
    private final UpdateUserRole updateUserRole;

    @GetMapping
    public List<UserResponseDTO> getAll(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean isDeleted
    ) {
        List<User> users = getAllUsers.handle(
                UserRestMapper.toQuery(
                        searchTerm,
                        status,
                        isDeleted
                )
        );
        return users.stream()
                .map(UserRestMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable UUID id) {
        User user = getUserById.handle(
                UserRestMapper.toQueryById(id)
        );
        return UserRestMapper.toResponse(user);
    }

    @PatchMapping("{id}/role")
    public UserResponseDTO changeRole(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserRoleRequestDTO dto
    ) {
        User user = updateUserRole.handle(
                UserRestMapper.toUpdateRoleCommand(
                        id,
                        dto
                )
        );
        return UserRestMapper.toResponse(user);
    }
}
