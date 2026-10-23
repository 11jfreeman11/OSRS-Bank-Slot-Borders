package com.bankslotborders;

public enum GridPreset
{
 CUSTOM("Custom (keep my settings)"), SUBTLE("Subtle"), HIGH_CONTRAST("High contrast"), DRAG_ONLY("Drag only");
 private final String label;
 GridPreset(String label) { this.label = label; }
 @Override public String toString() { return label; }
}
