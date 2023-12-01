package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TouristDto {
    private String touristId;
    private String NIC;
    private String name;
    private String ageLevel;
    private String email;
    private String mobileNumber;
    private String region;
    private String cashierId;
    private String address;
}
