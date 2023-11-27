package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PackageDto {
    private String packageId;
    private String description;
    private double price;
    private String features;
    private String packageType;
}
