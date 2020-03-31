package angel.anicamod.tileentity;

import angel.anicamod.AnicaMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class AnicaEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

	private static boolean debug = false;
	
    public AnicaEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
        if (this.energy > getMaxEnergyStored()) {
            this.energy = getEnergyStored();
        }
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
        if (this.energy < 0 ) {
            this.energy = 0;
        }
    }
    
    @Override 
    public boolean canReceive() {
    	return true;
    }
    
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
    	if ( (this.energy + maxReceive) > this.capacity )
    	{
    		setEnergy( capacity );
    		return 0; // not quiet right, should return balance of enegery used 
    	}
    	else
    	{
    		if (AnicaEnergyStorage.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaEnergyStorage: adding engergy " + maxReceive);
    		addEnergy( maxReceive );
    		return maxReceive;
    	}
    }
    
    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("energy", getEnergyStored());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setEnergy(nbt.getInt("energy"));
    }
    
}
