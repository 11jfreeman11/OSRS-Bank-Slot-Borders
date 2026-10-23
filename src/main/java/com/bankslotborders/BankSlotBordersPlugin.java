package com.bankslotborders;
import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
@PluginDescriptor(name="Bank Slot Borders", description="Borders around visible bank item slots", tags={"bank","grid","borders"})
public class BankSlotBordersPlugin extends Plugin {
 @Inject private OverlayManager overlayManager;
 @Inject private BankSlotBordersOverlay overlay;
 @Override protected void startUp() { overlayManager.add(overlay); }
 @Override protected void shutDown() { overlayManager.remove(overlay); }
 @Provides BankSlotBordersConfig provideConfig(ConfigManager manager) { return manager.getConfig(BankSlotBordersConfig.class); }
}
