int rectHeight = 20;
int rectWidth = 80;
Shape[][] rectangles;

boolean movesRight(int row) {
  return row % 2 == 0;
}

Shape makeNewRect(int row, int col) {
  float h = random(360);
  float startX = movesRight(row) ? (col-1) * rectWidth : (width - col * rectWidth);
  float y = row * rectHeight;
  return new Shape(startX, y, h);
}

void setup() {
  // grid setup
  colorMode(HSB, 360, 100, 100);
  size(480, 480);

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

void repositionRectangles(int row) {
  // move everything one over
  for (int j = rectangles[row].length-1; j >= 1; j--) {
    rectangles[row][j] = rectangles[row][j-1];
  }

  rectangles[row][0] = makeNewRect(row, 0);
}

void draw() {
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
