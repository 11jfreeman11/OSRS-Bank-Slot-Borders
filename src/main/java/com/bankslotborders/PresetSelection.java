package com.bankslotborders;

final class PresetSelection
{
 private PresetSelection() { }

 static boolean shouldUseCustom(String group, String key, GridPreset preset)
 {
  if (!"bankslotborders".equals(group) || preset == GridPreset.CUSTOM || key == null) return false;
  switch (key)
  {
   case "style":
   case "thickness":
   case "color":
   case "transparency":
   case "dragOnly":
   case "separateShading":
   case "shadingColor":
   case "shadingTransparency":
    return true;
   default:
    return false;
  }
 }
}
