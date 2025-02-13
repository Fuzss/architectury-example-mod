package net.examplemod;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static final String MOD_NAME = "Example Mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    // Registering a new creative tab
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID,
            Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> EXAMPLE_TAB = TABS.register("example_tab",
            () -> CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".example_tab"),
                    () -> new ItemStack(ExampleMod.EXAMPLE_ITEM.get())));

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "example_item"))).arch$tab(ExampleMod.EXAMPLE_TAB)));

    public static void init() {
        TABS.register();
        ITEMS.register();

        // Demonstrate @ExpectPlatform
        LOGGER.info(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
        // Demonstrate common Access Widener
        LOGGER.info(TitleScreen.COPYRIGHT_TEXT.getString());
    }
}
