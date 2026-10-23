package com.bankslotborders;
import java.awt.*;
import java.awt.geom.Area;
import java.util.List;
import java.util.TreeSet;
final class BankGridPainter {
 private BankGridPainter() {}
 // Normal bank spacing is the fallback for a tab with only one item.
 static int pitch(TreeSet<Integer> positions,int fallback,int minimum) {
  Integer previous=null;
  int smallest=Integer.MAX_VALUE;
  for(int position:positions) {
   if(previous!=null && position-previous>=minimum) smallest=Math.min(smallest,position-previous);
   previous=position;
  }
  // A sparse layout may skip entire rows/columns; do not mistake that for
  // a larger cell. Small differences from real neighbors are authoritative.
  return smallest==Integer.MAX_VALUE || smallest>fallback*3/2 ? fallback : smallest;
 }
 static void paint(Graphics2D g,Rectangle viewport,List<Rectangle> items,Color color,int thickness,GridStyle style) {
  if(items.isEmpty() || color==null || color.getAlpha()==0) return;
  TreeSet<Integer> xs=new TreeSet<>(),ys=new TreeSet<>();
  for(Rectangle item:items) { xs.add(item.x); ys.add(item.y); }
  Rectangle sample=items.get(0);
  int dx=pitch(xs,48,sample.width),dy=pitch(ys,36,sample.height);
  int originX=xs.first()-Math.max(0,dx-sample.width)/2;
  int firstX=originX+Math.floorDiv(viewport.x-originX,dx)*dx;
  TreeSet<Integer> rowTops=rowTops(ys,dy,sample.height,viewport);
  int t=Math.max(1,Math.min(4,thickness));
  g.setColor(color);
  if(style==GridStyle.SHADED_SQUARES) {
   for(int y:rowTops)
    for(int x=firstX;x<viewport.x+viewport.width;x+=dx)
     g.fillRect(x+2,y+2,Math.max(1,dx-4),Math.max(1,dy-4));
  } else {
   // A union keeps intersections from receiving the alpha twice.
   Area lines=new Area();
   for(int x=firstX;x<viewport.x+viewport.width;x+=dx)
    lines.add(new Area(new Rectangle(x,viewport.y,t,viewport.height)));
   for(int y:rowTops) {
    lines.add(new Area(new Rectangle(viewport.x,y,viewport.width,t)));
    lines.add(new Area(new Rectangle(viewport.x,y+dy,viewport.width,t)));
   }
   g.fill(lines);
  }
 }
 // Bank tab headings introduce gaps that are not multiples of the slot pitch.
 // Anchor every occupied row independently, then fill whole empty rows between
 // anchors. Keep the heading remainder blank rather than shifting later rows.
 static TreeSet<Integer> rowTops(TreeSet<Integer> positions,int pitch,int itemHeight,Rectangle viewport) {
  TreeSet<Integer> tops=new TreeSet<>();
  int padding=Math.max(0,pitch-itemHeight)/2;
  Integer previous=null;
  for(int position:positions) {
   int top=position-padding;
   if(previous!=null) {
    for(int empty=previous+pitch;empty+pitch<=top;empty+=pitch) tops.add(empty);
   }
   tops.add(top);
   previous=top;
  }
  int first=tops.first(),last=tops.last();
  for(int y=first-pitch;y+pitch>viewport.y;y-=pitch) tops.add(y);
  for(int y=last+pitch;y<viewport.y+viewport.height;y+=pitch) tops.add(y);
  return tops;
 }
}
