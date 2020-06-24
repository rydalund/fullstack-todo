package se.ecutb.fullstacktodo.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.fullstacktodo.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);
    List<AppUser> findAll();
    AppUser findByUserId (int userId);
}

