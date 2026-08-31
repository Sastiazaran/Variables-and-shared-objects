# Variables and Shared Objects

A small Java demo of **threads sharing one object**. Five worker threads (`Hilo`) each increment the same `DataCenter` counter 1,000 times. Access is serialized with a round-robin spinlock so only one thread mutates the counter at a time.

Expected result: `5 × 1000 = 5000`.

## How it works

```
App
 └─ DataCenter (shared counter `valor`)
     ├─ Hilo 0  ── incrementa(0) × 1000
     ├─ Hilo 1  ── incrementa(1) × 1000
     ├─ Hilo 2  ── incrementa(2) × 1000
     ├─ Hilo 3  ── incrementa(3) × 1000
     └─ Hilo 4  ── incrementa(4) × 1000
```

1. `App` creates one `DataCenter` and five `Hilo` threads, then starts them.
2. Each thread loops 1,000 times and calls `DataCenter.incrementa(tID)`.
3. `incrementa` waits until `nTurn` equals that thread’s id, increments `valor`, then passes the turn to `(nTurn + 1) % 5`.
4. `App` joins every thread and prints the final value.

`nTurn` and `valor` are `volatile` so the busy-wait sees updates from other threads. Without that (or another happens-before edge such as `synchronized`), a thread can spin forever on a cached copy of `nTurn`.

## Requirements

- JDK 8 or later (`javac` and `java` on the `PATH`)

## Build and run

From the repository root:

```bash
javac -d out src/*.java
java -cp out App
```

Example output:

```
Start value: 0
Thread 0 incrementa.
Thread 1 incrementa.
Thread 2 incrementa.
Thread 3 incrementa.
Thread 4 incrementa.
Finish value: 5000
```

Thread finish lines can appear in any order. The finish value must be `5000`; if it is not, the program exits with status `1`.

## Project layout

| File | Role |
|------|------|
| `src/App.java` | Entry point: starts threads, waits, prints the result |
| `src/Hilo.java` | Worker thread that increments the shared counter |
| `src/DataCenter.java` | Shared object (`valor`) and round-robin lock (`nTurn`) |
