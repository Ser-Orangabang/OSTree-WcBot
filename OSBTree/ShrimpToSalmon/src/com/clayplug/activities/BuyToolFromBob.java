package src.com.clayplug.activities;

import src.com.clayplug.activities.WalkToSpot;
import src.com.clayplug.activities.conditions.IsAtSpot;
import com.clayplug.utilities.btree.Task;
import com.clayplug.utilities.btree.composites.Selector;
import com.clayplug.utilities.btree.composites.Sequence;
import com.clayplug.utilities.btree.decorators.Inverter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
public class BuyToolFromBob extends Task {
    private final String tool = "Bronze axe";
    private final Area bobsShop = new Area(3227, 3205, 3233, 3201);

    public BuyToolFromBob(MethodProvider api) {
        super(api);
    }

    public STATUS run() {

        try {
            Task.STATUS status =
                    new Selector(api,
                            new Sequence(api,
                                    new Inverter(api, new IsAtSpot(api, bobsShop)),
                                    new WalkToSpot(api, bobsShop)
                            ),
                            new Sequence(api,
                                    new IsAtSpot(api, bobsShop),
                                    new BuyTool(api, tool)
                            )
                    ).run();
            return status;
        } catch (Exception e) {
            return STATUS.FAILED;
        }
        }

    @Override
    public void terminate() {

    }
    }
