# Brick Breaker Java Game

NOTE: USE ARROW KEYS !!!

A classic brick breaker game written in pure Java using `javax.swing` and `java.awt`. Control the paddle, bounce the ball, and destroy all the bricks!

---

## Features

- Paddle and ball collision physics
- Score and life tracking with heart icons
- Block reset when all are destroyed
- Game resets on life loss or win
- Keyboard input via custom KeyHandler
- Resource-based images (paddle, ball, blocks, background, hearts)

---

## Gameplay

![Gameplay Demo](docs/gameplay_27_07_2025.gif)

---
## 🛠 How to Run (In IntelliJ)

### 1. Open in IntelliJ

- Open the `BrickBreaker` folder as a project.

### 2. Set Resources Directory

- Right-click `resources/` folder →  
  **Mark Directory as → Resources Root**

This allows image loading via `getResourceAsStream`.

### 3. Run the Game

- Locate `Main.java` inside `src/Game`
- Right-click → **Run 'Main.main()'**

---
