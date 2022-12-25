package com.vtwo.furtelcraft.furtelcraft.entitymodel;

import com.vtwo.furtelcraft.furtelcraft.entities.TestBaseFurryMob;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import static com.vtwo.furtelcraft.furtelcraft.clientinit.EntityModelInit.MAIN;
import static com.vtwo.furtelcraft.furtelcraft.clientinit.EntityModelInit.TEST_BASE_FURRY_MOB_ID;

public class TestBaseFurryMobModel extends EntityModel<TestBaseFurryMob> {
	public static final EntityModelLayer TEST_BASE_FURRY_MOB = new EntityModelLayer(TEST_BASE_FURRY_MOB_ID,MAIN);
	private final ModelPart leftleg;
	private final ModelPart rightleg;
	private final ModelPart body;
	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart head;
	public TestBaseFurryMobModel(ModelPart root) {
		this.leftleg = root.getChild("leftleg");
		this.rightleg = root.getChild("rightleg");
		this.body = root.getChild("body");
		this.leftarm = root.getChild("leftarm");
		this.rightarm = root.getChild("rightarm");
		this.head = root.getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData leftleg = modelPartData.addChild("leftleg", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

		ModelPartData rightleg = modelPartData.addChild("rightleg", ModelPartBuilder.create().uv(32, 0).cuboid(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 12.0F, 0.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData leftarm = modelPartData.addChild("leftarm", ModelPartBuilder.create().uv(0, 32).cuboid(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData rightarm = modelPartData.addChild("rightarm", ModelPartBuilder.create().uv(24, 16).cuboid(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 32).cuboid(-2.0F, -27.0F, -7.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-5.0F, -33.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.0F, -33.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(TestBaseFurryMob entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightleg.pivotX = MathHelper.cos(limbSwing) * 1.0F * limbSwingAmount;
		this.rightarm.pivotX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.leftleg.pivotX = MathHelper.cos(limbSwing) * -1.0F * limbSwingAmount;
		this.leftarm.pivotX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;

	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		leftleg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightleg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftarm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightarm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}