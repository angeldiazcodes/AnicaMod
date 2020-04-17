package angel.anicamod.world.structures;

import com.mojang.datafixers.Dynamic;

import angel.anicamod.AnicaMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

/**
 * The structure class is mostly used to decide where the structure will be, structure pieces focus on actual generation
 * This is a scattered structure that spawns randomly in the world
 * See getStartPositionForPosition in ScatteredStructure for details on how placement is chosen
 * For custom placement, overwrite Structure itself and implement the hasStartAt method
 */
public class AnicaHutStructure extends ScatteredStructure<NoFeatureConfig> {
	
	private static boolean debug = true;
	
	public AnicaHutStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
		super(config);
	}
	
	@Override
	public boolean func_225558_a_(BiomeManager arg0, ChunkGenerator arg1, Random arg2, int arg3, int arg4, Biome biome) {
    	if (AnicaHutStructure.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaHutStructure:func_225558_a_: " + biome.getDisplayName().toString());
    	
    	Random rand = new Random();
    	int rand_int1 = rand.nextInt(1000);
    	
    	if ( rand_int1 < 10 )
    	{
    		if (AnicaHutStructure.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaHutStructure:func_225558_a_: TRUE");
    		return true;
    	}
    	else return false;
	}
	
	/**
	 * A modifier to the random seed for scattered structures, so that every structure does not spawn in the same place
	 *
	 * @return a number, vanilla likes to specifically use 8 digit numbers
	 */
	@Override
	protected int getSeedModifier() {
		return 12345678;
	}
	
	/**
	 * Get the start of the structure
	 * In vanilla the start is usually a static inner class of the structure class, follow this convention if you wish
	 *
	 * @return method reference to Start constructor
	 */
	@Override
	public IStartFactory getStartFactory() {
		return Start::new;
	}
	
	/**
	 * An 'id' for the structure, distinct from registry id
	 * Used for the Locate command (by forge only, vanilla uses its own system)
	 * Should probably be in the format 'modid:name'
	 *
	 * @return name of structure
	 */
	@Override
	public String getStructureName() {
		return AnicaMod.MODID + ":little_hut";
	}
	
	/**
	 * Legacy code, only used to update from old structure format
	 *
	 * @return irrelevant
	 */
	@Override
	public int getSize() {
		return 3;
	}
	
	/**
	 * The structure start is responsible for creating the structure in memory, but not for placing the blocks themselves
	 */
	public static class Start extends StructureStart {
		
		public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed) {
			super(structure, chunkX, chunkZ, boundingBox, references, seed);
		}
		
		/**
		 * For most structures this is the only method you will need to care about
		 * Not a lot needs to be done here, most of the work is done by structure pieces
		 * Examples of things vanilla does for different structures here include:
		 * - Getting configs from the chunk generator
		 * - Deciding the rotation of the structure
		 * - Getting height (rarely, most times height is determined in the piece)
		 */
		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
			//For the purposes of this demonstration I am using this space to decide which structure file to use and what rotation the structure is at
			
			ResourceLocation templateResource;
			
			if(this.rand.nextBoolean()) {
				//data/structureexample/structures/oak_hut.nbt
				templateResource = new ResourceLocation(AnicaMod.MODID, "oak_hut");
			}
			else {
				//data/structureexample/structures/spruce_hut.nbt
				templateResource = new ResourceLocation(AnicaMod.MODID, "spruce_hut");
			}
			
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			
			AnicaHutPiece piece = new AnicaHutPiece(templateManager, templateResource, new BlockPos(chunkX * 16, 10, chunkZ * 16), rotation);
			
			//piece.func_225577_a_( null, generator, this.rand, this.getBoundingBox() , new BlockPos(chunkX * 16, 10, chunkZ * 16) );
			
			//The important thing is that pieces are added like this
			//See the shipwreck and igloo for an alternate take on this process
			this.components.add(piece);
			
			//This should be called last, after all components have been added
			this.recalculateStructureSize();
			
            
		}
	}
}