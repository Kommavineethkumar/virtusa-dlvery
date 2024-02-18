package com.virtusa.dlvery.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dlvery_user_role_tbl")

public class DlveryUserRoleAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID assignmentID;

    @NotNull(message = "UserID is required")
    @ManyToOne
    private DlveryUser user;

    @NotNull(message = "RoleID is required")
    @ManyToOne
    private DlveryRole role;

    // Constructor without ID
    public DlveryUserRoleAssignment(DlveryUser user, DlveryRole role) {
        this.user = user;
        this.role = role;
    }
}
