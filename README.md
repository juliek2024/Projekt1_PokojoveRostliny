Projekt : První projekt: Pokojové rostliny = Text zadání:

Úkol 1: Model dat
Připrav třídu pro ukládání informací o pokojových rostlinách (plant). U každé rostliny chceme evidovat:

název (name)
poznámky (notes)
datum, kdy byly zasazena (planted)
datum poslední zálivky (watering)
běžnou frekvenci zálivky ve dnech (frequency of watering)
﻿

Vytvoř tři konstruktory:

jeden pro nastavení všech atributů
druhý nastaví jako poznámku prázdný řetězec a datum zasazení i datum poslední zálivky nastaví na dnešní datum
třetí nastaví totéž co druhý a navíc výchozí frekvenci zálivky na 
7 dnů 
Uživatel tedy zadá pouze název rostliny.
﻿
Vytvoř výchozí přístupové metody pro všechny atributy.

﻿

Úkol 2: Metody
Připrav metody:

getWateringInfo()
Vrátí textovou informaci obsahující název květiny, datum poslední zálivky a datum doporučené další zálivky.
doWateringNow()
Nastaví datum poslední zálivky na dnešní den.
﻿

Úkol 3: Ošetření nesprávného vstupu
Vytvoř novou třídu výjimek s názvem PlantException. Bude potomkem (extends) třídy Exception.

Ošetři tyto chybové stavy:

zadávání frekvence zálivky — pokud je parametrem 0 nebo záporné číslo, 
zadávání data poslední zálivky — nesmí být starší než datum zasazení rostliny.
﻿
Při chybném zadání vyhoď výjimku třídy PlantException s vhodným popisem chyby.

Nezapomeň zahrnout do ošetření i zadávání dat v konstruktoru třídy.

﻿
Úkol 4: Správa seznamu květin
Vytvoř třídu pro ukládání správu seznamu pokojových květin. Jako atribut bude mít kolekci, uchovávající objekty s informacemi o květinách.

Chceme mít možnost vytvářet více seznamů a jednotlivé seznamy exportovat do souboru či je ze souboru načítat.

Vytvoř metody pro:

 přidání nové květiny
získání květiny na zadaném indexu
odebrání květiny ze seznamu
získání kopie seznamu květin.

Přidej metodu, která vrátí seznam rostlin, které je třeba zalít. 

(Jejich datum poslední zálivky je starší než počet dnů, kdy mají být zalité.)
﻿

Úkol 5: Řazení a práce s rostlinami
Do správce seznamu rostlin přidej možnost seřadit rostliny 
v seznamu podle názvu a podle data poslední zálivky.

Řazení podle názvu rostliny nastav jako výchozí variantu řazení rostlin.


Úkol 6: Načtení ze souboru a uložení do souboru
Přidej do seznamu květin metody pro:

načtení květin ze souboru,
uložení květin do souboru.

Z jednoho souboru se vždy načte a uloží obsah jednoho seznamu květin.

V případě chybného vstupu vyhoď výjimku.

Vyzkoušej, že aplikace správně zareportuje chybu při načtení vadných souborů:

 kvetiny-spatne-datum.txt
kvetiny-spatne-frekvence.txt
Aplikace by neměla havarovat, ale vypsat chybu a řádně pokračovat s prázdným seznamem.

Soubory si stáhni celé! Při kopírování obsahu IntelliJ nahradí tabulátory za mezery a načtení nebude fungovat správně! 


Úkol 7: Ověření funkčnosti aplikace
Vytvoř testovací kód v metodě main:

Načti seznam květin ze souboru kvetiny.txt.
Soubor si stáhni celý! Oddělovačem jsou tabulátory (\t). Při kopírování obsahu IntelliJ nahradí tabulátory za mezery a načtení nebude fungovat správně!
Vypiš na obrazovku informace o zálivce pro všechny květiny ze seznamu.
Přidej novou květinu do seznamu (údaje si vymysli). 
Přidej 10 rostlin s popisem „Tulipán na prodej 1“ až „Tulipán na prodej 10“. Zasazeny byly dnes, zality také, frekvence zálivky je 14 dnů.
Květinu na třetí pozici odeber ze seznamu (prodali jsme ji).
Ulož seznam květin do nového souboru a ověř, že je jeho obsah odpovídá provedeným změnám.
Vyzkoušej opětovné načtení vygenerovaného souboru. 
Vyzkoušej seřazení rostlin ve správci seznamu podle různých kritérií a výpis seřazeného seznamu.
