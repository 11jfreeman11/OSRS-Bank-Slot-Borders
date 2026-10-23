package com.bankslotborders;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
public class BankSlotBordersPluginTest {
 public static void main(String[] args) throws Exception {
  ExternalPluginManager.loadBuiltin(BankSlotBordersPlugin.class);
  RuneLite.main(args);
 }
}
