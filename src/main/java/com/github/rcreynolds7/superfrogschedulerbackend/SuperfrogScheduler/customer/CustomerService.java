package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;

import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Integer customerId) {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer", customerId));
    }
    public List<Customer> findAll(){return this.customerRepository.findAll();}


    public Customer update(Integer customerId, Customer update) {
        return this.customerRepository.findById(customerId)
                .map(oldCustomer -> {
                    oldCustomer.setFirstName(update.getFirstName());
                    oldCustomer.setLastName(update.getLastName());
                    oldCustomer.setEmail(update.getEmail());
                    oldCustomer.setPhone(update.getPhone());

                    return this.customerRepository.save(oldCustomer);
                })
                .orElseThrow(() -> new ObjectNotFoundException("superfrogstudent", customerId));
    }
}
