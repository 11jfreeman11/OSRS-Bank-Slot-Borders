package com.bankslotborders;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
final class SlotBorderPainter {
 private SlotBorderPainter() {}
 static Color withTransparency(Color color,int transparency) {
  if (color==null) return null;
  int level=Math.max(1,Math.min(10,transparency));
  return new Color(color.getRed(),color.getGreen(),color.getBlue(),Math.round(255f*(10-level)/9f));
 }
 static void paint(Graphics2D g,Rectangle b,Color color,int thickness) {
  if (b.width<=0 || b.height<=0 || color==null || color.getAlpha()==0) return;
  int t=Math.min(Math.max(1,Math.min(4,thickness)),Math.min(b.width,b.height));
  g.setColor(color);
  g.fillRect(b.x,b.y,b.width,t);
  int bottom=Math.max(t,b.height-t);
  g.fillRect(b.x,b.y+bottom,b.width,b.height-bottom);
  int middle=bottom-t;
  g.fillRect(b.x,b.y+t,t,middle);
  int right=Math.max(t,b.width-t);
  g.fillRect(b.x+right,b.y+t,b.width-right,middle);
 }
}
