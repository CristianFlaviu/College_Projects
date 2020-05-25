package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Report;
import ro.utcn.sd.entity.Room;

import java.util.List;

@Repository
public interface ReportRepo extends JpaRepository<Report,String> {

    public List<Report> findByRoom(Room room);
}
