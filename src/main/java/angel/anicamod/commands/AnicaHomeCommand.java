package angel.anicamod.commands;

import java.util.Random;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import angel.anicamod.AnicaModStrucutresList;
import angel.anicamod.AnicaModStrucutresPiecesList;
import angel.anicamod.network.Networking;
import angel.anicamod.network.PacketOpenGUI;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.fml.network.NetworkDirection;

public class AnicaHomeCommand implements Command<CommandSource> {

    private static final AnicaHomeCommand CMD = new AnicaHomeCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("home")
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        BlockPos pos = player.getPosition();
        World world = player.getEntityWorld();
        
        // world.getBiome(pos).addStructure(AnicaModStrucutresList.ANICA_CABIN.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        //StructureRegistry.LITTLE_HUT.place(world, generator, rand, pos, config)
        return 0;
    }
}