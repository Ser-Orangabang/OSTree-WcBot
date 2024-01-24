package src.com.clayplug.activities;

import com.clayplug.utilities.Sleep;
import src.com.clayplug.activities.conditions.IsAtSpot;
import com.clayplug.utilities.btree.Task;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;


public class WalkToSpot extends Task {

    private final Area spot;

    public WalkToSpot(MethodProvider api, Area spot) {
        super(api);
        this.spot = spot;
    }


    @Override
    public STATUS run() {
        api.getWalking().webWalk(spot);

        Sleep.sleepUntil(() -> spot.contains(api.myPosition()), 20000);

        if (new IsAtSpot(api, spot).run() == STATUS.SUCCEEDED) {
            return STATUS.SUCCEEDED;
        }

        return STATUS.RUNNING;
    }

    @Override
    public void terminate() {

    }
}
