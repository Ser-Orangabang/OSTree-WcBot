package com.clayplug.utilities.btree.composites;

import com.clayplug.utilities.btree.Task;
import org.osbot.rs07.script.MethodProvider;

import java.util.ArrayList;

import static com.clayplug.utilities.btree.Task.STATUS.FAILED;
import static com.clayplug.utilities.btree.Task.STATUS.SUCCEEDED;
import static java.util.Collections.shuffle;

public class NonDeterministicSelector extends Selector {
    public NonDeterministicSelector(MethodProvider api, ArrayList<Task> children) {
        super(api, children);
    }

    public NonDeterministicSelector(MethodProvider api, Task... children) {
        super(api, children);
    }

    @Override
    public Task.STATUS run() {
        shuffle(children);
        for (Task task : children) {
            if (task.run() == SUCCEEDED) {
                return SUCCEEDED;
            }
        }
        return FAILED;
    }
}
