package se.ecutb.fullstacktodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.ecutb.fullstacktodo.data.AppUserRepository;
import se.ecutb.fullstacktodo.data.AppUserRoleRepository;
import se.ecutb.fullstacktodo.data.TodoItemRepository;
import se.ecutb.fullstacktodo.dto.CreateAppUserForm;
import se.ecutb.fullstacktodo.entity.AppUser;
import se.ecutb.fullstacktodo.entity.AppUserRole;
import se.ecutb.fullstacktodo.entity.Roles;
import se.ecutb.fullstacktodo.entity.TodoItem;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AppUserRoleRepository roleRepository;
    private TodoItemRepository todoItemRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AppUserRoleRepository roleRepository, TodoItemRepository todoItemRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.todoItemRepository = todoItemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser registerAppUser(CreateAppUserForm appUserForm) {
        AppUser newUser = new AppUser(
                appUserForm.getUsername(),
                appUserForm.getFirstName(),
                appUserForm.getLastName(),
                LocalDate.now(),
                passwordEncoder.encode(appUserForm.getPassword())

        );
        AppUserRole role = roleRepository.findByRole(Roles.USER);

        Set<AppUserRole> roleSet = new HashSet<>();
        roleSet.add(role);

        newUser = appUserRepository.save(newUser);
        newUser.setRoleSet(roleSet);

        return newUser;
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public Optional<TodoItem> findByItemTitle(String title) {
        return todoItemRepository.findByItemTitle(title);
    }

    @Override
    public List<AppUser> findAll(){
        return appUserRepository.findAll();
    }
}