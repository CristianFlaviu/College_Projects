package ro.utcn.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.sd.entity.Announcement;

@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement,String> {
}
