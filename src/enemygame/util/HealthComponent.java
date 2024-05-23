package enemygame.util;

public class HealthComponent {
    private final double maxHealth;
    private double currentHealth;

    public HealthComponent(double maxHealth, double currentHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
    }

    public HealthComponent(double maxHealth) {
        this(maxHealth, maxHealth);
    }

    public void damage(double amount) {
        currentHealth -= amount;
        if (currentHealth < 0) currentHealth = 0;
    }

    public void heal(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) currentHealth = maxHealth;
    }

    public boolean isDead() {
        return currentHealth == 0;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }
}
