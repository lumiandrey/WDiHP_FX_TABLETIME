package by.bsuir.vdishp.model.entity;

/**
 * Created by andrey on 14.05.2016.
 */
public class Ticket {
    private static final int COST = 0;
    private int cost;
    public Ticket(){
        this.cost = COST;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "cost=" + cost +
                '}';
    }
}
