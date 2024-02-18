package com.virtusa.dlvery.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dlvery_role_tbl")
public class DlveryRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleID;

    @NotBlank(message = "RoleName is required")
    @Size(min = 1, max = 255, message = "RoleName must be between 1 and 255 characters")
    private String roleName;

    @NotBlank(message = "RoleCreatedBy is required")
    @Size(min = 1, max = 255, message = "RoleCreatedBy must be between 1 and 255 characters")
    private String roleCreatedBy;

    @NotNull(message = "CreatedDate is required")
    private Date createdDate;

    // Constructor without ID
    public DlveryRole(String roleName, String roleCreatedBy, Date createdDate) {
        this.roleName = roleName;
        this.roleCreatedBy = roleCreatedBy;
        this.createdDate = createdDate;
    }

}
