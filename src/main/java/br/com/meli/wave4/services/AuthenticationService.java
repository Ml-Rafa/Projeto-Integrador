package br.com.meli.wave4.services;

        import br.com.meli.wave4.entities.User;
        import br.com.meli.wave4.repositories.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

@Service

public class AuthenticationService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUser(username);
    }

    public  User authenticated(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}