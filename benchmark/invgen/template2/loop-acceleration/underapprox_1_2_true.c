
int underapprox_1_2_true() {
  int x = 0;
  int y = 1;

  while (x < 6) {
    x = x + 1;
    y = 2*y;
  }
  return y;
  //__VERIFIER_assert(y % 3);
}