package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.UserService;
import algebra.hr.dal.entity.User;
import algebra.hr.dal.repository.UserRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository _userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        _userRepository = userRepository;
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
}
