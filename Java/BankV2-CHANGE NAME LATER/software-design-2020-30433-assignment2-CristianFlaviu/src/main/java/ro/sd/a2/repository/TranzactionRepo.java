package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Factura;
import ro.sd.a2.entity.Tranzaction;

@Repository
public interface TranzactionRepo extends JpaRepository<Tranzaction,String> {

    public Tranzaction findByFactura(Factura factura);
}
