package ApiResponse;

import com.example.library_management_system_app.data.model.User;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private List<User> users;

    public ApiResponse(boolean success, String message, List<User> users) {
        this.success = success;
        this.message = message;
        this.users = users;
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(boolean success,List<User> users,  String message) {
        this.message = message;
        this.users = users;
    }

    public ApiResponse(List<User> users, String message) {
        this.message = message;
        this.users = users;
    }
    public ApiResponse() {
//            this.users = new ArrayList<>();
    }
}