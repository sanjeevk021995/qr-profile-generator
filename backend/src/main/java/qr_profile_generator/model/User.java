package qr_profile_generator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String fullName;
    private String email;
    private String mobileNo;
    private String password; // Should be hashed in real app
    private String name; // For profile
    private int age;
    private String address;
    private String emergencyContactNumber;
    private String bloodGroup;
    private String profilePhoto; // Store URL or Base64
    private String role; // "USER" or "ADMIN"

}