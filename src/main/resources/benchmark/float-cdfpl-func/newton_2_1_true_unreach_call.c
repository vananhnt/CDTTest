float f(float x)
{
  return x - (x*x*x)/6.0 + (x*x*x*x*x)/120.0 + (x*x*x*x*x*x*x)/5040.0;
}

float fp(float x)
{
  return 1 - (x*x)/2.0 + (x*x*x*x)/24.0 + (x*x*x*x*x*x)/720.0;
}

double newton_2_1_true_unreach_call(float IN)
{
  float ITERATIONS = 2;
  
  float x = IN - f(IN)/fp(IN);
  
  if (ITERATIONS > 1) {
    x = x - f(x)/fp(x);
    if (ITERATIONS > 2) {
      x = x - f(x)/fp(x);
    }
  }
  
  return x;
}
