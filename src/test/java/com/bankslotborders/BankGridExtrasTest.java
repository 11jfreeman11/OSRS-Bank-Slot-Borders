package com.bankslotborders;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankGridExtrasTest
{
 private final Rectangle viewport = new Rectangle(10,10,144,180);
 private final List<Rectangle> items = Arrays.asList(new Rectangle(16,12,36,32),
  new Rectangle(64,12,36,32),new Rectangle(16,48,36,32),new Rectangle(16,104,36,32));

 @Test public void extrasOffProducesIdenticalPixelsForEveryOriginalStyle()
 {
  for (GridStyle style : GridStyle.values())
  {
   BankSlotBordersConfig config = new BankSlotBordersConfig() {
    @Override public GridStyle style() { return style; }
    @Override public int thickness() { return 3; }
    @Override public int transparency() { return 7; }
   };
   BufferedImage before = new BufferedImage(180,200,BufferedImage.TYPE_INT_ARGB);
   BufferedImage after = new BufferedImage(180,200,BufferedImage.TYPE_INT_ARGB);
   Graphics2D old = before.createGraphics();
   old.clip(viewport);
   Color color = SlotBorderPainter.withTransparency(config.color(),config.transparency());
   if (style == GridStyle.ITEM_BORDERS)
    for (Rectangle item : items) SlotBorderPainter.paint(old,item,color,config.thickness());
   else BankGridPainter.paint(old,viewport,items,color,config.thickness(),style);
   old.dispose();
   Graphics2D updated = after.createGraphics();
   BankGridRenderer.paint(updated,viewport,items,config,new GridAppearance(config),new Point(30,25),new Point(78,25));
   updated.dispose();
   assertArrayEquals(style.toString(),before.getRGB(0,0,180,200,null,0,180),after.getRGB(0,0,180,200,null,0,180));
  }
 }

 @Test public void presetsDoNotOverwriteCustomChoices()
 {
  BankSlotBordersConfig config = new BankSlotBordersConfig() {
   @Override public GridPreset preset() { return GridPreset.HIGH_CONTRAST; }
   @Override public GridStyle style() { return GridStyle.ITEM_BORDERS; }
   @Override public int thickness() { return 4; }
  };
  GridAppearance preset = new GridAppearance(config);
  assertEquals(GridStyle.CONTINUOUS_LINES,preset.style);
  assertEquals(2,preset.thickness);
  assertEquals(GridStyle.ITEM_BORDERS,config.style());
  assertEquals(4,config.thickness());
  assertTrue(new GridAppearance(new BankSlotBordersConfig() {
   @Override public GridPreset preset() { return GridPreset.DRAG_ONLY; }
  }).dragOnly);
 }

 @Test public void shadingOverridesOnlyShadedStyleWhenOptedIn()
 {
  BankSlotBordersConfig config = new BankSlotBordersConfig() {
   @Override public boolean separateShading() { return true; }
   @Override public GridStyle style() { return GridStyle.SHADED_SQUARES; }
   @Override public Color shadingColor() { return Color.RED; }
   @Override public int shadingTransparency() { return 1; }
  };
  assertEquals(Color.RED,new GridAppearance(config).color);
  assertEquals(SlotBorderPainter.withTransparency(new BankSlotBordersConfig() {}.color(),5),
   new GridAppearance(new BankSlotBordersConfig() {
    @Override public boolean separateShading() { return true; }
    @Override public Color shadingColor() { return Color.RED; }
   }).color);
 }

 @Test public void hoverAndGuidesSkipSectionGapsAndOutsideViewport()
 {
  BankGridLayout layout = new BankGridLayout(viewport,items);
  assertNull(layout.cellAt(new Point(30,90)));
  assertNull(layout.cellAt(new Point(9,25)));
  Rectangle cell = layout.cellAt(new Point(30,25));
  assertNotNull(cell);
  assertTrue(layout.guides(cell,true,true).contains(30,25));
  assertFalse(layout.guides(cell,true,true).contains(30,90));
  assertFalse(layout.guides(cell,true,true).contains(9,25));
 }

 @Test public void sourceAndHoverCanHighlightDifferentCellsAndNoStaleSource()
 {
  BankSlotBordersConfig config = new BankSlotBordersConfig() {
   @Override public boolean hoverHighlight() { return true; }
   @Override public boolean sourceHighlight() { return true; }
   @Override public Color hoverColor() { return Color.YELLOW; }
   @Override public Color sourceColor() { return Color.BLUE; }
  };
  BankGridLayout layout = new BankGridLayout(viewport,items);
  BufferedImage image = new BufferedImage(180,200,BufferedImage.TYPE_INT_ARGB);
  Graphics2D g = image.createGraphics();
  BankGridHighlights.paint(g,layout,config,new Point(30,25),new Point(78,25));
  g.dispose();
  assertEquals(Color.YELLOW.getRGB(),image.getRGB(30,25));
  assertEquals(Color.BLUE.getRGB(),image.getRGB(78,25));
  BufferedImage released = new BufferedImage(180,200,BufferedImage.TYPE_INT_ARGB);
  g = released.createGraphics();
  BankGridHighlights.paint(g,layout,config,new Point(30,25),null);
  g.dispose();
  assertEquals(0,released.getRGB(78,25));
 }

 @Test public void optionalGapClippingLeavesOriginalGridOtherwiseUntouched()
 {
  BankSlotBordersConfig config = new BankSlotBordersConfig() {
   @Override public boolean respectSectionGaps() { return true; }
  };
  BufferedImage image = new BufferedImage(180,200,BufferedImage.TYPE_INT_ARGB);
  Graphics2D g = image.createGraphics();
  BankGridRenderer.paint(g,viewport,items,config,new GridAppearance(config),null,null);
  g.dispose();
  assertEquals(0,image.getRGB(58,90));
  assertTrue((image.getRGB(58,25) >>> 24)>0);
 }
}
