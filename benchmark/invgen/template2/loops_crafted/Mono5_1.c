//false
int Mono5_1() {
	int x, y, z;
	x = 0;
	y = 10000000;
	z = 5000000;
	while (x<y) {	
		if (x < 5000000) {
			x = x + 1;
		} else {
			z = z - 1;
			x = x + 1;
		}
	}
//  __VERIFIER_assert(z!=0);
	return z;
}