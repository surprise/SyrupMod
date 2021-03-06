package com.emhwebserver.syrup.ui;

import com.emhwebserver.syrup.event.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class UIRenderer {
  private Minecraft mc = Minecraft.getInstance();
  
  public void draw() {
    drawString("[FPS] " + Minecraft.getDebugFPS(), 2, 1, 0xffffff);
    drawString("[XYZ] " + Math.round(mc.player.posX) + "/" + Math.round(mc.player.posY) + "/" + Math.round(mc.player.posZ), 2, 15, 0xffffff);
    GlStateManager.scaled(2.0, 2.0, 2.0);
    drawString("W", 11, 16, mc.gameSettings.keyBindForward.isKeyDown() ? 0x00ff00 : 0xff0000);
    drawString("S", 11, 27, mc.gameSettings.keyBindBack.isKeyDown() ? 0x00ff00 : 0xff0000);
    drawString("A", 1, 27, mc.gameSettings.keyBindLeft.isKeyDown() ? 0x00ff00 : 0xff0000);
    drawString("D", 21, 27, mc.gameSettings.keyBindRight.isKeyDown() ? 0x00ff00 : 0xff0000);
    GlStateManager.scaled(0.5, 0.5, 0.5);
    drawString("Sprint", 2, 75, mc.player.isSprinting() ? 0x00ff00 : 0xff0000);
    drawString("Swinging", 2, 90, mc.player.isSwingInProgress ? 0x00ff00 : 0xff0000);
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Helmet Durability: " + getDurability(mc.player.inventory.armorInventory.get(3))+ "/" + mc.player.inventory.armorInventory.get(3).getMaxDamage() : "", 2, 105, getColorByCurrentAndMax(getDurability(mc.player.inventory.armorInventory.get(3)), mc.player.inventory.armorInventory.get(3).getMaxDamage()));
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Chestplate Durability: " + getDurability(mc.player.inventory.armorInventory.get(2)) + "/" + mc.player.inventory.armorInventory.get(2).getMaxDamage() : "", 2, 120, getColorByCurrentAndMax(getDurability(mc.player.inventory.armorInventory.get(2)), mc.player.inventory.armorInventory.get(2).getMaxDamage()));
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Leggings Durability: " + getDurability(mc.player.inventory.armorInventory.get(1)) + "/" + mc.player.inventory.armorInventory.get(1).getMaxDamage() : "", 2, 135, getColorByCurrentAndMax(getDurability(mc.player.inventory.armorInventory.get(1)), mc.player.inventory.armorInventory.get(1).getMaxDamage()));
    drawString(Event.moduleManager.armorStatus.isEnabled() ? "Boots Durability: " + getDurability(mc.player.inventory.armorInventory.get(0)) + "/" + mc.player.inventory.armorInventory.get(0).getMaxDamage() : "", 2, 150, getColorByCurrentAndMax(getDurability(mc.player.inventory.armorInventory.get(0)), mc.player.inventory.armorInventory.get(0).getMaxDamage()));
  }
  
  private int getColorByCurrentAndMax(int current, int max) {
    double currentD = (double)current;
    double maxD = (double)max;
    if (currentD / maxD >= 0.9)
      return 32768;
    if (currentD / maxD <= 0.9 && currentD / maxD >= 0.8)
      return 1017600;
    if (currentD / maxD <= 0.8 && currentD / maxD >= 0.7)
      return 6336256;
    if (currentD / maxD <= 0.7 && currentD / maxD >= 0.6)
      return 11195392;
    if (currentD / maxD <= 0.6 && currentD / maxD >= 0.5)
      return 14150400;
    if (currentD / maxD <= 0.5 && currentD / maxD >= 0.4)
      return 16580096;
    if (currentD / maxD <= 0.4 && currentD / maxD >= 0.3)
      return 16764160;
    if (currentD / maxD <= 0.3 && currentD / maxD >= 0.2)
      return 16756224;
    if (currentD / maxD <= 0.2 && currentD / maxD >= 0.1)
      return 16734720;
    if (currentD / maxD <= 0.1) {
      return 16711680;
    }
    return 0xffffff;
  }
  
  private void drawString(String text, float x, float y, int color) {
    mc.fontRenderer.drawStringWithShadow(text, x, y, color);
  }

  private int getDurability(ItemStack itemStack) {
    return itemStack.getMaxDamage() - itemStack.getDamage();
  }

}
