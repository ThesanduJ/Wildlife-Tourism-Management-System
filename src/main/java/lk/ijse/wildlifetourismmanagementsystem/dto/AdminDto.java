package lk.ijse.wildlifetourismmanagementsystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AdminDto {
    private String password;
    private String email;
    private String username;
}
