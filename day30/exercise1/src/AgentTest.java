import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgentTest {

    @Test
    void withdrawingTakesFromTheFloat() throws InsufficientFundsException {
        AgentFloat agentFloat = new AgentFloat(1000);
        agentFloat.withdraw(400);
        assertEquals(600, agentFloat.getBalance());
    }

    @Test
    void tooMuchThrowsTheCheckedExceptionAndLeavesTheFloatAlone() {
        AgentFloat agentFloat = new AgentFloat(200);
        InsufficientFundsException e =
                assertThrows(InsufficientFundsException.class, () -> agentFloat.withdraw(500));
        assertEquals(300, e.getShortBy());
        assertEquals("Short by KES 300", e.getMessage());
        assertEquals(200, agentFloat.getBalance());
    }

    @Test
    void nothingOrLessIsAnIllegalArgument() {
        AgentFloat agentFloat = new AgentFloat(200);
        IllegalArgumentException e =
                assertThrows(IllegalArgumentException.class, () -> agentFloat.withdraw(-50));
        assertEquals("Amount must be above 0, not -50", e.getMessage());
        assertThrows(IllegalArgumentException.class, () -> agentFloat.withdraw(0));
    }

    @Test
    void theWholeAmountCanBeWithdrawn() throws InsufficientFundsException {
        AgentFloat agentFloat = new AgentFloat(200);
        agentFloat.withdraw(200);
        assertEquals(0, agentFloat.getBalance());
    }

    @Test
    void servesTheQueueWithAnOutcomeForEveryone() {
        AgentFloat agentFloat = new AgentFloat(1000);
        Agent agent = new Agent(agentFloat);
        List<String> outcomes = agent.serve(List.of("500", "abc", "-50", "700", " 300 ", ""));
        assertEquals(List.of(
                "Paid 500",
                "Not an amount: abc",
                "Refused: Amount must be above 0, not -50",
                "Declined: Short by KES 200",
                "Paid 300",
                "Not an amount: "), outcomes);
        assertEquals(200, agentFloat.getBalance());
    }

    @Test
    void countsEveryCustomerEvenTheOnesTurnedAway() {
        Agent agent = new Agent(new AgentFloat(100));
        agent.serve(List.of("50", "x", "500"));
        agent.serve(List.of("0"));
        assertEquals(4, agent.customersServed());
    }
}
