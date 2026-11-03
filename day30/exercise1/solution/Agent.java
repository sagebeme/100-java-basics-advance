import java.util.ArrayList;
import java.util.List;

public class Agent {

    private final AgentFloat agentFloat;
    private int customersServed;

    public Agent(AgentFloat agentFloat) {
        this.agentFloat = agentFloat;
    }

    // One outcome per request, in order. A bad request never stops the queue.
    public List<String> serve(List<String> requests) {
        List<String> outcomes = new ArrayList<>();
        for (String request : requests) {
            try {
                int amount = Integer.parseInt(request.trim());
                agentFloat.withdraw(amount);
                outcomes.add("Paid " + amount);
            } catch (NumberFormatException e) {
                outcomes.add("Not an amount: " + request.trim());
            } catch (IllegalArgumentException e) {
                outcomes.add("Refused: " + e.getMessage());
            } catch (InsufficientFundsException e) {
                outcomes.add("Declined: " + e.getMessage());
            } finally {
                customersServed++;
            }
        }
        return outcomes;
    }

    public int customersServed() {
        return customersServed;
    }

    public static void main(String[] args) {
        Agent agent = new Agent(new AgentFloat(1000));
        agent.serve(List.of("500", "abc", "-50", "700", " 300 ")).forEach(System.out::println);
        System.out.println(agent.customersServed() + " customers served");
    }
}
