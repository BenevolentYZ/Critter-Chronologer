package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dataObject.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = mappDTOToEntity(customerDTO);
        customer = customerRepository.save(customer);
        return mapEntityToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> retrievedCustomerDTOs= new ArrayList<>();
        List<Customer> retrievedCustomers = customerRepository.findAll();
        if (retrievedCustomers != null) {
            for (Customer customer : retrievedCustomers) {
                retrievedCustomerDTOs.add(mapEntityToDTO(customer));
            }
        }
        return retrievedCustomerDTOs;
    }

    private Customer mappDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }
    private CustomerDTO mapEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
}
