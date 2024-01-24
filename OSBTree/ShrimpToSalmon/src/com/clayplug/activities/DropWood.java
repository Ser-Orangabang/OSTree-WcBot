package src.com.clayplug.activities;

import com.clayplug.utilities.btree.Task;
import org.osbot.rs07.script.MethodProvider;


public class DropWood extends Task {
    public DropWood(MethodProvider api) {
        super(api);
    }

    @Override
    public STATUS run() {
        api.getInventory().dropAllExcept("Bronze axe", "Coins");
        return STATUS.SUCCEEDED;
    }


    @Override
    public void terminate() {

    }
}
