package se.ecutb.fullstacktodo.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.fullstacktodo.entity.AppUserRole;
import se.ecutb.fullstacktodo.entity.Roles;

import java.util.Optional;

public interface AppUserRoleRepository extends CrudRepository<AppUserRole, Integer> {

    AppUserRole findByRole(Roles role);
    /*@Override
    Optional<AppUserRole> findById(Integer integer);*/
}

