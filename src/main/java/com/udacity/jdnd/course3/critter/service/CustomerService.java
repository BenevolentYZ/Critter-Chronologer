package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
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
    @Autowired
    PetRepository petRepository;

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

    public CustomerDTO getOwnerByPet(long petId) {
        Pet pet = petRepository.getOne(petId);
        CustomerDTO customerDTO = mapEntityToDTO(pet.getCustomer());
        return customerDTO;
    }

    private Customer mappDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        if (customerDTO.getPetIds() != null && customerDTO.getPetIds().size() > 0) {
            for (long petId : customerDTO.getPetIds()) {
                customer.addPet(petRepository.getOne(petId));
            }
        }
        return customer;
    }
    private CustomerDTO mapEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        List<Long> petIds = new ArrayList<>();
        if (customer.getPets() != null) {
            for (Pet pet : customer.getPets()) {
                petIds.add(pet.getId());
            }
        }
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }
}
