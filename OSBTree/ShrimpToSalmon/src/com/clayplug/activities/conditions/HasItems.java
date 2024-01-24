package src.com.clayplug.activities.conditions;

import com.clayplug.utilities.btree.Task;
import org.osbot.rs07.script.MethodProvider;


public class HasItems extends HasItem {

    private final int amount;
    public HasItems(MethodProvider api, String item, int amount) {
        super(api, item);
        this.item = item;
        this.amount = amount;
    }

    @Override
    public Task.STATUS run() {
        if (item != null &&
                !(api.getInventory().getAmount(item) >= amount)) {
            return Task.STATUS.FAILED;
        } else {
            return Task.STATUS.SUCCEEDED;
        }
    }
}
