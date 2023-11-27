package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleDto {
    String registration;
    String packageId;
    String adminEmail;
    String permitNo;
    String permitD;
    String licenceD;
}
