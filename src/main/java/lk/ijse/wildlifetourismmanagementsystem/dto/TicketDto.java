package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketDto {

    private String ticket_id;
    private double price;
    private String ticket_type;
    private String cashier_id;
    private int ticketCount;
    private String packageId;
}
