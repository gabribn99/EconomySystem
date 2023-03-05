package plugin.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceBean {
    private String playerName;
    private double amount;

    public void addAmount(double amount) {
        if (amount > 0) {
            this.amount += amount;
        }
    }

    public void quitAmmount(double amount) {
        if (amount > 0 && (this.amount - amount) >= 0) {
            this.amount -= amount;
        }
    }
}
