package tokyo.ramune.origami.system;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Command {
    private final String name;
    @Nullable
    private final Function<CommandArgs, Boolean> onCommand;
    @Nullable
    private final Function<CommandArgs, List<String>> onTabComplete;

    public Command(@Nonnull String name, @Nullable Function<CommandArgs, Boolean> onCommand, @Nullable Function<CommandArgs, List<String>> onTabComplete) {
        this.name = name;
        this.onCommand = onCommand;
        this.onTabComplete = onTabComplete;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public Function<CommandArgs, Boolean> getOnCommand() {
        return onCommand;
    }

    @Nullable
    public Function<CommandArgs, List<String>> getOnTabComplete() {
        return onTabComplete;
    }
}
