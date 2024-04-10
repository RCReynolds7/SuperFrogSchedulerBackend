package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SuperFrogStudentService {
    private final SuperFrogStudentRepository superFrogStudentRepository;

    @Autowired
    public SuperFrogStudentService(SuperFrogStudentRepository superFrogStudentRepository) {
        this.superFrogStudentRepository = superFrogStudentRepository;
    }

    public SuperFrogStudent findById(Integer superFrogStudentId) {
        return this.superFrogStudentRepository.findById(superFrogStudentId).
                orElseThrow(() -> new ObjectNotFoundException("superfrogstudent", superFrogStudentId));
    }

    public List<SuperFrogStudent> findAll() {
        return this.superFrogStudentRepository.findAll();
    }

    public SuperFrogStudent update(Integer superFrogStudentId, SuperFrogStudent update) {
        return this.superFrogStudentRepository.findById(superFrogStudentId)
                .map(oldSuperFrogStudent -> {
                    oldSuperFrogStudent.setFirstName(update.getFirstName());
                    oldSuperFrogStudent.setLastName(update.getLastName());
                    oldSuperFrogStudent.setEmail(update.getEmail());
                    oldSuperFrogStudent.setPhone(update.getPhone());
                    oldSuperFrogStudent.setAddress(update.getAddress());
                    oldSuperFrogStudent.setActive(update.getActive());

                    return this.superFrogStudentRepository.save(oldSuperFrogStudent);
                })
                .orElseThrow(() -> new ObjectNotFoundException("superfrogstudent", superFrogStudentId));
    }

    public List<SuperFrogStudent> searchStudents(String firstName, String lastName, String email, String phone) {
        Specification<SuperFrogStudent> spec = Specification.where(null);
        if (StringUtils.hasText(firstName)) {
            spec = spec.and(SuperFrogStudentSpecifications.withFirstName(firstName));
        }
        if (StringUtils.hasText(lastName)) {
            spec = spec.and(SuperFrogStudentSpecifications.withLastName(lastName));
        }
        if (StringUtils.hasText(email)) {
            spec = spec.and(SuperFrogStudentSpecifications.withEmail(email));
        }
        if (StringUtils.hasText(phone)) {
            spec = spec.and(SuperFrogStudentSpecifications.withPhone(phone));
        }
        return superFrogStudentRepository.findAll(spec);
    }
}
