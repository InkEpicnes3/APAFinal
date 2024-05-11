package enemygame.util;

public class HealthComponent {
    private int maxHealth, currentHealth;

    public HealthComponent(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void damage(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0)
            currentHealth = 0;
    }

    public void heal(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth)
            currentHealth = maxHealth;
    }

    public boolean isDead() {
        return currentHealth == 0;
    }
}
