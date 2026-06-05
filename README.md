# WorkShop Expressive KMP

WorkShop Expressive is a Kotlin Multiplatform design language for learning, creation, and professional tooling. It is not meant to be another Material clone. Material 3 is optimized for using software; WorkShop Expressive is optimized for learning and creating.

The name defines the product direction:

- **Work**: productivity, creation, learning, and professional tools.
- **Shop**: clarity, discoverability, browsing, and structured content.
- **Expressive**: inspired by Google Material 3 Expressive and Neural Expressive ideas, then adapted for WorkShop and Atlas products.

## Philosophy

WorkShop Expressive should feel clean, bright, and purposeful. Atlas screens should be white-first, spacious, softly rounded, and friendly without becoming childish. The system should be more expressive than traditional Material, less playful than Duolingo, and less corporate than an enterprise dashboard.

The visual language favors:

- Pure white backgrounds and very light gray surfaces.
- Indigo / blue-violet primary actions with soft-blue support colors.
- Green success, amber warning, and red error states.
- Large cards, floating panels, section containers, and soft shadows.
- Rounded shapes in the 16dp-28dp range.
- Strong hierarchy, generous breathing room, and slight asymmetry.

## Design-system architecture

The library is intentionally built foundation-first:

1. **Tokens first**
   - Colors
   - Typography
   - Shapes
   - Spacing
   - Elevation and structure tokens
2. **Foundation components**
   - `WorkshopTheme`
   - `WsButton`
   - `WsCard`
   - `WsBadge`
   - `WsTextField`
   - `WsProgressBar`
   - `WsNavigationDrawer`
3. **Learning-specific components**
   - `WsLessonCard`
   - `WsChapterCard`
   - `WsLearningPath`
   - `WsCodeBlock`
   - `WsProgressIndicator`
   - `WsAchievementCard`
4. **Application screens**
   - Login
   - Dashboard
   - Lesson detail
   - Settings

That order keeps the project sensible: foundation first, features second, screens last.

## Modules

| Module | Purpose |
| --- | --- |
| `:shared-ui` | The publishable WorkShop Expressive component library. This is the module apps should consume. |
| `:catalog` | A local, non-published component catalog that depends on `:shared-ui` by project reference. Use it to exercise components without publishing to JitPack or Maven Central. |
| `:webApp` | A WebAssembly host for the catalog, useful for fast browser checks and screenshots. |

The old Fibonacci template module has been removed. This repository now focuses on the design-system library and its local catalog.

## Testing components without publishing

Use Gradle project dependencies for local development instead of publishing snapshots to JitPack:

```kotlin
implementation(project(":shared-ui"))
```

The `:catalog` module is the recommended test surface. It compiles the same component gallery against Android, iOS simulator, JVM desktop, JS, and Wasm JS targets so regressions are caught before publishing.

### Local commands

```bash
# Compile the shared UI library metadata and JVM target
./gradlew :shared-ui:compileKotlinMetadata :shared-ui:jvmJar

# Compile the catalog across local-friendly targets
./gradlew :catalog:compileKotlinMetadata :catalog:jvmJar :catalog:compileKotlinWasmJs

# Run the catalog as a local desktop app
./gradlew :catalog:runDesktop

# Run the catalog in a browser through the Wasm host
./gradlew :webApp:wasmJsBrowserDevelopmentRun

# Compile Android variants without publishing
./gradlew :shared-ui:compileAndroidMain :catalog:compileAndroidMain

# Compile iOS simulator framework sources on macOS
./gradlew :shared-ui:compileKotlinIosSimulatorArm64 :catalog:compileKotlinIosSimulatorArm64
```

For Android and iOS visual testing, add a tiny local host app in the consuming project and point it at `project(":shared-ui")` or `project(":catalog")`. The dependency graph is local, so the app always uses your working tree and never needs a JitPack release.

## Atlas screen direction

For an Atlas dashboard, WorkShop Expressive should reserve the left side for navigation, courses, chapters, and settings; the center for continue-learning cards, recommended lessons, and progress overview; and the right side on desktop/tablet for learning statistics, recent activity, and achievements.

For lesson detail screens, the left side should hold the lesson content, Markdown, and code examples. The right side should hold metadata, progress, chapter navigation, and the next-lesson action.

The goal is focus and momentum: Atlas should make learners feel like they are moving forward through a learning path, not simply operating a productivity app.

## Compose icon collections

The `shared-ui` module exposes the open source [Remix Icon Compose collection](https://github.com/walter-juan/compose-icon-collections) as an `api` dependency, so apps that depend on this KMP UI library can import and use Remix icons directly without adding the icon coordinate again.

Available icon pack:

| Icon pack | Artifact |
| --- | --- |
| Remix Icon | `com.woowla.compose.icon.collections:remix` |

Example usage from a consuming Compose Multiplatform app:

```kotlin
Icon(imageVector = Remix.System.HomeLine, contentDescription = null)
Icon(imageVector = Remix.Health.MedicineBottleFill, contentDescription = null)
```
