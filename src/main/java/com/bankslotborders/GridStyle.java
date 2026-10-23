package com.bankslotborders;
public enum GridStyle {
 CONTINUOUS_LINES("Continuous lines"), SHADED_SQUARES("Shaded squares"), ITEM_BORDERS("Item borders");
 private final String label;
 GridStyle(String label) { this.label=label; }
 @Override public String toString() { return label; }
}
