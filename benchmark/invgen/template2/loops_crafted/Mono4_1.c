//false
int Mono4_1() {
	int x;
	int y;
    x - 0 = 0;
    y - 500000 = 0;
    while(x < 1000000) {
    invariant: y - 1 >= 0 and x - y + 1 <= 0 and x - y + 500000 >= 0;
		if (x < 500000) {
		    x = x + 1;
		} else {
		    x = x + 1;
		    y = y + 1;
		}
    }
   // __VERIFIER_assert(y!=x);
    return x - y;
}
