package angel.anicamod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import angel.anicamod.network.Networking;
import angel.anicamod.network.PacketOpenGUI;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

public class AnicaSpawnerCommand implements Command<CommandSource> {

    private static final AnicaSpawnerCommand CMD = new AnicaSpawnerCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("spawn")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        Networking.INSTANCE.sendTo(new PacketOpenGUI(), player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        return 0;
    }
}