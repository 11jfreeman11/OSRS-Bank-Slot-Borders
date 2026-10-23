package com.bankslotborders;

import java.awt.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class PresetSelectionTest
{
 @Test public void everyOverriddenControlSelectsCustom()
 {
  for (GridPreset preset : new GridPreset[]{GridPreset.SUBTLE,GridPreset.HIGH_CONTRAST,GridPreset.DRAG_ONLY})
   for (String key : new String[]{"style","thickness","color","transparency","dragOnly","separateShading","shadingColor","shadingTransparency"})
    assertTrue(preset + " / " + key,PresetSelection.shouldUseCustom("bankslotborders",key,preset));
 }

 @Test public void presetEventsAndIndependentHelpersDoNotResetPresetOrLoop()
 {
  for (String key : new String[]{"preset","enabled","hoverHighlight","sourceHighlight","rowGuide","columnGuide","guideColor","respectSectionGaps"})
   assertFalse(key,PresetSelection.shouldUseCustom("bankslotborders",key,GridPreset.DRAG_ONLY));
  assertFalse(PresetSelection.shouldUseCustom("other-plugin","style",GridPreset.DRAG_ONLY));
  assertFalse(PresetSelection.shouldUseCustom("bankslotborders","style",GridPreset.CUSTOM));
  assertFalse(PresetSelection.shouldUseCustom("bankslotborders",null,GridPreset.DRAG_ONLY));
 }

 @Test public void screenshotCaseUsesChosenStyleAndDragCheckboxAfterEdit()
 {
  final GridPreset[] preset = {GridPreset.DRAG_ONLY};
  BankSlotBordersConfig config = new BankSlotBordersConfig() {
   @Override public GridPreset preset() { return preset[0]; }
   @Override public GridStyle style() { return GridStyle.ITEM_BORDERS; }
   @Override public boolean dragOnly() { return false; }
   @Override public int thickness() { return 4; }
   @Override public int transparency() { return 1; }
   @Override public Color color() { return Color.RED; }
  };
  assertEquals(GridStyle.SHADED_SQUARES,new GridAppearance(config).style);
  if (PresetSelection.shouldUseCustom("bankslotborders","style",config.preset())) preset[0]=GridPreset.CUSTOM;
  GridAppearance appearance = new GridAppearance(config);
  assertEquals(GridStyle.ITEM_BORDERS,appearance.style);
  assertEquals(4,appearance.thickness);
  assertEquals(Color.RED,appearance.color);
  assertFalse(appearance.dragOnly);
 }
}
