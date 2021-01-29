This is an implementation for elliptic curve cryptography in Java.
Due to the weierstrass-polynom

y^2 + a1*x*y + a3*y -x^3 - a2*x^2 -a4*x -a6

we have the coeffixients a1,a2,a3,a4 and a6.

If we set the weierstrass-polanom to zero we can solve it w.r.t. y.
Using quadratic completion we get the following discrimant
D = 4*x^3 + b2*x^2 +2*b4*x + b6, where b2,b4 and b6 are the same as in the elliptic curve
Hence

b2 = a1^2 + 4*a2

b4 = 2*a4 + a1*a3

b6 = a3^2 + 4*a6

Warning: The discriminant w.r.t. y has nothing to do with the discriminant to check singularity of the elliptic curve itself.

Since the discriminant D is a cubic polynom in x and it has to be greater or equal to 0 to get a solution, we should use big x values to find the corresponding y of a Point P(x/y) on the elliptic curve.

If we found a point P on the curve we can calculate P,2P,3P,...kP (by using the add-method of projective point) in the cyclic goup of P.

Now Alice and Bob can calculate kP and lP with their private keys k and l. They exchange kP and lP and calculate klP = lkP. Now Bob and Alice both have the element klP without interchanging their private keys k or l. This principle is called Diffie-Hellman key exchange found on Werner "Elliptische Kurven in der Kryptographie" on page 5.