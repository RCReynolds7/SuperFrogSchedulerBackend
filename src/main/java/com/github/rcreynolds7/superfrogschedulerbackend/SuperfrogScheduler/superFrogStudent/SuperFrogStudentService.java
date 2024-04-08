package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.superFrogStudent;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                orElseThrow(() -> new SuperFrogStudentNotFoundException(superFrogStudentId));
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
                .orElseThrow(() -> new SuperFrogStudentNotFoundException(superFrogStudentId));
    }
}
