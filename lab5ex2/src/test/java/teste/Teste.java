package teste;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lab5ex2.PerecheNr;
import lab5ex2.MainAPP;

public class Teste {
    @Test
    public void testSuntConsecutiveFibonacci() {
        PerecheNr p = new PerecheNr(8, 13);
        assertTrue(p.suntConsecutiveFibonacci());

        p = new PerecheNr(13, 21);
        assertTrue(p.suntConsecutiveFibonacci());

        p = new PerecheNr(5, 6);
        assertFalse(p.suntConsecutiveFibonacci());
    }

    @Test
    public void testCmmmc() {
        PerecheNr p = new PerecheNr(6, 8);
        assertEquals(24, p.cmmmc());

        p = new PerecheNr(12, 15);
        assertEquals(60, p.cmmmc());

        p = new PerecheNr(5, 10);
        assertEquals(10, p.cmmmc());
    }

    @Test
    public void testSumaCifrelorEgala() {
        PerecheNr p = new PerecheNr(123, 321);
        assertTrue(p.sumaCifrelorEgala());

        p = new PerecheNr(456, 654);
        assertTrue(p.sumaCifrelorEgala());

        p = new PerecheNr(123, 456);
        assertFalse(p.sumaCifrelorEgala());
    }

    @Test
    public void testAcelasiNumarCifrePare() {
        PerecheNr p = new PerecheNr(246, 864);
        assertTrue(p.acelasiNumarCifrePare());

        p = new PerecheNr(1234, 5678);
        assertTrue(p.acelasiNumarCifrePare());

        p = new PerecheNr(123, 456);
        assertFalse(p.acelasiNumarCifrePare());
    }
}
