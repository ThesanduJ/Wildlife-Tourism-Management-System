package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DriverDto {
    private String id;
    private String NIC;
    private String name;
    private String p_id;
    private String mobile;
    private String email;
    private String expairDate;
}
