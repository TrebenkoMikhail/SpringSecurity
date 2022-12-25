import entities.Role;
import entities.User;
import lombok.RequiredArgsConstructor;
import reposetories.UserReposetory;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserReposetory userReposetory;

    public Optional<User> findByUsername(String username) {return userReposetory.findByUsername(username); }
    @Override
    @Transactional
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundExeption(String.format("Username '%' not found", username));
        return new org.springframework.security.core.userdetail.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
