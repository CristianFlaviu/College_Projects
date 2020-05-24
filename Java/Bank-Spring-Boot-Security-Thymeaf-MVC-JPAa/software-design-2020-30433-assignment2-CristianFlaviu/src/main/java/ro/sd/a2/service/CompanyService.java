package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Company;
import ro.sd.a2.repository.CompanyRepo;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepo companyRepo;

    public  void insertCompany(Company company)
    {
        companyRepo.save(company);
    }

    public Company getById(String id)
    {
        return companyRepo.findById(id).orElse(null);
    }

    public List<Company> getAll()
    {
        return companyRepo.findAll();
    }
}
