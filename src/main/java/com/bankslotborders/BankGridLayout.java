package com.bankslotborders;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.List;
import java.util.TreeSet;

// Optional features share the established painter's geometry without changing it.
final class BankGridLayout
{
 private final Rectangle viewport;
 private final int firstX, dx, dy;
 private final TreeSet<Integer> rows;

 BankGridLayout(Rectangle viewport, List<Rectangle> items)
 {
  this.viewport = new Rectangle(viewport);
  TreeSet<Integer> xs = new TreeSet<>(), ys = new TreeSet<>();
  for (Rectangle item : items) { xs.add(item.x); ys.add(item.y); }
  Rectangle sample = items.get(0);
  dx = BankGridPainter.pitch(xs,48,sample.width);
  dy = BankGridPainter.pitch(ys,36,sample.height);
  int origin = xs.first() - Math.max(0,dx-sample.width)/2;
  firstX = origin + Math.floorDiv(viewport.x-origin,dx)*dx;
  rows = BankGridPainter.rowTops(ys,dy,sample.height,viewport);
 }

 Rectangle cellAt(Point point)
 {
  if (point == null || !viewport.contains(point)) return null;
  Integer top = rows.floor(point.y);
  if (top == null || point.y >= top + dy) return null;
  int left = firstX + Math.floorDiv(point.x-firstX,dx)*dx;
  return new Rectangle(left,top,dx,dy);
 }

 Area rowArea()
 {
  Area result = new Area();
  for (int y : rows) result.add(new Area(new Rectangle(viewport.x,y,viewport.width,dy)));
  result.intersect(new Area(viewport));
  return result;
 }

 Area guides(Rectangle cell, boolean row, boolean column)
 {
  Area result = new Area();
  if (row) result.add(new Area(new Rectangle(viewport.x,cell.y,viewport.width,cell.height)));
  if (column) result.add(new Area(new Rectangle(cell.x,viewport.y,cell.width,viewport.height)));
  result.intersect(rowArea());
  return result;
 }
}
