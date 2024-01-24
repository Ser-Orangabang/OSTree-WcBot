package src.com.clayplug.scripts;

import src.com.clayplug.activities.DropWood;
import src.com.clayplug.activities.*;
import src.com.clayplug.activities.conditions.*;
import com.clayplug.utilities.btree.Task;
import com.clayplug.utilities.btree.composites.Selector;
import com.clayplug.utilities.btree.composites.Sequence;
import com.clayplug.utilities.btree.decorators.Inverter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.util.Arrays;

import static src.com.clayplug.activities.Chop.goChop;

@ScriptManifest(name = "WoodChopper", author = "Ser Orangabang", version = 0.1, info = "Chops wood up to level 15", logo = "")

public class WoodChopper extends Script {

    public void onStart() throws InterruptedException {
        log("Wood will be chopped! (Chopper started)");

        int woodcuttingLevel = skills.getStatic(Skill.WOODCUTTING);
        determineWoodCuttingStrategy(woodcuttingLevel);
    }

    private void determineWoodCuttingStrategy(int woodcuttingLevel) {
        if (skills.getStatic(Skill.WOODCUTTING) >= 15) {
            goChop();
        }
    }

    public int onLoop() throws InterruptedException {
        try {
            Task.STATUS test =
                    new Selector(this,
                            new IsMoving(this),
                            new Selector(this,
                                    new Sequence(this,
                                            new HasInventorySpace(this),
                                            new Inverter(this, new HasItem(this, "Bronze axe")),
                                            new Selector(this,
                                                    new Sequence(this,
                                                            new HasMoney (this, 16),
                                                            new BuyToolFromBob(this)),
                                                    new Sequence(this,
                                                            new Inverter(this, new HasMoney(this, 16)),
                                                            new Selector(this,
                                                                    new Sequence(this,
                                                                                new Inverter(this, new HasItems(this,"Small fishing net", 5)),
                                                                            new WalkToSpot(this, new Area(3241, 3160, 3245, 3154)),
                                                                            new TakeObject(this, "Small fishing net"),
                                                                                new TakeObject(this, "Small fishing net"),
                                                                                new TakeObject(this, "Small fishing net"),
                                                                                new TakeObject(this, "Small fishing net"),
                                                                                new TakeObject(this, "Small fishing net")
                                                                    ),
                                                                    new Sequence(this,
                                                                            new HasItems(this, "Small fishing net", 5),
                                                                            new SellToGerrants(this, "Small fishing net", 5)
                                                                    )
                                                            )
                                                    )
                                            )
                                    ),
                                            new Sequence(this,
                                                    new Sequence(this,
                                                            new IsAtSpot(this, Chop.getChoppingArea()),
                                                            new HasItem(this, "Bronze axe"),
                                                            new HasInventorySpace(this)
                                                    ),
                                                    new Chop(this)
                                            ),
                                    new Sequence(this,
                                            new Sequence(this,
                                                    new HasInventorySpace(this),
                                                    new HasItem(this, "Bronze axe"),
                                                    new Inverter(this, new IsAtSpot(this, Chop.getChoppingArea()))
                                            ),
                                            new WalkToSpot(this, Chop.getChoppingArea())
                                    ),
                                        new Sequence(this,
                                                new Sequence(this,
                                                        new Inverter(this, new HasInventorySpace(this))
                                                ),
                                                new DropWood(this)
                                        )
                            )
                    ).run();
            } catch (NullPointerException e) {
            log(Arrays.toString(e.getStackTrace()));
            }

            return random(1000, 9001);
    }}