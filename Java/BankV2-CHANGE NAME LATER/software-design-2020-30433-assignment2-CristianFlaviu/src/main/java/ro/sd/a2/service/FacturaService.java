package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.entity.Factura;
import ro.sd.a2.repository.FacturaRepo;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepo facturaRepo;

    public void insertFactura(Factura factura)
    {
        facturaRepo.save(factura);
    }

    public Factura getById(String id)
    {
        return facturaRepo.findById(id).orElse(null);
    }

    public void delete(Factura factura)
    {
        facturaRepo.delete(factura);
    }
}
