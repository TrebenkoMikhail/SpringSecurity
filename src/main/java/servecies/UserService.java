package servecies;

import entities.Roles;
import entities.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reposetories.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<Users> findByUsername(String username) {return userRepository.findByUsername(username); }
    @Override
    @Transactional
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Users user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Username '%' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    @Override


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
