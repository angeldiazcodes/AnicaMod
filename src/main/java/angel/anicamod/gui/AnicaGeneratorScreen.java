package angel.anicamod.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import angel.anicamod.AnicaMod;
import angel.anicamod.containers.AnicaGeneratorContainer;
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
public class AnicaGeneratorScreen extends ContainerScreen<AnicaGeneratorContainer> {

	private static boolean debug = false;
	private ResourceLocation GUI = new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_GENERATOR_GUI );
	private AnicaGeneratorContainer container;
	
	public AnicaGeneratorScreen(AnicaGeneratorContainer container, PlayerInventory inv, ITextComponent name)
	{
		super(container, inv, name);
		this.container = container;
		AnicaMod.log(debug,"AnicaGeneratorScreen: Constructor");
	}

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        AnicaMod.log(debug,"AnicaGeneratorScreen: render");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    	AnicaMod.log(debug,"AnicaGeneratorScreen: drawGuiContainerForegroundLayer");
		drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);     
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F); // color4f 
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
        AnicaMod.log(debug,"AnicaGeneratorScreen: drawGuiContainerBackgroundLayer");
    }

}
