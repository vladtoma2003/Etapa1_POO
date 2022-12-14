Package data:

1) DataBase:
   Scopul acestei clase este sa tina minte lista de useri, de filme si diferite informatii
   ajutatoare pentru rezolvarea acestei teme. De asemenea mai contine si metodele: existUser
   care verifica daca un user este deja inregistrat pe site; getCurrentMovie, getPurchasedMovie
   si getWatchedMovie care retunreaza filmul cautat din listele de filme specificate in nume.
2) FilterCountryOut:
   Aceasta clasa contine metoda statica filterCountry care scoate filmele care nu pot
   fi vazute de userul logat deoarece aceste filme sunt banate in tara sa.
3) Movie:
   Aceasta clasa contine informatiile filmului dar si urmatoarele metode: compareTo care
   suprascrie metoda compareTO pentru a compara 2 obiecte de tip Movie, compareRating si
   compareDuration care compara fieldurile cu numele din titlu.
4) OutputError:
   Aceasta clasa este outputul. In ea se afla field-urile necesare ce trebuie printate.
5) User:
   Aceasta clasa este Userul efectiv de pe site. In ea se afla si metoda equals care
   suprascire metoda equals si addMovie care copiaza filmele dintr-un user vechi in unul nou.

Package factory(in acest pachet se afla toate clasele implementate cu design patternul
factory):

1) ErrorFactory:
   Aceasta clasa are urmatoarele metode: standardError care creaza eroare de tip standard(
   cand o actiune este esuata, atunci eroarea este de tip standard), succes care creaza un obiect
   nou de tip OutputError cu campurile setate corect pentru afisare.
2) MovieFactory:
   Aceasta clasa are o singura metoda care creaza un obiect nou de tip Movie si il returneaza.
3) PageFactory:
   Aceasta clasa are o singura metoda care primeste numele paginii si returneaza un obiect
   de tipul paginii respective. Acest lucru este necesar pentru a accepta visitorii.
4) UserFactory:
   Aceasta clasa creaza un obiect nou de tipul user si il returneaza.

Package fileio:
Acest package contine toate clasele necesare pentru citirea informatiilor din inputul
dat de teste.

Package mainprogram:

1) Actions:
   In aceasta clasa se creaza un obiect de tipul paginii curente si se verifica daca
   actiunea corecta este de tipul "change page" sau "on page". Daca este de tipul "change page"
   atunci se verifica daca se poate merge la destinatia ceruta de pe pagina curenta, iar in caz
   afirmativ se cheama acceptDestination in care se efectueaza actiunea de mutare de pe o pagina
   pe alta. Daca este de tipul "on page" se verifica daca in pagina curenta exista acitiunea
   ceruta si in caz afirmativ se cheama acceptAction in care se efectueaza actiunea ceruta pe
   pagina curenta.
2) Main:
   In aceasta clasa se citesc datele din json si se initializeaza database ul.

Package pages:
In acest pachet exista toate clasele de tipul Page (de exemplu Login, Register, Logout, ...).
Fiecare clasa contine metodele: canGoThere care verifica daca de pe pagina curenta se poate muta
pe o alta pagina data, canDoAction care verifica daca pagina curenta poate efectua actiunea
dorita, acceptDestination care accepta visitorul de tip Destination si acceptAction care accepta
visitorul de tip action. De asemenea are urmatoarele campuri final: name care este numele
paginii, destinations care este un array cu toate destinatiile posibile si actions care este un
array cu taote actiunile posibile date pe pagina curenta. Acestea nu se pot modifica.

Package visitor:
Acest package contine toate clasele de tip visitor. Aceste clase sunt folosite pentru
implementarea tuturor actiunilor.

1) Interfetele VisitorAction si VisitorDestination:
   Acestea sunt interfete de tip visitor. Ele contin definitiile tuturor metodelor de tip visit.
2) VisitPageDestination:
   In aceasta clasa exista implementarea tuturor visitorilor detstination. Fara aceasta clasa
   actiunea de "change page" nu ar fi posibila. In majoritatea cazurilor, aceste metode schimba
   numele paginii curente in pagina urmatoare. Cazuri mai speciale ar fi "logout" si "see details".
   In logout se efectueaza si actiunea de logout o data cu mutarea in pagina. Aceasta actiune se
   realizeaza cu ajutorul accept-ului din pagina de tip Logout. La see details se schimba si filmul
   curent in filmul cautat (daca exista acest film).
3) VisitPageAction:
   in aceasta clasa exista implementarea tuturor visitorilor action. Aici se efectueaza toate
   actiunile de tip "on page". Toate implementarile sunt diferite:

- Actiunea "login": in aceasta actiune se retine userul logat si se trece pe pagina home.
- Actiunea "register": se creaza un user nou(in cazul in care acesta nu exista deja) si se
  retine in baza de date. De asemenea se trece si pe pagina home.
- Actiunea "search": se cauta filmul dorit si se salveaza rezultatul (se poate salva si o lista
  goala).
- Actiunea "filter": se "uita" toate filmele de pana atunci. Se filtreaza din nou dupa tara si se
  filtreaza toate filmele care pot fi vazute de user. Exista mai multe criterii: sort care le
  aranjeaza crescator/descrescator dupa ratin/duration, contains care afiseaza doar filmele ce
  contin un anumit actor sau fac parte dintr-un anumit genre.
- Actiunea "purchase": in aceasta aciunte se retine filmul care este dorit intr-o lista cu toate
  filmele cumparate de userul curent pana in momentul respectiv.
- Actiunea "watch": se retine filmul vizionat (daca este cumparat inainte) intr-o lista
- Actiunea "rate/like": se seteaza rate-ul/nr de like uri la filmul dorit. Aceasta aciune poate fi
  executata doar daca userul a vizionat filmul inainte.
- Actiunea "buy tokens": se verifica daca userul are destui bain in balance si daca da se cumpara
  token-uri. Un token este echivalentul unei unitate din balance.
- Actiunea "buy premium account": Se verifica daca userul are suficiente token-uri si daca da se
  schimba tipul contului userului curent la premium.
- Actiunea "logout": se "sterge" userul curent si se trece pe pagina de start, unde nu este nimeni
  autentificat.