package mod.caedis.aainfo.utils;

import mod.caedis.aainfo.ActuallyAdvancedInfo;
import mod.caedis.aainfo.misc.IDisableableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.IItemHandler;

import java.util.Collection;


// originally from ActuallyAdditions
public final class StackUtils {

    /**
     * Pretty much just a check for {@link ItemStack#isEmpty()} but exists in case Mojang does some more refactoring.
     * @param stack The stack
     * @return If the stack is not empty, or if it's an IDisableableItem, if its enabled.
     */
    public static boolean isValid(ItemStack stack) {
        if (stack == null){
            ActuallyAdvancedInfo.LOGGER.warn("Why is this null??? - {}", stack);
            return false;
        }
        Item i = stack.getItem();
        if (i instanceof IDisableableItem) return !((IDisableableItem) i).isDisabled();
        return !stack.isEmpty();
    }

    /**
     * @return The empty itemstack instance.
     */
    public static ItemStack getEmpty() {
        return ItemStack.EMPTY;
    }

    /**
     * A helper method to make NonNullLists with empty fill.
     * @param size How big the list will be.
     * @return A {@link NonNullList} with the same size as provided.
     */
    public static NonNullList<ItemStack> makeList(int size) {
        return NonNullList.withSize(size, getEmpty());
    }

    /**
     * Checks if a collection of stacks are empty, as {@link Collection#isEmpty()} does not care about empty stacks.
     * @param stacks Some ItemStacks
     * @return If all stacks in the collection return true for {@link ItemStack#isEmpty()}
     */
    public static boolean isEmpty(Collection<ItemStack> stacks) {
        if (stacks.isEmpty()) return true;
        for (ItemStack s : stacks)
            if (!s.isEmpty()) return false;
        return true;
    }

    /**
     * Util method to find the first filled item in a handler.  Searches from slot 0 to the end.
     * @param inv The IItemHandler to search.
     * @return The first filled slot, or -1 if all slots are empty.
     */
    public static int findFirstFilled(IItemHandler inv) {
        for (int i = 0; i < inv.getSlots(); i++) {
            if (!inv.getStackInSlot(i).isEmpty()) return i;
        }
        return -1;
    }

    /**
     * Helper method to add stack size and return the stack.
     */
    public static ItemStack grow(ItemStack s, int i) {
        s.grow(i);
        return s;
    }

    /**
     * Helper method to remove stack size and return the stack.
     */
    public static ItemStack shrink(ItemStack s, int i) {
        s.shrink(i);
        return s;
    }

    /**
     * Helper method to remove stack size and return the stack.
     */
    public static ItemStack shrinkForContainer(ItemStack s, int i) {
        ItemStack sc = s.copy();
        s.shrink(i);
        if (s.isEmpty()) return sc.getItem().getContainerItem(sc);
        return s;
    }


}