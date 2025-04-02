package no.vjd.sqldatabase.Repository;

import no.vjd.sqldatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
