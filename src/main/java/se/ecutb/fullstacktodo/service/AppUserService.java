package se.ecutb.fullstacktodo.service;


import se.ecutb.fullstacktodo.dto.CreateAppUserForm;
import se.ecutb.fullstacktodo.entity.AppUser;
import se.ecutb.fullstacktodo.entity.TodoItem;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    AppUser registerAppUser(CreateAppUserForm appUserForm);
    Optional<AppUser> findByUsername(String username);
    Optional<TodoItem> findByItemTitle(String title);

    List<AppUser> findAll();
}

