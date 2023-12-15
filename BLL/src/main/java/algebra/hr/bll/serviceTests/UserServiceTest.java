package algebra.hr.bll.serviceTests;

import algebra.hr.bll.serviceImplementation.SecurityServiceImpl;
import algebra.hr.bll.serviceImplementation.UserServiceImpl;
import algebra.hr.dal.entity.User;
import algebra.hr.dal.repository.AuthorityRepository;
import algebra.hr.dal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository _userRepository;
    @Mock
    private AuthorityRepository _authorityRepository;
    @InjectMocks
    private UserServiceImpl _userService;
    @Mock
    private SecurityServiceImpl _securityService;

    @Test
    public void testFindAllUsers() {
        // create the user
        User userAdmin = new User();
        userAdmin.setUsername("HardoHardich");
        userAdmin.setPassword(_securityService.doBCryptPassEncoding("password123"));
        userAdmin.setEnabled(true);

        // create the student
        User userStudent = new User();
        userStudent.setUsername("Tvrtko94");
        userStudent.setPassword(_securityService.doBCryptPassEncoding("password91"));
        userStudent.setEnabled(false);

        // mock repos response
        when(_userRepository.findAll()).thenReturn(Arrays.asList(userAdmin, userStudent));

        List<User> users = _userService.findAll();

        assertEquals(2, users.size());
        assertTrue(users.containsAll(Arrays.asList(userAdmin, userStudent)));
    }

    @Test
    public void testFindByUsernameExistingUser() {
        String username = "existingUser";
        User user = new User();
        user.setUsername(username);

        when(_userRepository.findAll()).thenReturn(List.of(user));

        User foundUser = _userService.findByUsername(username);
        assertEquals(username, foundUser.getUsername());
    }


    @Test
    public void testSaveAdmin() {
        String userNameAdmin = "HardoHardich";
        String passwordAdmin = "password123";

        when(_securityService.doBCryptPassEncoding(passwordAdmin)).thenReturn(passwordAdmin);

        _userService.createAdminUser(userNameAdmin, passwordAdmin);

        verify(_userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testSaveStudent() {
        String userNameStudent = "TvrtkoTvrtkich";
        String passwordStudent = "password123";

        when(_securityService.doBCryptPassEncoding(passwordStudent)).thenReturn(passwordStudent);

        _userService.createStudentUser(userNameStudent, passwordStudent);

        verify(_userRepository, times(1)).save(any(User.class));
    }
}
