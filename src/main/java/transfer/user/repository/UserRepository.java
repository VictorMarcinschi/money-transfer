package transfer.user.repository;

import transfer.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findByIdentifier(UUID identifier);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findExactByDetails(String email, String phone);

    void create(User user);
}
