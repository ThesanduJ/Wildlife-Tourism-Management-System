package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FinanceDto {
    private String touristId;
    private String ticketId;
    private String packageId;
    private double ticketPrice;
    private double packagePrice;
    private double paidAmount;
}
