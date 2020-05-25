package ro.utcn.sd.mapper;

import ro.utcn.sd.dto.ReportDto;
import ro.utcn.sd.entity.Report;
import ro.utcn.sd.entity.Room;

import java.util.Date;

public class ReportMapper {

    public static Report DtoToReport(ReportDto reportDto, Room room,String id)
    {
        Report report=new Report();

        report.setId(id);
        report.setRoom(room);
        report.setDate(new Date());
        report.setTitle(reportDto.getTitle());
        report.setDescription(reportDto.getDescription());

        return report;
    }

    public static ReportDto ReportToDto(Report report)
    {
        ReportDto reportDto=new ReportDto();

        reportDto.setId(report.getId());
        reportDto.setDate(report.getDate());
        reportDto.setDescription(report.getDescription());
        reportDto.setTitle(report.getTitle());
        reportDto.setRoomNumber(report.getRoom().getRoomNumber());

        return reportDto;
    }
}
