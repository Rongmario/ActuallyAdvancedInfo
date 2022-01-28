package mod.caedis.aainfo.registry;

import mod.caedis.aainfo.ActuallyAdvancedInfo;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ActuallyAdvancedInfo.MODID, name = ActuallyAdvancedInfo.NAME, category = ActuallyAdvancedInfo.MODID)
public class ModConfig
{
    @Config.Comment("Configure what is in the tooltip")
    public static Tooltip tooltip = new Tooltip();



    public static class Tooltip{

        @Config.Comment("Is the control key needed to show the advanced data?")
        public boolean requireCTRL = true;

        @Config.Comment("Number of characters for NBT data")
        @Config.RangeInt(min = 1, max = 500)
        public int charLimit_NBT = 40;
    }






    @Mod.EventBusSubscriber
    public static class ConfigHolder {
        @SubscribeEvent
        @SuppressWarnings("unused")
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ActuallyAdvancedInfo.MODID)) {
                ConfigManager.sync(ActuallyAdvancedInfo.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
