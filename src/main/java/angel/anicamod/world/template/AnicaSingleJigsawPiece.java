package angel.anicamod.world.template;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.util.IDynamicDeserializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.IJigsawDeserializer;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.JigsawReplacementStructureProcessor;
import net.minecraft.world.gen.feature.template.NopProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.Template.BlockInfo;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class AnicaSingleJigsawPiece extends JigsawPiece {
    protected final ResourceLocation location;
    protected final ImmutableList<StructureProcessor> processors;

    @Deprecated
    public AnicaSingleJigsawPiece(String p_i51400_1_, List<StructureProcessor> p_i51400_2_) {
        this(p_i51400_1_, p_i51400_2_, JigsawPattern.PlacementBehaviour.RIGID);
    }

    public AnicaSingleJigsawPiece(String location, List<StructureProcessor> processors, JigsawPattern.PlacementBehaviour p_i51401_3_) {
        super(p_i51401_3_);
        this.location = new ResourceLocation(location);
        this.processors = ImmutableList.copyOf(processors);
    }

    @Deprecated
    public AnicaSingleJigsawPiece(String location) {
        this(location, ImmutableList.of());
    }

    public AnicaSingleJigsawPiece(Dynamic<?> p_i51403_1_) {
        super(p_i51403_1_);
        this.location = new ResourceLocation(p_i51403_1_.get("location").asString(""));
        this.processors = ImmutableList.copyOf(p_i51403_1_.get("processors").asList((p_214858_0_) -> {
            return IDynamicDeserializer.func_214907_a(p_214858_0_, Registry.STRUCTURE_PROCESSOR, "processor_type", NopProcessor.INSTANCE);
        }));
    }

    public List<Template.BlockInfo> func_214857_a(TemplateManager p_214857_1_, BlockPos p_214857_2_, Rotation p_214857_3_, boolean p_214857_4_) {
        Template template = p_214857_1_.getTemplateDefaulted(this.location);
        List<Template.BlockInfo> list = template.func_215386_a(p_214857_2_, (new PlacementSettings()).setRotation(p_214857_3_), Blocks.STRUCTURE_BLOCK, p_214857_4_);
        List<Template.BlockInfo> list1 = Lists.newArrayList();

        for(Template.BlockInfo template$blockinfo : list) {
            if (template$blockinfo.nbt != null) {
                StructureMode structuremode = StructureMode.valueOf(template$blockinfo.nbt.getString("mode"));
                if (structuremode == StructureMode.DATA) {
                    list1.add(template$blockinfo);
                }
            }
        }

        return list1;
    }

    @Override
    public List<Template.BlockInfo> getJigsawBlocks(TemplateManager p_214849_1_, BlockPos p_214849_2_, Rotation p_214849_3_, Random p_214849_4_) {
        Template template = p_214849_1_.getTemplateDefaulted(this.location);
        List<Template.BlockInfo> list = template.func_215386_a(p_214849_2_, (new PlacementSettings()).setRotation(p_214849_3_), Blocks.JIGSAW, true);
        Collections.shuffle(list, p_214849_4_);
        // Blocks.JIGSAW 
        return list;
    }

    @Override
    public MutableBoundingBox getBoundingBox(TemplateManager p_214852_1_, BlockPos p_214852_2_, Rotation p_214852_3_) {
        Template template = p_214852_1_.getTemplateDefaulted(this.location);
        return template.getMutableBoundingBox((new PlacementSettings()).setRotation(p_214852_3_), p_214852_2_); // getMutableBoundingBox
    }

    public boolean func_225575_a_(TemplateManager p_225575_1_, IWorld p_225575_2_, ChunkGenerator<?> p_225575_3_, BlockPos p_225575_4_, Rotation p_225575_5_, MutableBoundingBox p_225575_6_, Random p_225575_7_) {
        Template template = p_225575_1_.getTemplateDefaulted(this.location);
        PlacementSettings placementsettings = this.func_214860_a(p_225575_5_, p_225575_6_);
        if (!template.addBlocksToWorld(p_225575_2_, p_225575_4_, placementsettings, 18)) {
            return false;
        } else {
            for(Template.BlockInfo template$blockinfo : Template.processBlockInfos(template, p_225575_2_, p_225575_4_, placementsettings, this.func_214857_a(p_225575_1_, p_225575_4_, p_225575_5_, false))) {
                this.func_214846_a(p_225575_2_, template$blockinfo, p_225575_4_, p_225575_5_, p_225575_7_, p_225575_6_);
            }

            return true;
        }
    }

    protected PlacementSettings func_214860_a(Rotation rotation, MutableBoundingBox boundingBox) {
        PlacementSettings placementsettings = new PlacementSettings();
        placementsettings.setBoundingBox(boundingBox);
        placementsettings.setRotation(rotation);
        placementsettings.func_215223_c(true);
        placementsettings.setIgnoreEntities(false);
        placementsettings.addProcessor(JigsawReplacementStructureProcessor.INSTANCE);
        this.processors.forEach(placementsettings::addProcessor);
        return placementsettings;
    }

    public IJigsawDeserializer getType() {
        return IJigsawDeserializer.SINGLE_POOL_ELEMENT;
    }

    public <T> Dynamic<T> serialize0(DynamicOps<T> p_214851_1_) {
        return new Dynamic<>(p_214851_1_, p_214851_1_.createMap(ImmutableMap.of(p_214851_1_.createString("location"), p_214851_1_.createString(this.location.toString()), p_214851_1_.createString("processors"), p_214851_1_.createList(this.processors.stream().map((p_214859_1_) -> {
            return p_214859_1_.serialize(p_214851_1_).getValue();
        })))));
    }

    public String toString() {
        return "Single[" + this.location + "]";
    }

}
