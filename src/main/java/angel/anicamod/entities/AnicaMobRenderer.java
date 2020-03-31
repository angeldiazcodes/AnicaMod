package angel.anicamod.entities;

import javax.annotation.Nullable;

import angel.anicamod.AnicaMod;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class AnicaMobRenderer extends LivingRenderer<AnicaMobEntity, AnicaMobModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(AnicaMod.MODID, AnicaMod.ANICA_MOB_ENTITY_TEXTURE);

    public AnicaMobRenderer(EntityRendererManager manager) {
        super(manager, new AnicaMobModel(), 0.5f);
    }

    @Nullable
    @Override
	public ResourceLocation getEntityTexture(AnicaMobEntity entity) {
        return TEXTURE;
    }
    
    public static class RenderFactory implements IRenderFactory<AnicaMobEntity>
    {

		@Override
		public EntityRenderer<? super AnicaMobEntity> createRenderFor(EntityRendererManager manager) {
			// TODO Auto-generated method stub
			return new AnicaMobRenderer(manager);
		}
    	
    }
}
