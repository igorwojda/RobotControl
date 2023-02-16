# Martian Robots

## The Problem

The surface of Mars can be modelled by a rectangular grid around which robots are able to
move according to instructions provided from Earth. You are to write a program that
determines each sequence of robot positions and reports the final position of the robot.

A robot position consists of a grid coordinate (a pair of integers: x-coordinate followed by
y-coordinate) and an orientation (N, S, E, W for north, south, east, and west).
A robot instructions:

- Left (`L`) - the robot turns left 90 degrees and remains on the current grid point.
- Right (`R`) - the robot turns right 90 degrees and remains on the current grid point.
- Forward (`F`) - the robot moves forward one grid point in the Orientation of the current
  orientation and maintains the same orientation.

The Orientation North corresponds to the Orientation from grid point (x, y) to grid point (x, y+1).

There is also a possibility that additional command types may be required in the future and
provision should be made for this.

Since the grid is rectangular and bounded (...yes Mars is a strange planet), a robot that
moves “off” an edge of the grid is lost forever. However, lost robots leave a robot “scent” that
prohibits future robots from dropping off the world at the same grid point. The scent is left at
the last grid position the robot occupied before disappearing over the edge. An instruction to
move “off” the world from a grid point from which a robot has been previously lost is simply
ignored by the current robot.

## The Input

The first line of input is the upper-right coordinates of the rectangular world, the lower-left
coordinates are assumed to be 0, 0.

The remaining input consists of a sequence of robot positions and instructions (two lines per
robot). A position consists of two integers specifying the initial coordinates of the robot and
an orientation (N, S, E, W), all separated by whitespace on one line. A robot instruction is a
string of the letters “L”, “R”, and “F” on one line.

Each robot is processed sequentially, i.e., finishes executing the robot instructions before the
next robot begins execution.

The maximum value for any coordinate is 50.

## The Output

For each robot position/instruction in the input, the output should indicate the final grid
position and orientation of the robot. If a robot falls off the edge of the grid the word “LOST”
should be printed after the position and orientation.

## Sample data

### Run 1
```
Input:
5 3
1 1 E
RFRFRFRF

Output:
1 1 E
```

### Run 2

```
Input:
5 3
3 2 N
FRRFLLFFRRFLL

Output: 
3 3 N LOST
```

The position where robot was lost in saved, so in the following run this instruction and robot will be saved.

### Run 3

```
Input:
5 3
0 3 W
LLFFFLFLFL

Output:
2 3 S
```

## Possible extensions of this task

- Draw debug map in the console. Represent Robot as unicode arrow character (`←` `↑` `→` `↓`)
- Add support for additional commands
  - Backward (`F`) - the robot moves backward one grid point in the Orientation opposite of the current
    orientation and maintains the same orientation.
  - Turn (`T`) - the robot turns around (turns 180 degrees) and remains on the current grid point.
  - Slide right (`>`) - the robot moves one grid point to the right of the current orientation.
  - Slide left (`<`) - the robot moves one grid point to the left of the current orientation.

## How to open this project

1. Run [IntelliJ Idea](https://www.jetbrains.com/idea/)
2. Open File -> New -> Project From Version Control...
3. Paste Git repository URL and click Open
