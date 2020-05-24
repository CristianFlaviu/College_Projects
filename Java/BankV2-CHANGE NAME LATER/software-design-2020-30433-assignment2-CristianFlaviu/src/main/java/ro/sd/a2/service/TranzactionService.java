package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Factura;
import ro.sd.a2.entity.Tranzaction;
import ro.sd.a2.repository.TranzactionRepo;

import java.util.List;

@Service
public class TranzactionService  {

    @Autowired
    private TranzactionRepo tranzactionRepo;

    public void insertTranzaction(Tranzaction tranzaction)
    {
        tranzactionRepo.save(tranzaction);
    }

    public Tranzaction getById(String id)
    {
        return tranzactionRepo.findById(id).orElse(null);
    }

    public List<Tranzaction> getAll()
    {
        return tranzactionRepo.findAll();
    }

    public Tranzaction getByFactura(Factura factura)
    {
        return tranzactionRepo.findByFactura(factura);
    }


}
