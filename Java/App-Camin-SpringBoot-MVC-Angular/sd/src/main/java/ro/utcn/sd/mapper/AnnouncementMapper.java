package ro.utcn.sd.mapper;

import ro.utcn.sd.dto.AnnouncementDto;
import ro.utcn.sd.entity.Announcement;
import ro.utcn.sd.entity.User;

import java.util.Date;

public class AnnouncementMapper {

    public static AnnouncementDto AnnouncementToDto(Announcement announcement)
    {
        AnnouncementDto announcementDto=new AnnouncementDto();

        announcementDto.setId(announcement.getId());
        announcementDto.setDate(announcement.getDate());
        announcementDto.setTitle(announcement.getTitle());
        announcementDto.setDescription(announcement.getDescription());
        announcementDto.setUserName(announcement.getUser().getFirstName());


        return announcementDto;
    }

    public static Announcement DtoToAnnoucement(AnnouncementDto announcementDto, String id, User user)
    {
        Announcement announcement=new Announcement();
        announcement.setId(id);
        announcement.setDate(new Date());
        announcement.setUser(user);
        announcement.setTitle(announcementDto.getTitle());
        announcement.setDescription(announcementDto.getDescription());

        return announcement;
    }

}
