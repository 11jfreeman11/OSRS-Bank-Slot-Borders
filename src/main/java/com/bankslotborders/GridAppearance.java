package com.bankslotborders;

import java.awt.Color;

// Presets are temporary rendering choices, never writes to saved configuration.
final class GridAppearance
{
 final GridStyle style;
 final Color color;
 final int thickness;
 final boolean dragOnly;

 GridAppearance(BankSlotBordersConfig config)
 {
  GridPreset preset = config.preset();
  style = preset == GridPreset.CUSTOM ? config.style() :
   preset == GridPreset.DRAG_ONLY ? GridStyle.SHADED_SQUARES : GridStyle.CONTINUOUS_LINES;
  thickness = preset == GridPreset.HIGH_CONTRAST ? 2 : preset == GridPreset.CUSTOM ? config.thickness() : 1;
  dragOnly = config.dragOnly() || preset == GridPreset.DRAG_ONLY;
  if (preset == GridPreset.CUSTOM)
  {
   boolean shading = style == GridStyle.SHADED_SQUARES && config.separateShading();
   color = SlotBorderPainter.withTransparency(shading ? config.shadingColor() : config.color(),
    shading ? config.shadingTransparency() : config.transparency());
  }
  else if (preset == GridPreset.HIGH_CONTRAST) color = new Color(240,235,210,220);
  else if (preset == GridPreset.DRAG_ONLY) color = new Color(190,180,155,65);
  else color = new Color(190,180,155,55);
 }
}
