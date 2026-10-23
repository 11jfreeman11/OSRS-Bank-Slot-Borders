# Grid update validation — October 23, 2026

## Preset control correction

Editing an appearance control while a preset is active now switches to Custom
via RuneLite's ConfigChanged event. All production sources and the launcher
compile with Java 11 / cached RuneLite 1.13.3 dependencies. All 14 JUnit tests pass,
including the prior pixel-preservation checks and three preset-control tests.
BankGridPainter and the default Custom rendering behavior are unchanged.
The fix is local and included in the source ZIP/JAR; the hosted launcher remains
pinned to the earlier GitHub source revision until that revision is updated.

## Optional features update

All production sources and the development launcher compile with Java 11 and
cached RuneLite 1.13.3 dependencies. All 11 JUnit tests pass. Added six tests
covering pixel-for-pixel preservation of all original styles with extras off,
non-destructive presets, opt-in separate shading, section-gap and viewport
hit-testing, source highlight removal on release, and optional gap clipping.
The original BankGridPainter remains unchanged by this update.

New features default off; the preset defaults to Custom, preserving existing
configuration. A source ZIP of the prior version is supplied separately as
bank-slot-borders-before-extras.zip. In-game regression and integration checks
with other bank plugins remain manual; see README.md.

All production sources and the development launcher compiled with Java 11 and
cached RuneLite 1.13.3 dependencies. Three JUnit tests passed: continuous lines
through empty cells, uniform alpha at line intersections and viewport clipping;
shaded cells with gaps; and transparency endpoints.

Validation used javac and JUnit directly because the sandbox previously blocked
Gradle daemon communication. The delivered JAR is rebuilt from this update.
No logged-in game testing was performed. Manual checks are in README.md.

Alignment correction: row boundaries now anchor to actual widget rows across bank section gaps. Measured neighbor spacing is no longer capped at the fallback. Java 11 compilation and all 5 JUnit tests passed, including section-gap and measured-pitch regression checks. In-game confirmation remains outstanding.
