package dev.rabies.vox.commands;

import dev.rabies.vox.Globals;

public abstract class BaseCommand implements Command, Globals {

    private final String[] aliases;

    public BaseCommand(String[] aliases) {
        this.aliases = aliases;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }
}
