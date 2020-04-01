package angel.anicamod;

import angel.anicamod.fluid.AnicaAcid;
import angel.anicamod.fluid.AnicaAcid.Flowing;
import angel.anicamod.fluid.AnicaAcid.Source;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AnicaMod.MODID)
public class AnicaModFluidsList {	
		
		public static AnicaAcid.Flowing ANICA_FLOWING_ACID = (Flowing) new AnicaAcid.Flowing().setRegistryName( new ResourceLocation (AnicaMod.MODID, AnicaMod.ANICA_ACID_FLOW));
		public static  AnicaAcid.Source ANICA_ACID_STILL = (Source) new AnicaAcid.Source().setRegistryName( new ResourceLocation (AnicaMod.MODID, AnicaMod.ANICA_ACID_STILL)); //ModUtil._null();
		private static boolean debug = true;
		
		public static class Tags {
				public static final Tag<Fluid> ANICA_ACID = new FluidTags.Wrapper( new ResourceLocation( AnicaMod.MODID, AnicaMod.ANICA_ACID_STILL)); /// ANICA_ACID
		}
		
	    public static void registerFluids(final IForgeRegistry<Fluid> registry) {
	        if (AnicaModFluidsList.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaModFluidsList: registerFluids" );
	        
			final Fluid [] fluids = {
					ANICA_FLOWING_ACID,
					ANICA_ACID_STILL
			};
			
	        registry.registerAll(fluids);
	    }

	}
