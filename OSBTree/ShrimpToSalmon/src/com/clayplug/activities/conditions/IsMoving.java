package src.com.clayplug.activities.conditions;

import com.clayplug.utilities.btree.Task;
import org.osbot.rs07.script.MethodProvider;

public class IsMoving extends Task {

    public IsMoving(MethodProvider api) {
        super(api);
    }


    public STATUS run() {
        if (api.myPlayer().isMoving() || api.myPlayer().isAnimating()) {
            return Task.STATUS.SUCCEEDED;
        }
        return Task.STATUS.FAILED;
    }

    public void terminate() {
    }
}