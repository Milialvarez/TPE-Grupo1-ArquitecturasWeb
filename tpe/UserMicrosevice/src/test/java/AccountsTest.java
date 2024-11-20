
import org.example.usermicroservice.entities.Account;
import org.example.usermicroservice.repositories.AccountRepository;
import org.example.usermicroservice.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountsTest {

        @InjectMocks
        private AccountService cuentaService;
        @Mock
        private AccountRepository cuentaRepository;

        private Account cuenta;

        @BeforeEach
        void setUp() {
            cuenta = new Account();
            cuenta.setId(8L);
            cuenta.setAnullated(false);
            cuentaService.save(cuenta);
        }

        @Test
        void TEST_anularCuenta() throws Exception {
            //Simulo que la cuenta existe
            System.out.println(cuenta.getId());
            when(cuentaRepository.findById(cuenta.getId())).thenReturn(Optional.of(cuenta));
            cuentaService.setAccountAnullated(cuenta.getId(), true);

            assertTrue(cuenta.isAnullated(), "La cuenta no se anuló correctamente");
        }

    }
