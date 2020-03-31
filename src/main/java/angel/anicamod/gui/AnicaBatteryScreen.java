package angel.anicamod.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import angel.anicamod.AnicaMod;
import angel.anicamod.containers.AnicaBatteryContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/*
 * AnicaGeneratorScreen
 * 
 * Description: The container is present on both sides but the Screen (the actual GUI) is only 
 * present on the client. When our GUI is opened we will get a reference to the client side 
 * version of the container. We  render our custom background and leave the rest to Minecraft.
 * 
 */
public class AnicaBatteryScreen extends ContainerScreen<AnicaBatteryContainer> {

	private static boolean debug = false;
	private ResourceLocation GUI = new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_BATTERY_GUI );
	private AnicaBatteryContainer container;
	
	public AnicaBatteryScreen(AnicaBatteryContainer container, PlayerInventory inv, ITextComponent name)
	{
		super(container, inv, name);
		this.container = container;
		if (AnicaBatteryScreen.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryScreen: Constructor");
	}

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
		if (AnicaBatteryScreen.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryScreen: render");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if (AnicaBatteryScreen.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryScreen: drawGuiContainerForegroundLayer");
		 drawString(Minecraft.getInstance().fontRenderer, "Stored Energy: " + + container.getEnergy(), 10, 10, 0xffffff);     
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F); // color4f 
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
		if (AnicaBatteryScreen.debug) AnicaMod.logger.info(AnicaMod.logStub + "AnicaBatteryScreen: drawGuiContainerBackgroundLayer");
    }

}
