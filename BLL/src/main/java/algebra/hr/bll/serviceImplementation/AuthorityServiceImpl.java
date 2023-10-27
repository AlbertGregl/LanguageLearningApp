package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.AuthorityService;
import algebra.hr.dal.entity.Authority;
import algebra.hr.dal.repository.AuthorityRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;

import java.util.List;
import java.util.Optional;

public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository _authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        _authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> findAll() {
        return _authorityRepository.findAll();
    }

    @Override
    public Authority findById(int id) {
        Optional<Authority> authorityOptional = _authorityRepository.findById(id);

        if (authorityOptional.isEmpty()){
            throw new CustomNotFoundException("Authority id not found - " + id);
        }

        return authorityOptional.get();
    }

    @Override
    public Authority save(Authority authority) {
        return _authorityRepository.save(authority);
    }

    @Override
    public void deleteById(int id) {
        _authorityRepository.deleteById(id);
    }
}
