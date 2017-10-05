takeout(X,[X|Tail],Tail).
takeout(X,[Head|Tail],[Head|Temp]) :- takeout(X,Tail,Temp).
