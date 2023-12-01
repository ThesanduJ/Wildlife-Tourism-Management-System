package lk.ijse.wildlifetourismmanagementsystem.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TouristTm {

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
