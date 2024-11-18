import jdk.jfr.Description;
import org.example.usermicroservice.controllers.AccountController;
import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.repositories.AccountRepository;
import org.example.usermicroservice.services.AccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountsTest {
    private AccountController accountController;

    //AVERIGUAR POR QUE MIERDA NO FUNCIONA Y SIEMPRE DICE QUE EL SERVICE O EL REPO ES NULL
//    @BeforeEach
//    public void setUp() throws Exception {
//        accountController = new AccountController(new AccountService());
//    }


    @Test
    @Description("Test para validar que una cuenta se anula correctamente")
    public void testAnullateAccount() {
        Account a = new Account();
        accountController.save(a);
        accountController.anullateAccount(a);
        Account b = accountController.getAccountById(a.getId()).getBody();
        assertTrue(b.isAnullated(), "la cuenta no se anuló correctamente");
    }
}
