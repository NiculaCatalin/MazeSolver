Obiectiv:
Obiectivul programului este gasirea celui mai scurt drum intre doua puncte R(Romeo) si 
J(Juliet) dintre-o matrice de tip labirint unde valorile ' ' reprezinta locuri accesibile
si 'X' obstacole. R si J se pot deplasa in oricare din cele 8 directii din jurul lor.
R si J trebuie sa parcurga un numar egal de pasi si nu pot sta pe loc.

Rezolvarea efectiva:
Pentru a rezolva problema am folosit o forma a algoritmului lee care porneste de la un
punct din matricea de joc data si formeaza o matrice de distante. Matricea de distante
a unui punct este o matrice de valori intregi(initializata la 0), copie a matricei de 
joc in care, in loc de X avem 0 si in loc de ' ' / 'R' / 'J' avem lungimea drumului de la 
acel punct pana la punctul dat. Am implementat acest lucru in metoda GetDistanceMatrix 
din clasa Game.

Metoda functioneaza in felul urmator:

1. primeste un obiect de tip QueueNode(acesta pastreaza coordonatele unui punct si o distanta
  int)
2. porneste de la punctul dat de coordonate ox si oy si verifica pentru toti cei 9 vecini
  ai acestuia(inclusiv pe el insusi). Pentru aceasta am folosit 2 for-uri cu i si j de la 
  -1 la 1 si am verificat pentru punctele de coordonate ox+i respectiv oy+j din matricea 
  de joc daca sunt valide. Un punct este valid atunci cand valoarea lui este diferita de X,
  coordonatele lui sunt in limitele matricei de joc: 0->(n-1) si 0->(m-1)si daca nu a mai
  fost parcurs adica daca in matricea de distante are valoarea 0.
3. pentru toate punctele valide creaza cate un obiect de tip QueueNode in care pastreaza
  coordonatele acestora si distanta lor. Mai apoi il introduce intr-o coada de tip LinkedList.
4. pentru punctele respective scrie in matricea de distante valoarea distantei acestora
  pana la punctul dorit.
5. se repeta pasii 2,3,4 pana scotand cate un element din coada si aplicand pasii pe el
  pana cand coada devine goala.
6. returneaza matricea de distante aferenta punctului/nodului dat.

Datele:

Pentru a pastra matricea de joc am folosit clasa CityMap. Aceasta contine o matrice de 
caractere de dimensiune n*m si valosrile n si m.

Pentru a pastra informatiile punctelor valide si pentru a le putea arunca intr-o coada
am folosit clasa QueueNode in care sunt pastrate coordonatele unui punct si o valoare
intreaga int, reprezentand o distanta/lungime de drum.

Pentru a pastra informatiile caracterelor: Romeo si Julieta am considerat a fi mai 
eficienta si practica folosirea clasei QueueNode deoarece avea campurile de coordonate,
numele putea fi dat in loc de numele obiectului : QueueNode Romeo; si a fost mai usor sa
ii dau ca si parametru functiei GetDistanceMatrix.

Pentru a gasi informatiile celor doi am scris doua metode in clasa CityMap care parcurg 
matricea de joc cautand punctul de valoare 'R' respectiv 'J' si care returneaza un obiect
de tip QueueNode cu coordonatele acestora.

Pentru citirea datelor am folosit un scanner, citind linie cu linie datele din fisier si
scriindu-le tot linie cu linie in matricea de joc

Pentru scrierea datelor in fisier am folosit clasa writer pentru care a trebuit sa imi 
declar un terminator de rand deoarece nu exista metoda writer.writeln. Pentru a putea scrie
mai multe puncte, am pastrat toate punctele ce indeplineau conditiile problemei intr-o coada
pe care am dat-o ca si parametru metodei write.

Clasa main:

In aceasta clasa am pornit prin a citi datele din fisier, am aflat Romeo si Julieta(QueueNode)
iar pentru aceste 2 puncte am aflat matricile de distante prin metoda GetDistanceMatrix.
Am cautat distanta egala si minima prin intermediul a 2 for-uri ce parcurg cele 2 matrici iar
apoi le-am mai parcurs inca o data pentru a scrie toate punctele in care distanta este minima
si egala pentru ambele matrici intr-o coada de noduri. Iar mai apoi am trimis aceasta coada
metodei write care a scris fisierul. 


