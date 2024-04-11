package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Integer customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer", customerId));
    }
}
