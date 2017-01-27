#include <stdio.h>
#include <graphics.h>

//creating a structure for input commands from the user
struct emp {
	char w1[10]; //first string
	char w2[10]; //second string
	int a, b, c, d , x , y; //parameters of the figure
	char w3[10]; // aditional color parameters
};

int main()
{

	struct emp e;
	int gd = DETECT, gm;
	initgraph(&gd, &gm, NULL);
	FILE *fp;
	fp = fopen("Instructions.txt", "r");
	int line_count=0;
	char ch;
	int i , sides[100];
	while(!feof(fp)) {
		fscanf(fp, "%s %s", e.w1, e.w2);
		switch(e.w1[0]) {
			case 'd':
			case 'D':
				switch(e.w2[0]) {
					//draw line
					case 'l':
					case 'L':
						line_count++;
						fscanf(fp, "%d %d %d %d", &e.a, &e.b, &e.c, &e.d);
						line(e.a, e.b, e.c, e.d);
						break;
					//draw circle
					case 'c':
					case 'C':
						line_count++;
						fscanf(fp, "%d %d %d", &e.a, &e.b, &e.c);
						circle(e.a, e.b, e.c);
						break;
					//draw rectangle
					case 'R':
					case 'r':
						line_count++;
						fscanf(fp, "%d %d %d %d", &e.a, &e.b, &e.c, &e.d);
						rectangle(e.a, e.b, e.c, e.d);
						break;
					//draw ellipse
					case 'e':
					case 'E':
						line_count++;
						fscanf(fp, "%d %d %d %d %d %d", &e.a, &e.b, &e.c, &e.d , &e.x , &e.y);
						ellipse(e.a, e.b, e.c, e.d , e.x , e.y);
						
						break;
					//draw polygon
					case 'p':
					case 'P':
						line_count++;
						fscanf(fp, "%d", &e.a);
						for(i=0; i<2*e.a; i+=2) {
							fscanf(fp, "%d %d", &sides[i] , &sides[i+1]);
						}
						sides[2*e.a]=sides[0];
						sides[2*e.a+1]=sides[1];
						drawpoly(e.a+1, sides);
						break;
					default:
						line_count++;
						printf("Error on line no. %d\n", line_count);
						
				}
				break;
			case 'f':
			case 'F':
				switch(e.w2[0]) {
					//fill ellipse
					case 'e':
					case 'E':
						line_count++;
						fscanf(fp, "%d %d %d %d", &e.a, &e.b, &e.c, &e.d);
						fillellipse(e.a, e.b, e.c, e.d);
						break;
					//fill polygon
					case 'p':
					case 'P':
						line_count++;
						fscanf(fp, "%d", &e.a);
						for(i=0; i<2*e.a; i+=2) {
							fscanf(fp, "%d %d", &sides[i] , &sides[i+1]);
						}
						sides[2*e.a]=sides[0];
						sides[2*e.a+1]=sides[1];
						fillpoly(e.a+1, sides);
						break;
					//fill flood ie. an enclosed area 
					case 'f':
					case 'F':
						line_count++;
						fscanf(fp, "%d %d %d", &e.a, &e.b, &e.c);
						setcolor(e.c);
						floodfill(e.a, e.b, WHITE);	
						setcolor(15);
						break;
					//fill a sector (part of an ellipse)
					case 'S':
					case 's':
						line_count++;
						fscanf(fp, "%d %d %d %d %d %d", &e.a, &e.b, &e.c, &e.d , &e.x , &e.y);
						sector(e.a, e.b, e.c, e.d , e.x , e.y);	
						break;						
					default:
						line_count++;
						printf("Error on line no. %d\n", line_count);
				}
				break;
			case 's':
			case 'S':
				switch(e.w2[0]) {
					case 'l':
					case 'L':
						line_count++;
						fscanf(fp, "%d %d", &e.a, &e.b);	
						setlinestyle(e.a, 1, e.b);
						break;
				}
			default:
				printf("\n");
		}
	}
	fclose(fp);
	delay(1000);
	getch();
	closegraph();
	return 0;
}
