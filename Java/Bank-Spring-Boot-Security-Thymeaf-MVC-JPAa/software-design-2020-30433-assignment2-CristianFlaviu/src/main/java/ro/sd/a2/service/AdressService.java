package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Address;
import ro.sd.a2.repository.AddressRepository;

import java.util.List;

@Service
public class AdressService  {

    @Autowired
    private AddressRepository addressRepository;

    public void  insertAdress(Address address)
    {
        addressRepository.save(address);
    }

    public  Address getAdressById(String id)
    {
        return addressRepository.findById(id).orElse(null);

    }

    public void deleteAdressById(String id)
    {
        addressRepository.deleteById(id);
    }

    public List<Address> getAll()
    {
        return addressRepository.findAll();
    }
}
