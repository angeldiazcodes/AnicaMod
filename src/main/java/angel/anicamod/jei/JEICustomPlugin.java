package angel.anicamod.jei;

import angel.anicamod.AnicaMod;
import mezz.jei.api.IModPlugin;
import net.minecraft.util.ResourceLocation;

// https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/1.15/src/main/java/com/buuz135/industrial/jei/JEICustomPlugin.java
// https://github.com/mezz/JustEnoughItems/wiki/Getting-Started

public class JEICustomPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		// TODO Auto-generated method stub
		return new ResourceLocation(AnicaMod.MODID, "default");
	}

}
