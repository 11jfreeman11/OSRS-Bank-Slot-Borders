# Bank Slot Borders / Bank Grid

## Optional extras

Existing appearance is preserved by default: **Style preset = Custom**, and all
new highlights, guides, separate shading and section-gap clipping are off.
Existing config keys and saved values are retained.

* **Highlight hovered square:** soft color on the square under the cursor,
  including empty squares. It is a visual guide, not a drop/swap prediction.
* **Highlight drag source:** marks the original square during a bank item drag;
  it clears on release. The highlight is hidden if its source is scrolled away.
* **Hovered row guide / Hovered column guide:** optional guides that skip section
  gaps and stay within the bank viewport.
* **Separate shading appearance:** optional independent RGB and 1–10 transparency
  settings for Shaded squares in Custom mode. The original color/transparency
  settings still control lines and item borders.
* **Keep section gaps clear:** additionally clips vertical lines out of section
  gaps. Off preserves the grid you already tested. Row alignment is unchanged.
* **Style preset:** Custom uses your saved settings; Subtle uses faint single-pixel
  lines; High contrast uses brighter two-pixel lines; Drag only uses soft shaded
  squares during a bank drag. Presets do not modify saved custom values. Select
  Custom to return to them. The existing Only while dragging toggle is honored
  for every preset. Highlights/guides remain explicit opt-ins with any preset.

Hover, source and guide colors include their own opacity sliders. Main grid
transparency does not change these independent highlight colors.

After restarting, first check your original appearance with Custom selected.
Then enable extras one at a time. Check hover in empty cells, drag-source tracking,
release/cancel, scrolling, tab gaps, searches, placeholders, bank resizing, stretched
mode and your Bank Tags layouts. For Jagex login use the Jagex auth sidebar panel.
Community testers should report the selected style, enabled extras and layout mode
with screenshots of any misalignment. Compatibility in those modes still needs
human testing; no game interactions are automated.

Grid style now defaults to **Continuous lines**: straight shared cell boundaries
across the visible bank item area, including unoccupied squares. **Shaded squares**
provides the inventory-grid appearance, with gaps between filled cells.
**Item borders** retains the original appearance.

Enable **Only while dragging** to display the selected style only during an active
bank-item drag. It disappears on release. A click or a drag from another interface
does not activate it. All styles retain RGB color and **Transparency 1–10**:
1 opaque, 10 invisible, default 5. Line thickness affects lines and item borders.

The regular bank slot spacing is inferred from item widget positions, with normal
48 by 36 canvas pixel spacing as the sparse-tab fallback. Empty areas are filled
using that spacing; a completely empty bank has no item anchor and draws no grid.
Custom layouts or tab-heading gaps may need further alignment adjustments after
manual testing. This does not implement inventory swap previews or destination
highlights; it provides the visual square grid and optional drag-only visibility.

## Run

Restart the development client after updating. From this folder:

```powershell
.\gradlew.bat run
```

On this workspace, `Start-Plugin.ps1` also selects the downloaded portable Java 11
SDK. For Jagex account login, use the development client's Jagex auth panel:
https://github.com/runelite/runelite/wiki/Using-Jagex-Accounts

## Verify in game

Try Continuous lines and Shaded squares; check empty cells, scroll clipping,
searches, tabs, resized banks and stretched mode. Toggle Only while dragging;
check click without dragging, drag start, release, cancel, and closing the bank.
Check transparency 1, 5 and 10. Please report alignment issues with a screenshot
and the bank tab/layout mode. No game input is automated by this plugin.

## Build / Hub

Use JDK 11 and `./gradlew clean test build` (`gradlew.bat` on Windows).
The template wrapper and standard Hub metadata are included. Publish this source
in a public repository, set your author attribution in runelite-plugin.properties,
and submit a repository URL and tested commit SHA in plugins/bank-slot-borders
in a PR to https://github.com/runelite/plugin-hub. Review and approval are required.
Do not install the standalone JAR by copying it into a stock client; use the
development launcher for local testing.

Alignment update: occupied rows anchor independently across tab-heading gaps; separator space stays blank instead of shifting later rows. Normal neighboring widget spacing takes precedence over fallback guesses.
