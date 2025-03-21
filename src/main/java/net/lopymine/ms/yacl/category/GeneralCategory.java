package net.lopymine.ms.yacl.category;

import dev.isxander.yacl3.api.*;
import lombok.experimental.ExtensionMethod;

import net.lopymine.ms.config.MoreSpaceConfig;
import net.lopymine.ms.yacl.custom.base.*;
import net.lopymine.ms.yacl.custom.extension.SimpleOptionExtension;
import net.lopymine.ms.yacl.custom.utils.SimpleContent;

@ExtensionMethod(SimpleOptionExtension.class)
public class GeneralCategory {

	public static ConfigCategory get(MoreSpaceConfig defConfig, MoreSpaceConfig config) {
		return SimpleCategory.startBuilder("general")
				.groups(getMainGroup(defConfig, config))
				.groups(getHidingGroup(defConfig, config))
				.groups(getGameplayGroup(defConfig, config))
				.build();
	}

	private static OptionGroup getMainGroup(MoreSpaceConfig defConfig, MoreSpaceConfig config) {
		return SimpleGroup.startBuilder("main").options(
				SimpleOption.<Boolean>startBuilder("mod_enabled")
						.withBinding(defConfig.isModEnabled(), config::isModEnabled, config::setModEnabled, true)
						.withController()
						.withDescription(SimpleContent.NONE)
						.build()
		).build();
	}

	private static OptionGroup getHidingGroup(MoreSpaceConfig defConfig, MoreSpaceConfig config) {
		return SimpleGroup.startBuilder("hiding").options(
				SimpleOption.<Float>startBuilder("hiding_activation_distance")
						.withBinding(defConfig.getHidingActivationDistance(), config::getHidingActivationDistance, config::setHidingActivationDistance, true)
						.withController(0.0F, 32.0F, 0.1F)
						.withDescription(SimpleContent.NONE)
						.build(),
				SimpleOption.<Float>startBuilder("full_hiding_distance")
						.withBinding(defConfig.getFullHidingDistance(), config::getFullHidingDistance, config::setFullHidingDistance, true)
						.withController(0.0F, 32.0F, 0.1F)
						.withDescription(SimpleContent.NONE)
						.build(),
				SimpleOption.<Float>startBuilder("min_hiding_value")
						.withBinding(defConfig.getMinHidingValue(), config::getMinHidingValue, config::setMinHidingValue, true)
						.withController(0.0F, 1.0F, 0.1F)
						.withDescription(SimpleContent.NONE)
						.build(),
				SimpleOption.<Boolean>startBuilder("hide_shadow_enabled")
						.withBinding(defConfig.isHideShadowEnabled(), config::isHideShadowEnabled, config::setHideShadowEnabled, true)
						.withController()
						.withDescription(SimpleContent.NONE)
						.build()
		).build();
	}

	private static OptionGroup getGameplayGroup(MoreSpaceConfig defConfig, MoreSpaceConfig config) {
		return SimpleGroup.startBuilder("gameplay").options(
				SimpleOption.<Boolean>startBuilder("click_through_translucent_entities_enabled")
						.withBinding(defConfig.isClickThroughTranslucentPlayersEnabled(), config::isClickThroughTranslucentPlayersEnabled, config::setClickThroughTranslucentPlayersEnabled, true)
						.withController()
						.withDescription(SimpleContent.NONE)
						.build()
		).build();
	}

}
