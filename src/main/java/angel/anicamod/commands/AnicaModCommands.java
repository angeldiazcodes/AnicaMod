package angel.anicamod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import angel.anicamod.AnicaMod;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class AnicaModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(
                Commands.literal(AnicaMod.MODID)
                        .then(AnicaCommand.register(dispatcher))
                        .then(AnicaDimensionTeleportCommand.register(dispatcher))
        );

        dispatcher.register(Commands.literal("tut").redirect(cmdTut));
    }

}
