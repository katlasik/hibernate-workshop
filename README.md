[![CircleCI](https://circleci.com/gh/katlasik/hibernate-workshop.svg?style=svg)](https://circleci.com/gh/katlasik/hibernate-workshop)

# Przygotowania

1. Sklonuj ten projekt.
2. Skonfiguruj [połączenie](assets/Datasource.md) z bazą danych.
3. Otwórz plik `create_database.sql` znajdujący się w katalogu `src/main/resources/db`.
   Następnie klikając prawym klawiszem myszki otwórz menu kontekstowe i wybierz opcję
   `Run 'create_database.sql'...`, a potem wybierz skonfigurowany datasource i naciśnij `ok`.
   Po chwili powienieneś zobaczyć komunikat podobny do `Summary: X of Y statements executed in Z ms`.
4. Otwórz plik `HibernateBootstraperTest` znajdujący się w katalogu `src/test/java/pl/sda/hibernate/utils/`.
   Następnie klikając prawym klawiszem myszki otwórz menu kontekstowe i wybierz opcję
   `Run 'createEntityManager'`. Test powinien zakończyć się powodzeniem.

# Struktura danych

![dane](assets/diagram.png)

# Zadania

1. Uzupełnij mapowanie encji. Wygeneruj schemat bazy danych za pomocą opcji **hibernate.hbm2ddl.auto**.

2. Zaimplementuj metody w `StudentsRepository`:
   1. Zaimplementuj metodę `findStudentById` by ładowała encję studenta, używając `Optional`.
   2. Zaimplementuj metodę `findStudentByIdLazily`, tak by pobierała leniwie encję studenta.
   3. Zaimplementuj `createStudent`, `updateStudent`, `deleteStudent` i `refreshStudent` używając odpowiednich metod z frameworku **Hibernate**.
   4. Używając *JPQL* zaimplementuj `getStudentByName`, tak by zwracał pusty `Optional` gdy uczeń nie zostanie znaleziony.
   5. Używając *JPQL* zaimplementuj `getStudents`, `findStudentsByName`, `getStudentsCount` oraz `getClassesByStudentId`.
      Parametr `name` w `findStudentsByName` może mieć dowolną wielkość znaków.  
   6. Zaimplementuj `getAllFriendStudentsByStudentId` w ten sposób, żeby można było mieć dostęp do pola `schoolClasses` studenta nawet po zaknięciu sesji.
   
3. Zaimplementu metody w `StudentNoteRepository`:
   1. Zaimplementuj metodę `getAverageBySchoolClass`.
   2. Zaimplementuj metodę `getNotesByStudentId`.
   3. Zaimplementuj metodę `getNotesWithClassNameByStudentId`, tak by zwracała listę `NoteWithClassName`.
   4. Zaimplementuj metodę `prePersist` w `StudentNote`, tak by ustawiała pole `createdAt`.
   5. Dodaj odpowiednią kaskadę do relacji ocen do uczniów, tak by przy usuwaniu rekordu ucznia były także
         usuwane wszystkie oceny.
          
4. Zaimplementuj metody w `TeachersRepository`. 
   1. Użyj klasy `FullName` jako rekordu *embedded* zamiast `firstName` i `lastName` w `Teacher`.
      Zmodyfikuj odpowiednio *settery* i *gettery* dla `firstName` oraz `lastName`.
   1. Zaimplementuj `getTeachersNameDetails` używając `concat` z **JPQL**.
   2. W metodzie `getTeachersPaging` zaimplementuj stronnicowanie.
   3. Zaimplementuj metodę `assignToSchoolClass` w encji `Teacher` która pozwala na dodanie nauczyciela do przedmiotu.
   4. Zaimplementuj metodę `preRemove`, która sprawdza czy nauczyciel uczy jakiegokolwiek przedmiotu.
      Jeżeli tak, to zgłoś wyjątek `IllegalStateException`.
   5. Zaimplementuj metodę `getTeachersInitials`, zwracającą inicjały nauczycieli jako pojedyńczy łańcuch znaków,
      zawierający inicjały nauczycieli dzielone przecinkiem. Przykładowy wyniK: `D.L.,B.W.,A.W.`.
      Użyj natywnego zapytania używającego funkcji `SUBSTRING`, `CONCAT` oraz `GROUP_CONCAT`.
   
5. Zaimplementuj metody w `SchoolClassRepository`.
   1. Zaimplementuj metodę `getAllTestsBySchoolClassId`, która zwraca wszystkie testy dla danego przedmiotu.
   2. Zaimplementuj metodę `getAllVerbalTestsBySchoolClassId`, która zwraca wszystkie testy ustne dla danego przedmiotu.
   3. Zaimplementuj metodę `getTestsByType`, która zwraca wszystkie testy danego podtypu.
   4. Zaimplementuj metodę `findSchoolClassByName` w ten sposób, aby zapytanie `JPQL` było umieszone w klasie
      encji `SchoolClass`.
   5. Dodaj możliwość przechowywania listy tematów zajęć dla przedmiotu. W tym celu stwórz w klasie encji `SchoolClass` 
      dodaje pole `lessonTopics` o type kolekcji zawierającej typ `String`. 
      Zaimplementuj metody `addLessonTopic` oraz `getLessonTopics` w encji `SchoolClass`.
   6. Zaimplementuj metodę `getTopics`, która pozwala zwrócić listę tematów dla podanych `id` przedmiotów.
   7. Zaimplementuj metody `getStudentsBySchoolClassName` oraz `getStudentsNotesBySchoolClassId` używając **Criteria API**.
