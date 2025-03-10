package net.lopymine.ms.compat.yacl.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.YetAnotherConfigLib.Builder;
import dev.isxander.yacl3.impl.YetAnotherConfigLibImpl;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import net.lopymine.ms.utils.yacl.*;

@Pseudo
@Mixin(YetAnotherConfigLibImpl.BuilderImpl.class)
public class YetAnotherConfigLibImplBuilderMixin implements BetterYACLScreenBuilder {

	@Dynamic
	@Unique
	private boolean enabled;

	@Dynamic
	@ModifyReturnValue(at = @At("RETURN"), method = "build", remap = false)
	private YetAnotherConfigLib swapScreen(YetAnotherConfigLib original) {
		if (!enabled) {
			return original;
		}
		return ((BetterYACLScreenConfig) original).moreSpace$enable();
	}

	@Override
	public Builder moreSpace$enable() {
		this.enabled = true;
		return ((Builder) this);
	}
}
