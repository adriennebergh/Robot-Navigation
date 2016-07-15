# Robot-Navigation

Navigation Problem:
A robot represented as a point moves in a regular two-dimensional NxN grid (e.g., N = 100). Each point of the grid is either "free" or "forbidden" (obstacle). From any position (i,j) in the grid the robot can reach each of the 4 adjacent positions (i,j-1), (i,j+1), (i-1,j), (i+1,j), if it is not forbidden. A navigation problem consists of finding a path in the grid (sequence of adjacent free points) that connects a given initial position to a given goal position. Each move has a cost of 1.

Program takes as a command-line argument the name of a text file representing the map of the world the simulation will be carried out in.
