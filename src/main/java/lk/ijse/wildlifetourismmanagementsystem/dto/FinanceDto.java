package lk.ijse.wildlifetourismmanagementsystem.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FinanceDto {

  private String touristIds;
   private String ticketsId;
   private String packagesId;
   private double ticketsPrice;
   private double packagesPrice;
   private double paidAmount;
   private int qty;
}
