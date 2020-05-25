package ro.utcn.sd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {


    private String id;
    private String roomNumber;
    private int numberStudens;


}
