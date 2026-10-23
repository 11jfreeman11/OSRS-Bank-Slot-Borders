package com.bankslotborders;
import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
@PluginDescriptor(name="Bank Slot Borders", description="Borders around visible bank item slots", tags={"bank","grid","borders"})
public class BankSlotBordersPlugin extends Plugin {
 @Inject private OverlayManager overlayManager;
 @Inject private BankSlotBordersOverlay overlay;
 @Inject private ConfigManager configManager;
 @Inject private BankSlotBordersConfig config;
 @Override protected void startUp() { overlayManager.add(overlay); }
 @Override protected void shutDown() { overlayManager.remove(overlay); }
 @Provides BankSlotBordersConfig provideConfig(ConfigManager manager) { return manager.getConfig(BankSlotBordersConfig.class); }
 @Subscribe public void onConfigChanged(ConfigChanged event) {
  if(PresetSelection.shouldUseCustom(event.getGroup(),event.getKey(),config.preset())) {
   configManager.setConfiguration("bankslotborders","preset",GridPreset.CUSTOM);
  }
 }
}
