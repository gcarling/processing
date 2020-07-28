import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class tutorial extends PApplet {

int rectHeight = 20;
int rectWidth = 80;
Shape[][] rectangles;

public boolean movesRight(int row) {
  return row % 2 == 0;
}

public Shape makeNewRect(int row, int col) {
  float h = random(360);
  float startX = movesRight(row) ? (col-1) * rectWidth : (width - col * rectWidth);
  float y = row * rectHeight;
  return new Shape(startX, y, h);
}

public void setup() {
  // grid setup
  colorMode(HSB, 360, 100, 100);
  

  // vars
  int rowLength = width / rectWidth + 1;
  int numRows = height / rectHeight;
  rectangles = new Shape[numRows][rowLength];

  for (int i = 0; i < numRows; i++) {
    for (int j = 0; j < rowLength; j++) {
      rectangles[i][j] = makeNewRect(i, j);
    }
  }
}

public void repositionRectangles(int row) {
  // move everything one over
  for (int j = rectangles[row].length-1; j >= 1; j--) {
    rectangles[row][j] = rectangles[row][j-1];
  }

  rectangles[row][0] = makeNewRect(row, 0);
}

public void draw() {
  background(255);

  for (int i = 0; i < rectangles.length; i++) {
    Shape[] row = rectangles[i];
    for (int j = 0; j < row.length; j++) {
        Shape r = row[j];

        fill(r.hue, 100, 100);
        rect(r.x, r.y, rectWidth, rectHeight);

        int change = movesRight(i) ? 1 : -1;
        r.x += change;
      }

      Shape last = row[row.length - 1];

      // once we reach the end, we reposition
      if ((movesRight(i) && last.x == width) || (!movesRight(i) && last.x == (-1 * rectWidth))) {
        repositionRectangles(i);
      }
    }
}
// boolean shouldRandom = true;
// float pastVal;
// float x, y;

// void setup() {
//   colorMode(HSB, 360, 100, 100);
//   size(480, 480);
//   x = width/2;
//   y = height/2;
// }

// void mousePressed() {
//   shouldRandom = !shouldRandom;
// }

// void draw() {
//   float val;
//   if (shouldRandom) {
//     val = random(360);
//     pastVal = val;
//   } else {
//     val = pastVal;
//   }
//   fill(val, 100, 100);
//   ellipse(x, y, 80, 80);

//   float xMove = random(10);
//   if (random(2) < 1) {
//     x += xMove;
//     x = min(x, width);
//   } else {
//     x -= xMove;
//     x = max(x, 0);
//   }
//   float yMove = random(10);
//   if (random(2) < 1) {
//     y += yMove;
//     y = min(y, height);
//   } else {
//     y -= yMove;
//     y = max(y, 0);
//   }
// }
public class Shape {
  public float x;
  public float y;
  public float hue;

  Shape(float xcord, float ycord, float h) {
    x = xcord;
    y = ycord;
    hue = h; 
  }
}

  public void settings() {  size(480, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "tutorial" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
