package angel.anicamod.world.structures;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import angel.anicamod.AnicaMod;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AnicaCabinStrucutre extends Structure<NoFeatureConfig> {

	private static boolean debug = false;
	
    @SuppressWarnings("WeakerAccess")
    public static final String NAME = AnicaMod.MODID +  ":" + AnicaMod.ANICA_CABIN;
    private static final int CHUNK_RADIUS = 3;
    private static final int FEATURE_DISTANCE = 35;
    private static final int FEATURE_SEPARATION = 8;
    private static final int FREQUENCY = AnicaMod.ANICA_CABIN_FREQUENCY; // lower the number the less cabin's spawned
    
	public AnicaCabinStrucutre(Function<Dynamic<?>, ? extends NoFeatureConfig> deserialize) {
		super(deserialize);
		AnicaMod.log(debug,"AnicaCabinStrucutre: Constructor ");
	}

	@Override
	public boolean func_225558_a_(BiomeManager arg0, ChunkGenerator arg1, Random arg2, int arg3, int arg4, Biome biome) {
		AnicaMod.log(debug, "AnicaCabinStrucutre:func_225558_a_: Starting ... ");
    	
    	Random rand = new Random();
    	int rand_int1 = rand.nextInt(1000);
    	
    	if ( rand_int1 < FREQUENCY )
    	{
    		AnicaMod.log(debug,"AnicaCabinStrucutre:func_225558_a_: TRUE");
    		return true;
    	}
    	else return false;
	}

	@Override
	public int getSize() {
		return CHUNK_RADIUS;
	}

	@Override
	public IStartFactory getStartFactory() {
		AnicaMod.log(debug, "AnicaCabinStrucutre:getStartFactory");
		return Start::new;
	}

	@Override
	public String getStructureName() {
		return NAME;
	}

    public static class Start extends MarginedStructureStart {

        public Start(Structure<?> p_i225874_1_, int p_i225874_2_, int p_i225874_3_, MutableBoundingBox p_i225874_4_, int p_i225874_5_, long p_i225874_6_) {
            super(p_i225874_1_, p_i225874_2_, p_i225874_3_, p_i225874_4_, p_i225874_5_, p_i225874_6_);
            AnicaMod.log(debug, "AnicaCabinStrucutre:Start: Constructor" );
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
        	AnicaMod.log(debug,"AnicaCabinStrucutre:Start:init starting ... ");
            
        	BlockPos blockpos = new BlockPos(chunkX * 16, 10, chunkZ * 16);

            AnicaCabinPieces.func_215139_a(generator, templateManagerIn, blockpos, this.components, this.rand);
            
            this.recalculateStructureSize();
        }
    }
}
