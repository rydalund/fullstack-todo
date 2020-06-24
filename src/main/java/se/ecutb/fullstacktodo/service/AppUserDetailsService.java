package se.ecutb.fullstacktodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.fullstacktodo.data.AppUserRepository;
import se.ecutb.fullstacktodo.entity.AppUser;
import se.ecutb.fullstacktodo.entity.AppUserRole;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findByUsername(username);
        if(userOptional.isPresent()){
            AppUser user = userOptional.get();
            Collection<GrantedAuthority> collection = new HashSet<>();
            for(AppUserRole userRole : user.getRoleSet()){
                collection.add(new SimpleGrantedAuthority(userRole.getRole().name()));
            }
            return new AppUserDetails(user,collection);
        }else {
            throw new UsernameNotFoundException("Couldn't find user with name " + username);
        }

    }

}
