package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuideDto {

    private String nic;
    private String name;
    private String mobile;
    private  String date;
    private String address;
    private String packageId;
    private String email;
}
