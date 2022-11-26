package dev.rabies.vox.commands;

public interface Command {
    void execute(String[] args);

    String[] getAliases();
}
