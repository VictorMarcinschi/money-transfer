package transfer.user.service;

import lombok.RequiredArgsConstructor;
import transfer.moneytransfer.model.UserAttribute;
import transfer.user.model.User;
import transfer.user.repository.UserRepository;

import java.time.Clock;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class UserService {

    private final Map<UserAttribute, ByAttributeResolver> byAttributeResolvers = Map.of(
            UserAttribute.EMAIL, this::resolveUserByEmail,
            UserAttribute.PHONE, this::resolveUserByPhone
    );

    private final UserRepository userRepository;
    private final UserIdentifierGenerator userIdentifierGenerator;
    private final Clock systemClock;

    public User resolveUserByDetails(String email, String phone) {
        return userRepository.findExactByDetails(email, phone).orElseGet(() -> {
            var identifier = userIdentifierGenerator.generate();
            var user = new User(identifier, Optional.of(email), Optional.of(phone), systemClock);
            userRepository.create(user);
            return user;
        });
    }

    public User resolveUserByAttribute(UserAttribute attribute, String identifier) {
        return byAttributeResolvers.get(
                Optional.ofNullable(attribute).orElseThrow(IllegalArgumentException::new))
                .resolve(identifier);
    }

    private User resolveUserByEmail(String email) {
        var emailOptional = Optional.ofNullable(email);
        return resolve(userRepository::findByEmail,
                () -> new User(userIdentifierGenerator.generate(), emailOptional, Optional.empty(), systemClock),
                emailOptional.orElseThrow(IllegalArgumentException::new));
    }

    private User resolveUserByPhone(String phone) {
        var phoneOptional = Optional.ofNullable(phone);
        return resolve(userRepository::findByPhone,
                () -> new User(userIdentifierGenerator.generate(), Optional.empty(), phoneOptional, systemClock),
                phoneOptional.orElseThrow(IllegalArgumentException::new));
    }

    private User resolve(Finder finder, Supplier<User> userSupplier, String attribute) {
        return finder.find(attribute).orElseGet(() -> {
            var user = userSupplier.get();
            userRepository.create(user);
            return user;
        });
    }

    private interface Finder {

        Optional<User> find(String byUserDetailsAttribute);
    }

    private interface ByAttributeResolver {

        User resolve(String attribute);
    }
}
