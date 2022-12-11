package chain_of_responsibility;

import lombok.Setter;

public abstract class Handler {
    int quantity;
    @Setter
    private Handler next;
    public  Handler(int quantity) {
        this.quantity = quantity;
    }
    public void process(int amount) {

        if (next != null) {
            next.process(amount%quantity);
        }
        else if (amount%quantity > 0){
            throw new IllegalArgumentException();

        }
        System.out.println(quantity + " = " + amount/quantity);
    }
}
