package angel.anicamod;

import angel.anicamod.world.structures.AnicaCabinPieces;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

public class AnicaModStrucutresPiecesList {
    public static final IStructurePieceType ANICA_CABIN = register( AnicaMod.ANICA_CABIN, AnicaCabinPieces.AnicaCabinPiece::new);
    
    private static IStructurePieceType register(String key, IStructurePieceType type) {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(AnicaMod.MODID, key), type);
    }
}
