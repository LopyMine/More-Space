package net.lopymine.ms.mixin.builtin.conduit;

//? if <=1.21.3 {
/*import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.ConduitBlockEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import net.lopymine.ms.render.TransparencyManager;
import net.lopymine.ms.render.MoreSpaceLayers;

import java.util.function.Function;

@Mixin(ConduitBlockEntityRenderer.class)
public class ConduitBlockEntityRendererMixin {

	@ModifyArg(method = "render(Lnet/minecraft/block/entity/ConduitBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;", ordinal = 0), index = 1)
	private Function<Identifier, RenderLayer> modifyRenderLayer(Function<Identifier, RenderLayer> layerFactory) {
	    return MoreSpaceLayers.getLayer(layerFactory);
	}

	@ModifyArg(method = "render(Lnet/minecraft/block/entity/ConduitBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;", ordinal = 1), index = 1)
	private Function<Identifier, RenderLayer> modifyRenderLayer1(Function<Identifier, RenderLayer> layerFactory) {
	    return MoreSpaceLayers.getLayer(layerFactory);
	}

	@ModifyArg(method = "render(Lnet/minecraft/block/entity/ConduitBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;", ordinal = 2), index = 1)
	private Function<Identifier, RenderLayer> modifyRenderLayer2(Function<Identifier, RenderLayer> layerFactory) {
	    return MoreSpaceLayers.getLayer(layerFactory);
	}

	@ModifyArg(method = "render(Lnet/minecraft/block/entity/ConduitBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;", ordinal = 3), index = 1)
	private Function<Identifier, RenderLayer> modifyRenderLayer3(Function<Identifier, RenderLayer> layerFactory) {
	    return MoreSpaceLayers.getLayer(layerFactory);
	}
}

*///?}
