package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Factura;

@Repository
public interface FacturaRepo extends JpaRepository<Factura,String> {




}
