package net.examplemod.neoforge;

import net.examplemod.ExampleMod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(ExampleMod.MOD_ID)
public class ExampleModNeoForge {
    public ExampleModNeoForge(ModContainer modContainer) {
        ExampleMod.init();
    }
}
