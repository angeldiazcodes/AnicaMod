package angel.anicamod.tileentity;

import angel.anicamod.AnicaMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class AnicaEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

	private static boolean debug = false;
	private boolean canRecieve = true;
	
    public AnicaEnergyStorage(int capacity, int maxTransfer, boolean canRecieveIn ) {
        super(capacity, maxTransfer);
        canRecieve = canRecieveIn;
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
    	return canRecieve;
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
    		AnicaMod.log(debug, "AnicaEnergyStorage: adding engergy " + maxReceive);
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
