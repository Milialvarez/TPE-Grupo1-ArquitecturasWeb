import jdk.jfr.Description;
import org.example.adminmicroservice.controllers.AdminController;
import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.feignClients.UserFeignClient;
import org.example.adminmicroservice.models.User;
import org.example.adminmicroservice.services.AdminService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminTest {
    @InjectMocks
    private AdminService adminService;
    @Mock
    private UserFeignClient userFeignClient;

    ResponseEntity<List<User>> usersresp;

    @BeforeEach
    void setUp() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());
        usersresp = ResponseEntity.ok(users);
    }

    @Test
    public void correctResponse(){
        when(userFeignClient.getUsersByRole("admin")).thenReturn(usersresp);
        ResponseEntity<?> response = adminService.getUsersByRole("admin");
        assert response != null;
        assertEquals(Objects.requireNonNull(response.getBody()).getClass(), ArrayList.class, "clase incorrecta");
    }
}
