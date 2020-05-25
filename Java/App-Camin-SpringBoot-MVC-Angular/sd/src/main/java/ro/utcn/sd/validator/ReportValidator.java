package ro.utcn.sd.validator;

import ro.utcn.sd.entity.Report;

import java.util.Objects;

public class ReportValidator {

    public static Boolean validate(Report report)
    {

        if(Objects.isNull(report))
            return false;

        if(Objects.isNull(report.getRoom()) || Objects.isNull(report.getDate())|| Objects.isNull(report.getDescription())||
          Objects.isNull(report.getTitle())|| Objects.isNull(report.getId()))
        {
            return false;
        }

        if(report.getTitle().length()<2 )
            return false;

        if(report.getDescription().length()<10)
            return false;


        return true;

    }
}
