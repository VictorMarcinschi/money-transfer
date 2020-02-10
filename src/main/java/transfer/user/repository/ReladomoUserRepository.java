package transfer.user.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import transfer.user.model.User;
import transfer.user.model.UserFinder;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ReladomoUserRepository implements UserRepository {

    @Override
    public Optional<User> findByIdentifier(UUID identifier) {
        return Optional.ofNullable(UserFinder.findByUqUser(identifier.toString()));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(UserFinder.findOne(UserFinder.userDetails().email().eq(email)));
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return Optional.ofNullable(UserFinder.findOne(UserFinder.userDetails().phone().eq(phone)));
    }

    @Override
    public Optional<User> findExactByDetails(String email, String phone) {
        return Optional.ofNullable(UserFinder.findOne(
                UserFinder.userDetails().email().eq(email)
                        .and(UserFinder.userDetails().phone().eq(phone))));
    }

    @Override
    public void create(User user) {
        user.cascadeInsert();
    }
}
