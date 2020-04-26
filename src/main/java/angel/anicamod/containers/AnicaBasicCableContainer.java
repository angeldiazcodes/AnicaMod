package angel.anicamod.containers;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModBlocks;
import angel.anicamod.tileentity.AnicaEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

/*
 * AnicaBasicCableContainer
 * 
 * Description: This is a class that exists on the client and the server and it makes sure that 
 * changes the user does on the client are communicated properly to the server and the other 
 * way around. In the container we store the inventory of the player (as we need to manipulate 
 * player inventory slots too) and the inventory of our tile entity.
 * 
 */

public class AnicaBasicCableContainer extends AnicaContainer {
    
    private static final boolean debug = false;
    
    public AnicaBasicCableContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(windowId, world, pos, playerInventory, player, AnicaModBlocks.anica_basic_cable_container);
        AnicaMod.log(debug, "AnicaBasicCableContainer: Constructor");
       
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy();
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((AnicaEnergyStorage)h).setEnergy(value));
            }
        });
    }
   
    public int getEnergy() {
    	return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }
}
