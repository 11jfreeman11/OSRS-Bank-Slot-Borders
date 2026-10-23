package com.bankslotborders;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
public class BankSlotBordersOverlay extends Overlay {
 private final BankSlotBordersConfig config;
 private final Client client;
 @Inject BankSlotBordersOverlay(BankSlotBordersConfig config,Client client) {
  this.config=config; this.client=client;
  setPosition(OverlayPosition.DYNAMIC);
  setLayer(OverlayLayer.MANUAL);
  drawAfterLayer(InterfaceID.Bankmain.ITEMS);
 }
 @Override public Dimension render(Graphics2D graphics) {
  if(!config.enabled()) return null;
  Widget bank=client.getWidget(InterfaceID.Bankmain.ITEMS);
  if(bank==null || bank.isHidden()) return null;
  GridAppearance appearance=new GridAppearance(config);
  Widget dragged=client.getDraggedWidget();
  boolean bankDrag=client.isDraggingWidget() && dragged!=null && dragged.getParentId()==bank.getId();
  if(appearance.dragOnly && !bankDrag) return null;
  List<Rectangle> items=new ArrayList<>();
  for(Widget child:bank.getDynamicChildren()) {
   if(child!=null && !child.isHidden() && child.getItemId()>=0) {
    Rectangle bounds=child.getBounds();
    if(bounds.width>0 && bounds.height>0) items.add(bounds);
   }
  }
  if(items.isEmpty()) return null;
  Graphics2D g=(Graphics2D)graphics.create();
  try {
   Rectangle viewport=bank.getBounds();
   g.clip(viewport);
   net.runelite.api.Point mouse=client.getMouseCanvasPosition();
   Point source=null;
   if(bankDrag && config.sourceHighlight()) {
    Rectangle original=dragged.getBounds();
    source=new Point(original.x+original.width/2,original.y+original.height/2);
   }
   BankGridRenderer.paint(g,viewport,items,config,appearance,
    new Point(mouse.getX(),mouse.getY()),source);
  } finally { g.dispose(); }
  return null;
 }
}
