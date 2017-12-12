mother(M,X) :- parent(M,X), female(M).

father(F,X) :- parent(F,X), male(F).

sibling(X,Y) :- parent(A,X), parent(A,Y), not(X = Y).

sister(W,Z) :- sibling(W,Z), female(W).

aunt(W,Z) :- parent(A,Z), sibling(W,A), female(W).

grandfather(W,Z) :- parent(A,Z), father(W,A).

ancestor(W,Z) :- parent(W,Z).
ancestor(W,Z) :- parent(W,A), ancestor(A,Z).

nephew(W,Z) :- parent(A,W), sibling(A,Z), male(W).
