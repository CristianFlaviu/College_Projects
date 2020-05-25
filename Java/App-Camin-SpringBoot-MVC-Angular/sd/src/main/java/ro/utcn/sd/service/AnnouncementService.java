package ro.utcn.sd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.sd.entity.Announcement;
import ro.utcn.sd.repository.AnnouncementRepo;

import java.util.List;

@Service
public class AnnouncementService  {

    @Autowired
    private AnnouncementRepo announcementRepo;

    public void add(Announcement announcement)
    {
        announcementRepo.save(announcement);
    }

    public Announcement getById(String id)
    {
        return  announcementRepo.findById(id).orElse(null);
    }

    public List<Announcement> getAll()
    {
        return announcementRepo.findAll();
    }

    public void deleteById(String id)
    {
        announcementRepo.deleteById(id);
    }

}
