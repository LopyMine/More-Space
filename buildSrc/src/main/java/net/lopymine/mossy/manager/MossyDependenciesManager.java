package net.lopymine.mossy.manager;

import dev.kikugie.stonecutter.StonecutterBuild;
import lombok.experimental.ExtensionMethod;
import org.gradle.api.*;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;

import net.lopymine.mossy.*;
import net.lopymine.mossy.extension.MossyDependenciesExtension;

import java.util.Map;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

@ExtensionMethod(MossyPlugin.class)
public class MossyDependenciesManager {

	private static void addDependencies(MossyDependenciesExtension extension, Project project) {
		String minecraft = extension.getMinecraft();
		String mappings = extension.getMappings();
		String fabricApi = extension.getFabricApi();
		String fabricLoader = extension.getFabricLoader();
		String lombok = extension.getLombok();

		DependencyHandler dependencies = project.getDependencies();
		dependencies.add("minecraft", "com.mojang:minecraft:%s".formatted(minecraft));
		dependencies.add("mappings", "net.fabricmc:yarn:%s:v2".formatted(mappings));
		dependencies.add("modImplementation", "net.fabricmc.fabric-api:fabric-api:%s".formatted(fabricApi));
		dependencies.add("modImplementation", "net.fabricmc:fabric-loader:%s".formatted(fabricLoader));
		dependencies.add("compileOnly", "org.projectlombok:lombok:%s".formatted(lombok));
		dependencies.add("annotationProcessor", "org.projectlombok:lombok:%s".formatted(lombok));

		Map<String, String> properties = project.getMossyProperties("dep");
		String yacl = properties.remove("yacl");

		if (yacl != null) {
			dependencies.add("modImplementation", "dev.isxander:yet-another-config-lib:%s".formatted(yacl));
		}

		properties.forEach((key, value) -> {
			dependencies.add("modImplementation", "maven.modrinth:%s:%s".formatted(key, value));
		});
	}

	private static void addRepositories(Project project) {
		addRepository(project, "Quilt", "https://maven.quiltmc.org/repository/release/");
		addRepository(project, "Sonatype", "https://oss.sonatype.org/content/repositories/snapshots/");
		addRepository(project, "Terraformers", "https://maven.terraformersmc.com/");
		addRepository(project, "YACL", "https://maven.isxander.dev/releases");
		addRepository(project, "Nucleoid", "https://maven.nucleoid.xyz/");
		addRepository(project, "Modrinth", "https://api.modrinth.com/maven", (repository) -> {
			repository.content((descriptor) -> {
				descriptor.includeGroup("maven.modrinth");
			});
		});
	}

	private static void addRepository(Project project, String name, String url) {
		addRepository(project, name, url, (repository) -> {});
	}

	private static void addRepository(Project project, String name, String url, Consumer<MavenArtifactRepository> consumer) {
		project.getRepositories().maven((repository) -> {
			repository.setName(name);
			repository.setUrl(url);
			consumer.accept(repository);
		});
	}

	public static void apply(@NotNull Project project) {
		project.getExtensions().create("mossyDependencies", MossyDependenciesExtension.class);

		addRepositories(project);
		project.getGradle().addProjectEvaluationListener(new ProjectEvaluationListener() {
			@Override
			public void beforeEvaluate(@NotNull Project project) {
			}

			@Override
			public void afterEvaluate(@NotNull Project project, @NotNull ProjectState state) {
				MossyDependenciesExtension extension = project.getExtensions().getByType(MossyDependenciesExtension.class);
				MossyDependenciesManager.addDependencies(extension, project);
				project.getGradle().removeProjectEvaluationListener(this);
			}
		});
	}
}
