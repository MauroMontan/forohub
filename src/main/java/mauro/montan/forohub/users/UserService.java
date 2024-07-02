package mauro.montan.forohub.users;

import mauro.montan.forohub.users.errorHandlers.UserNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    @Lazy
    public UserService( UserRepository userRepository, PasswordEncoder encoder ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User createUser(User user) throws UserAlreadyExistException {

        var encodedPassword = this.encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException(user.getEmail());
        }

        return userRepository.save(user);
    }

    public UserProfileResponse findById(Long id){
        var user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }
        var existingUser = user.get();
        return existingUser.toProfileResponse();
    }

    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByRawEmail( String email) {

        return userRepository.findByRawEmail(email);
    }



}
