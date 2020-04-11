package angel.anicamod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import angel.anicamod.AnicaDimensionList;
import angel.anicamod.AnicaMod;
import angel.anicamod.util.helpers.TeleportationTools;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class AnicaDimensionTeleportCommand implements Command<CommandSource> {

    private static final AnicaDimensionTeleportCommand CMD = new AnicaDimensionTeleportCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("dim")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        int x = player.getPosition().getX();
        int z = player.getPosition().getZ();
        if (player.dimension.equals(AnicaDimensionList.DIMENSION_TYPE)) {
            TeleportationTools.teleport(player, DimensionType.OVERWORLD, new BlockPos(x, 200, z));
        } else {
            TeleportationTools.teleport(player, AnicaDimensionList.DIMENSION_TYPE, new BlockPos(x, 200, z));
        }
        return 0;
    }
}
