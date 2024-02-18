package com.virtusa.dlvery.services;

import com.virtusa.dlvery.entities.DlveryUser;

import java.util.List;
import java.util.UUID;

public interface DlveryUserService {

    List<DlveryUser> getAllUsers();

    DlveryUser getUserById(UUID userId);

    DlveryUser createUser(DlveryUser user);

    DlveryUser updateUser(UUID userId, DlveryUser user);

    void deleteUser(UUID userId);
}