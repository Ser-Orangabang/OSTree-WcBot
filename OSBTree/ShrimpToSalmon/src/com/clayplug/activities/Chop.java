package src.com.clayplug.activities;

import com.clayplug.utilities.Sleep;
import com.clayplug.utilities.btree.Task;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.osbot.rs07.script.MethodProvider.random;

public class Chop extends Task {
    private static Area choppingArea = (new Area(3199, 3248, 3206, 3239));
    private static String axeQuality = "Bronze";

    public Chop(MethodProvider api) { super(api); }

    public static Area getChoppingArea() { return choppingArea; }

public STATUS run() {
    api.log("Chop running");
    try {
        RS2Object tree = api.getObjects().closest("Tree");

        if (tree != null) {
            if (!api.myPlayer().isAnimating()) {
                tree.interact("Chop down");

                api.getMouse().moveOutsideScreen();
            }
        }
    } catch (Exception e) {
        api.log("Error within Chop");
        return STATUS.FAILED;
    }
    return STATUS.SUCCEEDED;
}

@Override
public void terminate() {
}

public static void goChop() {

}

}
