Graphics Editor Software(GES)

commands for compiling and running the software

g++ filename.c -lgraph

./.aout

commands for drawing are to be stored in a Instructions.txt file

<command> <object> <parameters>

1. draw rectangle x1 y1 x2 y2 

(where parameters x1 y1 are the co-ordinates of upper left corner
and x2 y2 are co-ordinates of lower right corner)

2. draw polygon n x1 y1 ... xn yn

(where xi yi are co-ordinates of each vertex of the polygon)

3. draw line x1 y1 x2 y2

(line function is used to draw a line from a point(x1,y1) to point(x2,y2) i.e. (x1,y1) and (x2,y2) are end points of the line.)

4. draw circle x y r

(where x y are the co-ordinates of the circle's centre and r is the radius)

5. draw ellipse x y stangle endangle xradius yradius

(Ellipse is used to draw an ellipse (x,y) are coordinates of center of the ellipse, stangle is the starting angle, end angle is the ending angle, and fifth and sixth parameters specifies the X and Y radius of the ellipse. To draw a complete ellipse strangles and end angle should be 0 and 360 respectively.)

6. fill ellipse x y xradius yradius

(creates a filled ellipse)

7. fill polygon

(where xi yi are co-ordinates of each vertex of the polygon)

8. fill flood x y border_color

((x, y) is any point on the screen if (x,y) lies inside the area then inside will be filled otherwise outside will be filled,border specifies the color of boundary of area.)

9. fill sector x y stangle endangle xradius yradius

(parameters same as ellipse)

10. set linestyle linestyle thickness



