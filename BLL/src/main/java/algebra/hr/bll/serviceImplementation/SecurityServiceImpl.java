package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.SecurityService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    //this is for hashing and salting our passwords
    private static final String ALGORITHM = "SHA-256";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public SecurityServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public String doBCryptPassEncoding(String plainTextPassword) {
        return bCryptPasswordEncoder.encode(plainTextPassword);
    }
}
