# Material theme shapes

Reusable Workshop components should use `MaterialTheme.shapes.*` for their default shapes.

## Rule

- Define the Workshop shape scale once in `WsShapes`.
- Pass `WsShapes` into `MaterialTheme` from `WorkshopTheme`.
- Make reusable components consume semantic Material shapes, such as `MaterialTheme.shapes.medium` or `MaterialTheme.shapes.large`.
- Use `RoundedCornerShape(...)` only when defining the theme shape scale or when a caller intentionally overrides one component.

## Preferred component pattern

```kotlin
shape = MaterialTheme.shapes.medium
```

Avoid hard-coding radius tokens in reusable components:

```kotlin
shape = RoundedCornerShape(WorkshopThemeTokens.radius.lg)
```

This keeps components theme-driven. If the shape scale changes later, components update automatically through `WorkshopTheme` instead of each component needing its own radius edit.

## Optional shape overrides

Components that expose a `shape` parameter should default to the theme shape and allow intentional local overrides:

```kotlin
@Composable
fun WsCard(
    shape: Shape? = null,
) {
    val resolvedShape = shape ?: MaterialTheme.shapes.medium
}
```

Callers can then use the default:

```kotlin
WsCard { ... }
```

or choose an explicit override:

```kotlin
WsCard(shape = MaterialTheme.shapes.extraLarge) { ... }
WsCard(shape = RoundedCornerShape(24.dp)) { ... }
```

## Theme definition

Keep numeric corner values centralized in the theme:

```kotlin
val WsShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp),
    extraLarge = RoundedCornerShape(16.dp),
)
```

## Repository recommendation

For Workshop Expressive, components should consume `MaterialTheme.shapes.*`; the theme should define what those shapes mean.
