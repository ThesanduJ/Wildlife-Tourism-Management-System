package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CashierDto {
    private String cashierId;
    private String cashierUsername;
    private String cashierPassword;
    private String adminEmail;
}
