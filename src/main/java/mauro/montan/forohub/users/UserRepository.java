package mauro.montan.forohub.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public UserDetails findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByRawEmail(String email);

    public boolean existsByEmail(String email);
}
