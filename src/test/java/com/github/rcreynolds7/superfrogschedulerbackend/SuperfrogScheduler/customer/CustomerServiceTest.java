package com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.customer;
import org.junit.jupiter.api.AfterEach;
import com.github.rcreynolds7.superfrogschedulerbackend.SuperfrogScheduler.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

@Mock
CustomerRepository customerRepository;

@InjectMocks
CustomerService customerService;


    List<Customer> customers;

    @Value("${api.endpoint.base-url}/customer")
    String baseUrl;

    @BeforeEach
    void setUp() {
        Customer c1 = new Customer();
        c1.setFirstName("ana");
        c1.setLastName("park");
        c1.setEmail("anapark@gmail.com");
        c1.setPhone("(563) 522-7491");

        Customer c2 = new Customer();
        c2.setFirstName("john");
        c2.setLastName("smith");
        c2.setEmail("johnsmith@gmail.com");
        c2.setPhone("(123) 456-7890");

        Customer c3 = new Customer();
        c3.setFirstName("alice");
        c3.setLastName("brown");
        c3.setEmail("alicebrown@gmail.com");
        c3.setPhone("(987) 654-3210");

        Customer c4 = new Customer();
        c4.setFirstName("michael");
        c4.setLastName("jones");
        c4.setEmail("michaeljones@gmail.com");
        c4.setPhone("(555) 123-4567");

        this.customers = new ArrayList<>();

        this.customers.add(c1);
        this.customers.add(c2);
        this.customers.add(c3);
        this.customers.add(c4);
    }


    @AfterEach()
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        // Given
        Customer expectedCustomer = customers.get(0);
        given(customerRepository.findById(expectedCustomer.getId())).willReturn(Optional.of(expectedCustomer));

        // When
        Customer actualCustomer = customerService.findById(expectedCustomer.getId());

        // Then
        assertThat(actualCustomer).isEqualTo(expectedCustomer);
        verify(customerRepository, times(1)).findById(expectedCustomer.getId());
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        Integer nonExistentId = 99;
        given(customerRepository.findById(nonExistentId)).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
           customerService.findById(nonExistentId);
        });

        // Then
        assertThat(thrown).isInstanceOf(ObjectNotFoundException.class);
        verify(customerRepository, times(1)).findById(nonExistentId);
    }

    @Test
    void testUpdateSuccess1() {
        // Given
        Customer existingCustomer = customers.get(0);
        Customer updatedInfo = new Customer();
        updatedInfo.setFirstName("UpdatedFirstName");
        updatedInfo.setLastName("UpdatedLastName");

        given(customerRepository.findById(1)).willReturn(Optional.of(existingCustomer));
        given(customerRepository.save(existingCustomer)).willReturn(existingCustomer);

        // When
        Customer updatedStudent = customerService.update(1, updatedInfo);

        // Then
        assertThat(updatedStudent.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updatedStudent.getLastName()).isEqualTo("UpdatedLastName");
        verify(customerRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).save(existingCustomer);
    }

    @Test
    void testUpdateSuccess2() {
        // Given
        Customer existingCustomer = customers.get(2);
        Customer updatedInfo = new Customer();
        updatedInfo.setFirstName("UpdatedFirstName");
        updatedInfo.setLastName("UpdatedLastName");
        updatedInfo.setEmail("updatedEmail@tcu.edu");
        updatedInfo.setPhone("999-999-9999");


        given(customerRepository.findById(2)).willReturn(Optional.of(existingCustomer));
        given(customerRepository.save(existingCustomer)).willReturn(existingCustomer);

        // When
        Customer updatedCustomer = customerService.update(2, updatedInfo);

        // Then
        assertThat(updatedCustomer.getFirstName()).isEqualTo("UpdatedFirstName");
        assertThat(updatedCustomer.getLastName()).isEqualTo("UpdatedLastName");
        assertThat(updatedCustomer.getEmail()).isEqualTo("updatedEmail@tcu.edu");
        assertThat(updatedCustomer.getPhone()).isEqualTo("999-999-9999");


        verify(customerRepository, times(1)).findById(2);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

        @Test
        void testUpdateNotFound() {
            // Given
            Customer updatedInfo = new Customer();
            updatedInfo.setFirstName("Nonexistent");
            given(customerRepository.findById(anyInt())).willReturn(Optional.empty());

            // When & Then
            assertThrows(ObjectNotFoundException.class, () -> customerService.update(99, updatedInfo));
            verify(customerRepository, times(1)).findById(99);
        }

    @Test
    void testUpdateInvalidFormat() {
        Integer customerId = 1;
        Customer existingCustomer = customers.get(0); // Assuming the first student is the one we're trying to update.
        Customer invalidUpdateInfo = new Customer();
        invalidUpdateInfo.setEmail("invalidEmail"); // Invalid email format
        invalidUpdateInfo.setPhone("1234567890"); // Invalid phone format

        given(customerRepository.findById(customerId)).willReturn(Optional.of(existingCustomer));

        // Assuming save method or some validation method throws IllegalArgumentException for invalid formats
        willThrow(new IllegalArgumentException("Invalid data format")).given(customerRepository).save(any(Customer.class));

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> customerService.update(customerId, invalidUpdateInfo));
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(any(Customer.class)); // Verifies save was attempted and exception was throws

    }



}












