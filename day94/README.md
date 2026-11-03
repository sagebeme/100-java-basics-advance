# Day 94 - Portfolio Project: Space Invaders

> Rows of aliens march across the screen and creep downwards, and you have one cannon. This alien-invasion shooter in the style of the 1978 arcade classic adds what Breakout didn't have: many enemies moving as one formation, and shots on a cooldown.

## 📖 The story

The kids at Otieno's cyber café in Kisumu (Day 86) have played Breakout to death. Now they want "the one with the aliens". Otieno's nephew, Brian, takes it on, reusing what he learned from Breakout: time-based movement, a game model with tests, and JavaFX drawing.

Move left and right with the arrow keys and fire with Space. The aliens (4 rows of 6) slide sideways together; when the formation touches an edge, it reverses and drops 20 pixels. Each hit is 10 points. Clear the wave and a faster one arrives; let the aliens reach your row and the game is over.

## 🎯 Learning Objectives
- Move a whole formation as one: check the edges first, then move every member
- Limit how fast the player can fire with a cooldown timer
- Manage lists of moving objects safely: add bullets, remove used ones, skip dead enemies
- Detect collisions between many bullets and many enemies
- Step the game in small time slices so nothing passes through anything
- Reuse a game structure from an earlier project, and test the new rules

## 🧰 What you'll use

**The formation.** Before moving, check whether *any* alive enemy would cross an edge. If so, the whole formation turns and descends; otherwise everyone moves sideways:

```java
boolean wouldExceedBounds = enemies.stream()
        .filter(Enemy::isAlive)
        .anyMatch(e -> e.getLeft() + step < 0 || e.getRight() + step > boardWidth);
```

Only *alive* enemies count, so when you clear the outer columns the formation travels further before turning, just like the original.

**A cooldown.** `shoot()` only works when `shootCooldownRemaining` is zero, and then sets it to 0.3 s; `update` counts it down. Holding Space fires steadily instead of one bullet per frame.

**Removing while iterating.** Changing a list inside a `for` loop over it throws `ConcurrentModificationException`. Mark things dead during the loop and clean up afterwards, or use `removeIf`:

```java
playerBullets.removeIf(b -> b.getBottom() < 0 || !b.isAlive());
```

**Small steps,** as in Day 86: a bullet at 300 px/s after a half-second stall would jump 150 px, straight over a 20 px alien. `update(dt)` splits long times into 1/120 s steps.

## 🪜 Steps

**Your brief** (the `day94/` folder is one finished version: build your own first, then compare).

1. `Player`, `Bullet` and `Enemy`, each with edges.
2. `SpaceInvadersGame`: shooting with a cooldown, bullets, the formation, collisions, score, waves and game over.
3. Tests: the starting grid, shooting and the cooldown, bullets leaving the screen, a hit, the next wave, the formation turning and descending, game over, the player kept on screen, and a slow frame.
4. `SpaceInvadersApp`: keys, the loop and drawing.
5. Make it yours: aliens that shoot back, three lives, shields that wear away, or a high score saved to a file.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day94
mvn test           # the rules, no window
mvn javafx:run     # play: arrow keys and Space
```

## 🆘 Stuck?

- **The formation breaks apart at the edge**: you checked and moved enemies one at a time. Check all of them first, then move all of them.
- **`ConcurrentModificationException`**: you removed from a list while looping over it. Use `removeIf` after the loop.
- **Holding Space fires a stream of bullets**: add the cooldown.
- **The aliens never speed up**: recompute the speed when a new wave starts.

## ✅ Checklist
- [ ] A formation that turns and descends together
- [ ] Shooting with a cooldown, and hits that score
- [ ] Waves that get faster, and game over
- [ ] The game model tested without a window
- [ ] Added return fire, lives, shields or a high score
- [ ] Committed code to Git

## ➡️ Next

Day 95 pulls three free web APIs into one dashboard: Nairobi's weather, crypto prices, and where the space station is right now.
