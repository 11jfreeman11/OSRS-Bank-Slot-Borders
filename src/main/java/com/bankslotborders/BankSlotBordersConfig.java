package com.bankslotborders;
import java.awt.Color;
import net.runelite.client.config.*;
@ConfigGroup("bankslotborders")
public interface BankSlotBordersConfig extends Config {
 @ConfigItem(keyName="enabled", name="Show grid", description="Show the bank grid", position=0)
 default boolean enabled() { return true; }
 @Range(min=1,max=4)
 @ConfigItem(keyName="thickness", name="Line thickness", description="Thickness in pixels", position=1)
 default int thickness() { return 1; }
 @ConfigItem(keyName="color", name="Border color", description="Border RGB color", position=2)
 default Color color() { return new Color(190,180,155,150); }
 @Range(min=1,max=10)
 @ConfigItem(keyName="transparency", name="Transparency", description="1 fully opaque; 10 invisible", position=3)
 default int transparency() { return 5; }
 @ConfigItem(keyName="style", name="Grid style", description="Continuous lines, shaded squares, or original item borders", position=4)
 default GridStyle style() { return GridStyle.CONTINUOUS_LINES; }
 @ConfigItem(keyName="dragOnly", name="Only while dragging", description="Show only during an active bank item drag", position=5)
 default boolean dragOnly() { return false; }
 @ConfigItem(keyName="preset", name="Style preset", description="Custom uses your saved settings; selecting Custom restores them without changes", position=6)
 default GridPreset preset() { return GridPreset.CUSTOM; }
 @ConfigItem(keyName="separateShading", name="Separate shading appearance", description="Use independent color and transparency for shaded squares in Custom mode", position=7)
 default boolean separateShading() { return false; }
 @ConfigItem(keyName="shadingColor", name="Shading color", description="RGB color for shaded squares when separate shading is enabled", position=8)
 default Color shadingColor() { return new Color(190,180,155); }
 @Range(min=1,max=10)
 @ConfigItem(keyName="shadingTransparency", name="Shading transparency", description="1 opaque, 10 invisible; used with Separate shading appearance", position=9)
 default int shadingTransparency() { return 8; }
 @ConfigItem(keyName="hoverHighlight", name="Highlight hovered square", description="Highlight the bank square under the mouse; does not predict a drop or swap", position=10)
 default boolean hoverHighlight() { return false; }
 @ConfigItem(keyName="sourceHighlight", name="Highlight drag source", description="Highlight the original square during a bank item drag", position=11)
 default boolean sourceHighlight() { return false; }
 @ConfigItem(keyName="rowGuide", name="Hovered row guide", description="Lightly shade the hovered row", position=12)
 default boolean rowGuide() { return false; }
 @ConfigItem(keyName="columnGuide", name="Hovered column guide", description="Lightly shade the hovered column, skipping section gaps", position=13)
 default boolean columnGuide() { return false; }
 @Alpha
 @ConfigItem(keyName="hoverColor", name="Hover highlight color", description="Color and opacity for the hovered square", position=14)
 default Color hoverColor() { return new Color(255,220,100,65); }
 @Alpha
 @ConfigItem(keyName="sourceColor", name="Source highlight color", description="Color and opacity for the original dragged square", position=15)
 default Color sourceColor() { return new Color(100,180,255,65); }
 @Alpha
 @ConfigItem(keyName="guideColor", name="Guide color", description="Color and opacity for row and column guides", position=16)
 default Color guideColor() { return new Color(255,255,255,20); }
 @ConfigItem(keyName="respectSectionGaps", name="Keep section gaps clear", description="Also stop vertical grid lines in bank section gaps; off preserves the existing grid appearance", position=17)
 default boolean respectSectionGaps() { return false; }
}
