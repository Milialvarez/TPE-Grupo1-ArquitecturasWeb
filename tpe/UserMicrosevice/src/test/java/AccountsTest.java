import jdk.jfr.Description;
import org.example.usermicroservice.controllers.AccountController;
import org.example.usermicroservice.entities.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountsTest {
    private static AccountController accountController = new AccountController();

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
