# Dancer Gesture Analyzer

A SuperCollider project that analyzes movement data from wearable sensors worn by dancers. The system evaluates movement gestures in terms of human expression, character, and personality traits.

## Features

- Parse timestamped sensor data files
- Analyze movement qualities (weight, time, space, flow)
- Interpret expressive characteristics based on Laban Movement Analysis principles
- Real-time visualization of movement qualities
- Map movement to sonic feedback

## Usage

1. Place sensor data files in the `data` folder
2. Open `main.scd` in SuperCollider
3. Run the script to analyze and interpret the movement data
4. View results in the SuperCollider post window or through the visualization interface

## Data Format

The system expects data files with timestamped entries containing arrays of numeric sensor values. Example format:

```
1648234567.123, [0.2, 0.8, 0.1, 0.4, 0.9, 0.3]
1648234567.223, [0.3, 0.7, 0.2, 0.5, 0.8, 0.2]
```

Where the timestamp is in seconds, and the array contains sensor readings (acceleration, rotation, etc.).

## Requirements

- SuperCollider 3.10+
- SC3 Plugins (optional, for advanced sound processing)
