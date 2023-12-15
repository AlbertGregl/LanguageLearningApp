package algebra.hr.bll.serviceTests;

import algebra.hr.bll.serviceImplementation.SecurityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {
    @InjectMocks
    private SecurityServiceImpl _securityService;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void testDoBCryptPassEncoding() {
        String plainTextPassword = "testPassword123";
        when(bCryptPasswordEncoder.encode(plainTextPassword)).thenReturn("encodedPassword");

        String encodedPassword = _securityService.doBCryptPassEncoding(plainTextPassword);

        assertNotNull(encodedPassword);
        assertNotEquals(plainTextPassword, encodedPassword);
        verify(bCryptPasswordEncoder, times(1)).encode(plainTextPassword);
    }
}
