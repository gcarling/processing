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
