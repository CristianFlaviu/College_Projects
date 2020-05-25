package ro.utcn.sd.validator;

import ro.utcn.sd.entity.Announcement;

import java.util.Objects;

public class AnnoucementValidator {

    public static Boolean validate(Announcement announcement) {
        if (Objects.isNull(announcement))
            return false;

        if (Objects.isNull(announcement.getDate()) || Objects.isNull(announcement.getDescription()) || Objects.isNull(announcement.getTitle())
                || Objects.isNull(announcement.getId()) || Objects.isNull(announcement.getUser()))
            return false;

        if(announcement.getTitle().length()<4)
            return false;

        if(announcement.getDescription().length()<10)
            return false;


        return true;

    }
}
