# Przygotowania

Uruchom skrypt `create_database.sql` na użytkowniku **root** bazy danych.

# Struktura danych




# Zadania

1. Uzupełnij mapowanie encji, tak by test `HibernateBootstraperTest` kończył się powodzeniem.
2. Zaimplementuj metody w `StudentsRepository`:
   1. Zaimplementuj metodę `findStudentById` by ładowała encję studenta, używając `Optional`.
   2. Zaimplementuj metodę `findStudentByIdLazily`, tak by pobierała leniwie encję studenta.
   3. Zaimplementuj `createStudent`, `updateStudent`, `deleteStudent` i `refreshStudent` używając odpowiednich metod z frameworku **Hibernate**.
   4. Używając *JPQL* zaimplementuj `getStudents`, `findStudentsByName`, `getStudentsCount` oraz `getClassesByStudentId`.
   Parametr `name` w `findStudentsByName` może mieć dowolną wielkość znaków.  
   5. Zaimplementuj `getAllFriendStudentsByStudentId` w ten sposób, żeby można było mieć dostęp do pola `schoolClasses` studenta nawet po zaknięciu sesji.
   
3. Zaimplementu metody w `StudentNoteRepository`:

   1. Zaimplementuj metodę `getNotesByStudentId`.
   2. Zaimplementuj metodę `getNotesWithClassNameByStudentId`, tak by zwracała listę `NoteWithClassName`.
   3. Zaimplementuj metodę `prePersist` w `StudentNote`, tak by ustawiała pole `createdAt`.
   
4. Zaimplementuj metody w `TeachersRepository`. 
   1. W metodzie `getTeachersNameDetails` użyj `contat` z **JPQL**.
   2. W metodzie `getTeachersPaging` zaimplementuj paginację.
   3. Zaimplementuj metodę `assignToSchoolClass`, która pozwala na dodanie nauczyciela do przedmiotu.
   
5. Zaimplementuj metody w `SchoolClassRepository`.

6. Zmodyfikuj metody `getStudents`, `findStudentsByName`, `getStudentsCount`, `getClassesByStudentId` oraz `findStudentsByName` tak by używały **NamedQueries**.

7. Użyj `FullName` jako obiekt *embedded* zamiast `firstName` i `lastName` w `Teacher` i `Student`. 
