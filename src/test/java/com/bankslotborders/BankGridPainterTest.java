package com.bankslotborders;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class BankGridPainterTest {
 @Test public void sectionGapDoesNotShiftLaterRows() {
  java.util.TreeSet<Integer> ys=new java.util.TreeSet<>(Arrays.asList(12,48,104,140));
  java.util.TreeSet<Integer> tops=BankGridPainter.rowTops(ys,36,32,new Rectangle(10,10,144,180));
  org.junit.Assert.assertTrue(tops.contains(102));
  org.junit.Assert.assertTrue(tops.contains(138));
  org.junit.Assert.assertFalse(tops.contains(82));
 }
 @Test public void measuredPitchIsNotClampedToOldGuess() {
  assertEquals(40,BankGridPainter.pitch(new java.util.TreeSet<>(Arrays.asList(12,52,92)),36,32));
 }
 private BufferedImage render(GridStyle style) {
  BufferedImage image=new BufferedImage(160,120,BufferedImage.TYPE_INT_ARGB);
  Graphics2D g=image.createGraphics();
  Rectangle viewport=new Rectangle(10,10,144,108);
  g.clip(viewport);
  BankGridPainter.paint(g,viewport,Arrays.asList(new Rectangle(16,12,36,32),new Rectangle(64,12,36,32),new Rectangle(16,48,36,32)),new Color(255,255,255,128),1,style);
  g.dispose(); return image;
 }
 @Test public void continuousGridCoversEmptyCellsWithoutDarkIntersections() {
  BufferedImage image=render(GridStyle.CONTINUOUS_LINES);
  assertEquals(128,image.getRGB(58,46)>>>24);
  assertEquals(128,image.getRGB(106,100)>>>24);
  assertEquals(128,image.getRGB(120,82)>>>24);
  assertEquals(0,image.getRGB(80,65));
  assertEquals(0,image.getRGB(9,46));
 }
 @Test public void shadedSquaresLeaveGapsAndFillEmptyCells() {
  BufferedImage image=render(GridStyle.SHADED_SQUARES);
  assertEquals(128,image.getRGB(120,95)>>>24);
  assertEquals(0,image.getRGB(58,70));
 }
 @Test public void transparencyEndpoints() {
  assertEquals(255,SlotBorderPainter.withTransparency(Color.WHITE,1).getAlpha());
  assertEquals(0,SlotBorderPainter.withTransparency(Color.WHITE,10).getAlpha());
 }
}
