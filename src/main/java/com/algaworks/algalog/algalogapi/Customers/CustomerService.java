package com.algaworks.algalog.algalogapi.Customers;

import com.algaworks.algalog.algalogapi.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public List<Customer> listAll() {
        return customerRepository.findAll();
    }

    public Customer create(Customer customer) {
        boolean emailExists = customerRepository.findByEmail(customer.getEmail())
                .stream()
                .anyMatch(customerExists -> !customerExists.equals(customer));
        if (emailExists) {
            throw new BusinessException("Já existe um cliente cadastrado com esse email");
        }
        return customerRepository.save(customer);
    }

    public Customer customerExists(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
    }
}
