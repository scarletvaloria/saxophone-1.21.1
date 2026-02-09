package net.scarletontv.saxophone;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SaxophoneDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		// FabricDataGeneration.Pack pack = fabricDataGenerator.createPack();

		// pack.addProvider(ExampleProvider::new);
	}
}

// instead of making each model by hand, you can use DataGeneration to generate misc models instead, such as gui item models. 
