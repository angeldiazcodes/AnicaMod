package angel.anicamod.entities;

import angel.anicamod.AnicaMod;
import angel.anicamod.AnicaModEntities;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class AnicaMobEntity extends CreatureEntity {

	private static boolean debug = false;
	
	@SuppressWarnings("unchecked")
    public AnicaMobEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super( (EntityType<? extends CreatureEntity>) AnicaModEntities.anica_mob_entity, worldIn);
        if (AnicaMobEntity.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaMobEntity: Constructor");
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(0, new SwimGoal(this) ); // goal priority, goal, ..
    	this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.2d) ); 
    	this.goalSelector.addGoal(1, new LookRandomlyGoal(this) );
    }
    
    @Override
    protected void registerAttributes()
    {
    	super.registerAttributes();
    	this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0d);
    	this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2d);
    }
}
