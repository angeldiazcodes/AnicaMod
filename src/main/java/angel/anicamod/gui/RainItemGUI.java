package angel.anicamod.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import angel.anicamod.AnicaMod;
import angel.anicamod.items.RainItem;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

public class RainItemGUI extends Screen {

	private static boolean debug = false;
	private static int RAIN_ITEM_ENERGY_CONSUMPTION = 1000;
	private ResourceLocation GUI = new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_RAIN_ITEM_GUI );
	private PlayerEntity playerIn = null;
	private Hand handIn = null;
	private ItemStack stack = null;
	private World worldIn = null;
	private WorldInfo worldInfo = null;
	private int xSize  = 256;
	private int ySize = 256;
	
	private Button rainOn  = null;
	private Button rainOff = null;
	private int buttonWidth = 100;
	private int buttonHeight = 20;
	
	public RainItemGUI(ITextComponent titleIn) {
		super(titleIn);
		AnicaMod.log(debug, "RainItemGUI: Constuctor ");
	}
 
	public RainItemGUI(ITextComponent titleIn, World worldIn, PlayerEntity playerIn, Hand handIn) {
		super(titleIn);
		AnicaMod.log(debug,"RainItemGUI: Constuctor long");
		this.playerIn = playerIn;
		this.handIn = handIn;
		if (playerIn != null ) this.stack = playerIn.getHeldItem(handIn);
		this.worldIn = worldIn;
		if ( worldIn != null) this.worldInfo = worldIn.getWorldInfo();
	}
	
	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		AnicaMod.log(debug,"RainItemGUI: render ");
		this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        int stringWidth = 0;
        int stringHeight = 0;
        String energyString = null;
        
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
        
        if ( stack.getTag() == null ) { stack.setTag( new CompoundNBT() ); stack.getTag().putInt("egy", 0 ); }
        
        energyString= "Energy: " + Integer.toString((stack.getTag().getInt("egy")));
        stringWidth = font.getStringWidth(energyString);
        stringHeight = font.FONT_HEIGHT;
        		
        drawString(this.font, energyString , (this.width/2) - stringWidth/2, (this.height/2) - stringHeight/2, 0xffffff);     

		super.render(p_render_1_, p_render_2_, p_render_3_);
	}
	
	/*
	 * void init()
	 * 
	 * Description: Called when init as well as window size change
	 * 
	 */
	
	@Override
	protected void init() {
		buttons.clear();

		AnicaMod.log(debug, "RainItemGUI: init ");
		if ( worldInfo != null )
		{
		rainOn  = new Button((this.width/2) - buttonWidth - 5 , (this.height/2) + 20 , buttonWidth, buttonHeight, "Rain On", 
				(button) -> { 
					
					if ( stack.getTag() == null ) { stack.setTag( new CompoundNBT() ); stack.getTag().putInt("egy", 0 ); }
					
					int currentEnergy =stack.getTag().getInt("egy");
					int newEnergy = currentEnergy - RAIN_ITEM_ENERGY_CONSUMPTION;
					
					if ( newEnergy > 0 )
					{
						stack.getTag().putInt("egy", newEnergy );
						
						AnicaMod.log(debug,"RainItemGUI: rainOn");
						worldInfo.setClearWeatherTime(0);
						worldInfo.setRainTime( 100000 );
						worldInfo.setRaining( true );
						worldInfo.setThundering( false );
						worldIn.setRainStrength(1f);
					
					}
					else
						stack.getTag().putInt("egy", 0 );
					
					//playerIn.setHeldItem(handIn, stack);
					
				});
		
		rainOff =  new Button((this.width/2) + 5, (this.height/2) + 20, buttonWidth, buttonHeight, "Rain Off", 
				(button) -> { 
				
					if ( stack.getTag() == null ) { stack.setTag( new CompoundNBT() ); stack.getTag().putInt("egy", 0 ); }
					
					int currentEnergy =stack.getTag().getInt("egy");
					int newEnergy = currentEnergy - RAIN_ITEM_ENERGY_CONSUMPTION;
					
					if ( newEnergy > 0 )
					{
						stack.getTag().putInt("egy", newEnergy );
						
						AnicaMod.log(debug, "RainItemGUI: rainOff");
						worldInfo.setClearWeatherTime(100000);
						worldInfo.setRainTime( 0 );
						worldInfo.setRaining( false );
						worldInfo.setThundering( false );
						worldIn.setRainStrength(0f);
					
					}
					else
						stack.getTag().putInt("egy", 0 );
					//playerIn.setHeldItem(handIn, stack);
				});
		
		addButton(rainOn);
		addButton(rainOff);
		}
		super.init();
	}
	
	@Override
	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
		return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
	}
	
	@Override
	public boolean isPauseScreen() {
		return super.isPauseScreen();
	}
	
	@Override
	public void onClose() {
		super.onClose();
	}
	
}
