package com.bankslotborders;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

final class BankGridRenderer
{
 private BankGridRenderer() { }
 static void paint(Graphics2D graphics, Rectangle viewport, List<Rectangle> items,
  BankSlotBordersConfig config, GridAppearance appearance, Point mouse, Point source)
 {
  if (items.isEmpty()) return;
  Graphics2D g = (Graphics2D) graphics.create();
  try
  {
   g.clip(viewport);
   boolean extras = config.hoverHighlight() || config.sourceHighlight() || config.rowGuide() || config.columnGuide();
   BankGridLayout layout = extras || config.respectSectionGaps() ? new BankGridLayout(viewport,items) : null;
   if (config.respectSectionGaps()) g.clip(layout.rowArea());
   // Extras go beneath the grid so original boundaries remain readable.
   if (extras) BankGridHighlights.paint(g,layout,config,mouse,source);
   if (appearance.style == GridStyle.ITEM_BORDERS)
   {
    for (Rectangle item : items) SlotBorderPainter.paint(g,item,appearance.color,appearance.thickness);
   }
   else BankGridPainter.paint(g,viewport,items,appearance.color,appearance.thickness,appearance.style);
  }
  finally { g.dispose(); }
 }
}
