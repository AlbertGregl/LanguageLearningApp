package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.UserService;
import algebra.hr.dal.entity.Authority;
import algebra.hr.dal.entity.User;
import algebra.hr.dal.enums.Role;
import algebra.hr.dal.repository.AuthorityRepository;
import algebra.hr.dal.repository.UserRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository _userRepository;
    @Autowired
    private final AuthorityRepository _authorityRepository;
    @Autowired
    private final SecurityServiceImpl _securityService;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, SecurityServiceImpl securityService) {
        _userRepository = userRepository;
        _authorityRepository = authorityRepository;
        _securityService = securityService;
    }

    @Override
    public List<User> findAll() {
        return _userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        List<User> allDBUsers = _userRepository.findAll();

        Optional<User> optionalUser = allDBUsers.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new CustomNotFoundException("User does not exist in the database!");
        }
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    public void createAdminUser(String username, String rawPassword) {
        // Create the user
        User user = new User();
        user.setUsername(username);
        user.setPassword(_securityService.doBCryptPassEncoding(rawPassword));
        user.setEnabled(true);
        _userRepository.save(user);

        // Create the authority
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority(Role.ROLE_ADMIN);
        _authorityRepository.save(authority);
    }

    public void createStudentUser(String username, String rawPassword) {
        // Create the user
        User user = new User();
        user.setUsername(username);
        user.setPassword(_securityService.doBCryptPassEncoding(rawPassword));
        user.setEnabled(true);
        _userRepository.save(user);

        // Create the authority
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority(Role.ROLE_STUDENT);
        _authorityRepository.save(authority);
    }

    public void createStudentTeacher(String username, String rawPassword) {
        // Create the user
        User user = new User();
        user.setUsername(username);
        user.setPassword(_securityService.doBCryptPassEncoding(rawPassword));
        user.setEnabled(true);
        _userRepository.save(user);

        // Create the authority
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority(Role.ROLE_TEACHER);
        _authorityRepository.save(authority);
    }
}
