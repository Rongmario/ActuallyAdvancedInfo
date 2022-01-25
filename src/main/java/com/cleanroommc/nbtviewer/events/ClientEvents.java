package com.cleanroommc.nbtviewer.events;

import com.cleanroommc.nbtviewer.registry.ModConfig;
import com.cleanroommc.nbtviewer.utils.StackUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;


public class ClientEvents {

    private static final String ADVANCED_INFO_TEXT_PRE = TextFormatting.DARK_GRAY + "     ";
    private static final String ADVANCED_INFO_HEADER_PRE = TextFormatting.GRAY + "  -";

    public ClientEvents() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        //Advanced Item Info
        if (event.getFlags().isAdvanced() && StackUtils.isValid(stack)) {
            if (!ModConfig.tooltip.requireCTRL || GuiScreen.isCtrlKeyDown()) {
                event.getToolTip().add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + "Advanced Info:");

                //OreDict Names
                int[] oreIDs = OreDictionary.getOreIDs(stack);
                event.getToolTip().add(ADVANCED_INFO_HEADER_PRE + "OreDicts" + ":");
                if (oreIDs.length > 0) {
                    for (int oreID : oreIDs) {
                        event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + OreDictionary.getOreName(oreID));
                    }
                }else {
                    event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + "None");
                }

                //Code Name
                event.getToolTip().add(ADVANCED_INFO_HEADER_PRE + "Code Name:");
                event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + Item.REGISTRY.getNameForObject(stack.getItem()));

                //Base Item's Unlocalized Name
                String baseName = stack.getItem().getTranslationKey();
                if (baseName != null) {
                    event.getToolTip().add(ADVANCED_INFO_HEADER_PRE + "Item's Unlocalized Name:");
                    event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + baseName);
                }

                //Metadata
                int meta = stack.getItemDamage();
                int max = stack.getMaxDamage();
                event.getToolTip().add(ADVANCED_INFO_HEADER_PRE + "Metadata:");
                event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + meta + (max > 0 ? "/" + max : ""));

                //Unlocalized Name
                String metaName = stack.getItem().getTranslationKey(stack);
                if (metaName != null && baseName != null && !metaName.equals(baseName)) {
                    event.getToolTip().add(ADVANCED_INFO_HEADER_PRE + "Meta's Unlocalized Name:");
                    event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + metaName);
                }

                //NBT
                NBTTagCompound compound = stack.getTagCompound();
                if (compound != null && !compound.isEmpty()) {
                    event.getToolTip().add(ADVANCED_INFO_HEADER_PRE + "NBT:");
                    if (GuiScreen.isShiftKeyDown()) {
                        int limit = ModConfig.tooltip.charLimit_NBT;
                        String compoundStrg = compound.toString();
                        int compoundStrgLength = compoundStrg.length();

                        String compoundDisplay;
                        if (limit > 0 && compoundStrgLength > limit) {
                            compoundDisplay = compoundStrg.substring(0, limit) + TextFormatting.GRAY + " (" + (compoundStrgLength - limit) + " more characters...)";
                        } else {
                            compoundDisplay = compoundStrg;
                        }
                        event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + compoundDisplay);
                    } else {
                        event.getToolTip().add(ADVANCED_INFO_TEXT_PRE + TextFormatting.ITALIC + "[Press Shift]");
                    }
                }

            } else {
                event.getToolTip().add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + "Press CTRL for Advanced Info");
            }
        }
    }
}
