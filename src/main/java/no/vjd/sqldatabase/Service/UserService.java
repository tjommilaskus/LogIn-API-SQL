package no.vjd.sqldatabase.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.vjd.sqldatabase.Repository.UserRepo;
import no.vjd.sqldatabase.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepo.findById(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        log.info("User {} not found", username);
        return null;
    }
    public User create(User user) {
        User newUser = userRepo.save(user);

        log.info("User {} was successfully created", user.getUsername());
        return newUser;
    }
    public User update(User user) {
        Optional<User> optionalUser = userRepo.findById(user.getUsername());
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEmail(user.getEmail());
            userRepo.save(updatedUser);
            log.info("User {} was successfully updated", user.getUsername());
            return updatedUser;
        }
        return null;
    }
    public boolean authenticate(String username, String password) {
        User user = findByUsername(username);
        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}
