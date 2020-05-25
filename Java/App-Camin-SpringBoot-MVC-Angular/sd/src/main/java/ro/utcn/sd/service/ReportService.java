package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Report;
import ro.utcn.sd.entity.Room;
import ro.utcn.sd.repository.ReportRepo;

import java.util.List;

@Service
public class ReportService  {

    @Autowired
    private ReportRepo reportRepo;

    public void add(Report report)
    {
        reportRepo.save(report);
    }

    public Report getById(String id)
    {
        return reportRepo.findById(id).orElse(null);
    }

    public List<Report> getAll()
    {
        return reportRepo.findAll();
    }

    public List<Report> getAllByRoomNumber(Room room)
    {
        return reportRepo.findByRoom(room);
    }
    public void deleteById(String id)
    {
        reportRepo.deleteById(id);
    }
}
