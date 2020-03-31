package angel.anicamod.world.structures;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModStrucutresPiecesList;
import angel.anicamod.world.template.AnicaSingleJigsawPiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AnicaCabinPieces {
	
    @SuppressWarnings("deprecation")
	public static void func_215139_a(ChunkGenerator<?> p_215139_0_, TemplateManager p_215139_1_, BlockPos p_215139_2_, List<StructurePiece> p_215139_3_, SharedSeedRandom p_215139_4_) {
    	ResourceLocation anicaCabin = new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_CABIN);
    	ResourceLocation empty = new ResourceLocation( "empty" );    	

    	JigsawManager.REGISTRY.register(new JigsawPattern(anicaCabin , empty, 
    			ImmutableList.of(Pair.of(new AnicaSingleJigsawPiece(AnicaMod.MODID + ":" + AnicaMod.ANICA_CABIN), 1) ), 
    			JigsawPattern.PlacementBehaviour.RIGID)); // JigsawManager.REGISTRY
    	
        JigsawManager.func_214889_a(new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_CABIN), 7, AnicaCabinPiece::new, p_215139_0_, p_215139_1_, p_215139_2_, p_215139_3_, p_215139_4_);
    }

    public static class AnicaCabinPiece extends AbstractVillagePiece {

        public AnicaCabinPiece(TemplateManager p_i50560_1_, JigsawPiece p_i50560_2_, BlockPos p_i50560_3_, int p_i50560_4_, Rotation p_i50560_5_, MutableBoundingBox p_i50560_6_) {
            super(AnicaModStrucutresPiecesList.ANICA_CABIN, p_i50560_1_, p_i50560_2_, p_i50560_3_, p_i50560_4_, p_i50560_5_, p_i50560_6_);
        }

        public AnicaCabinPiece(TemplateManager p_i50561_1_, CompoundNBT p_i50561_2_) {
            super(p_i50561_1_, p_i50561_2_, AnicaModStrucutresPiecesList.ANICA_CABIN);
        }
    }
}
