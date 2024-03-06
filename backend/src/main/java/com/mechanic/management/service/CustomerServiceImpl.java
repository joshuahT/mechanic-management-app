package com.mechanic.management.service;

import com.mechanic.management.DTO.CustomerDTO;
import com.mechanic.management.DTO.VehiclesDTO;
import com.mechanic.management.model.Customer;
import com.mechanic.management.model.Vehicles;
import com.mechanic.management.repository.CustomerRepo;
import com.mechanic.management.repository.VehiclesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final VehiclesRepo vehicleRepository;
    private final CustomerRepo customerRepo;


    @Autowired
    public CustomerServiceImpl(VehiclesRepo vehicleRepository,CustomerRepo customerRepo){
        this.vehicleRepository = vehicleRepository;
        this.customerRepo = customerRepo;
    }
    @Override
    public List<Vehicles> getVehiclesByCustomerId(Long customerId) {
        return vehicleRepository.findByCustomerCustomerId(customerId);
    }
    @Override
    public Customer getCustomerById(Long customerId) throws ChangeSetPersister.NotFoundException {
        return customerRepo.findById(customerId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public CustomerDTO createOrUpdateCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());

        Customer savedCustomer = customerRepo.saveAndFlush(customer);

        CustomerDTO savedCustomerDTO = new CustomerDTO();
        // Set properties of savedCustomerDTO from savedCustomer
        savedCustomerDTO.setCustomerId(savedCustomer.getId());
        savedCustomerDTO.setName(savedCustomer.getName());
        savedCustomerDTO.setPhoneNumber(savedCustomer.getPhoneNumber());
        savedCustomerDTO.setEmail(savedCustomer.getEmail());
        savedCustomerDTO.setAddress(savedCustomer.getAddress());

        return savedCustomerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomersWithVehicles() {
        List<Customer> customersWithVehicles = customerRepo.findAllWithVehicles();

        return customersWithVehicles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        // set customerDTO fields from customer
        customerDTO.setCustomerId(customer.getId());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());
        // map vehicles to VehiclesDTO and set in customerDTO
        customerDTO.setVehicles(customer.getVehicles().stream()
                .map(this::convertToVehiclesDTO)
                .collect(Collectors.toList()));

        return customerDTO;
    }

    private VehiclesDTO convertToVehiclesDTO(Vehicles vehicles) {
        VehiclesDTO vehiclesDTO = new VehiclesDTO();
        // set vehiclesDTO fields from vehicles
        vehiclesDTO.setVehicleId(vehicles.getVehicleId());
        vehiclesDTO.setMake(vehicles.getMake());
        vehiclesDTO.setModel(vehicles.getModel());
        vehiclesDTO.setYear(vehicles.getYear());
        vehiclesDTO.setLicensePlate(vehicles.getLicensePlate());

        return vehiclesDTO;
    }


}
