package dev.rabies.vox.commands;

import dev.rabies.vox.VoxMod;
import dev.rabies.vox.features.Feature;

public class ToggleCommand extends BaseCommand {

    public ToggleCommand() {
        super(new String[]{"toggle", "t"});
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) { // .t <null>
            // TODO: 2022/11/26 Usage 
            return;
        }

        Feature feature = VoxMod.getInstance().getFeatureByName(args[1]);
        if (feature == null) {
            // TODO: 2022/11/26 Log 
            return;
        }

        // TODO: 2022/11/26 Log 
        feature.toggle();
    }
}
