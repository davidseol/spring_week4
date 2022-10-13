package sparta.project.realboard.Dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String username;
    private String password;
    private String passwordConfirm;
    private boolean admin = false;
    private String adminToken = "";

}
