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
        // Create the user
        User userAdmin = new User();
        userAdmin.setUsername("HardoHardich");
        userAdmin.setPassword(_securityService.doBCryptPassEncoding("password123"));
        userAdmin.setEnabled(true);

        // Create the student
        User userStudent = new User();
        userStudent.setUsername("Tvrtko94");
        userStudent.setPassword(_securityService.doBCryptPassEncoding("password2019"));
        userStudent.setEnabled(false);

        when(_userRepository.findAll()).thenReturn(Arrays.asList(
                new User(),
                userStudent,
                userAdmin));
        List<User> users = _userService.findAll();
        assertEquals(3, users.size());
    }

    @Test
    public void testSaveAdmin() {
        // Create the user details
        String userNameAdmin = "HardoHardich";
        String passwordAdmin = "password123";

        when(_securityService.doBCryptPassEncoding(passwordAdmin)).thenReturn(passwordAdmin);

        _userService.createAdminUser(userNameAdmin, passwordAdmin);

        verify(_userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testSaveStudent() {
        // Create the user details
        String userNameStudent = "TvrtkoTvrtkich";
        String passwordStudent = "password123";

        when(_securityService.doBCryptPassEncoding(passwordStudent)).thenReturn(passwordStudent);

        _userService.createStudentUser(userNameStudent, passwordStudent);

        verify(_userRepository, times(1)).save(any(User.class));
    }
}
