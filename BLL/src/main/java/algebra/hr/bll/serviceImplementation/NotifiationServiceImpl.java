package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.NotificationService;
import algebra.hr.dal.entity.Notification;
import algebra.hr.dal.repository.NotificationRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;

import java.util.List;
import java.util.Optional;

public class NotifiationServiceImpl implements NotificationService {
    private final NotificationRepository _notificationRepository;

    public NotifiationServiceImpl(NotificationRepository notificationRepository) {
        _notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> findAll() {
        return _notificationRepository.findAll();
    }

    @Override
    public Notification findById(int id) {
        Optional<Notification> notificationOptional = _notificationRepository.findById(id);

        if (notificationOptional.isEmpty()){
            throw new CustomNotFoundException("Notification id not found - " + id);
        }

        return notificationOptional.get();
    }

    @Override
    public Notification save(Notification notification) {
        return _notificationRepository.save(notification);
    }

    @Override
    public void deleteById(int id) {
        _notificationRepository.deleteById(id);
    }
}
