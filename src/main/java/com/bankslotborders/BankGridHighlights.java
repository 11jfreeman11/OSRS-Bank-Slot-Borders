package com.bankslotborders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

final class BankGridHighlights
{
 private BankGridHighlights() { }
 static void paint(Graphics2D g, BankGridLayout layout, BankSlotBordersConfig config,
  Point mouse, Point source)
 {
  Rectangle hovered = layout.cellAt(mouse);
  if (hovered != null)
  {
   if (config.rowGuide() || config.columnGuide())
   {
    Color color = config.guideColor();
    if (color != null) { g.setColor(color); g.fill(layout.guides(hovered,config.rowGuide(),config.columnGuide())); }
   }
   if (config.hoverHighlight()) fill(g,hovered,config.hoverColor());
  }
  if (config.sourceHighlight())
  {
   Rectangle original = layout.cellAt(source);
   if (original != null) fill(g,original,config.sourceColor());
  }
 }
 private static void fill(Graphics2D g, Rectangle cell, Color color)
 {
  if (color == null) return;
  g.setColor(color);
  g.fillRect(cell.x+2,cell.y+2,Math.max(1,cell.width-4),Math.max(1,cell.height-4));
 }
}
