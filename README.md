# Hotel

## Testowanie i jakość oprogramowania- projekt

***

## Temat projektu

### Testowanie systemu wynajmu pokoi

***

## Scenariusze testowe dla testera manualnego

***

| Test <br/>Case ID | Opis                                                                                                     | Kroki testowe                                                                                                                                                                         | Oczekiwany wynik                                                                                                                    |
|-------------------|----------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| TC_01             | Zarejestrowanie nowego uzytkownika.                                                                      | 1. Kliknij przycisk na stronie "Zarejestruj się".<br/>2. Wypełnij formularz rejestracji. <br/>3. Kliknij Przycisk 'Zarejestruj się'.                                                  | Utworzenie nowego konta użytkownika                                                                                                 |
| TC_02             | Próba zarejestrowania nowego użytkownika na te sam adres email                                           | 1. Zarejestruj nowego użytkownika o adresie email: janek@email.com<br/> 2.Zarejestruj kolejnego użytkownika o adresie email: janek@email.com                                          | Konto o adresie email janek@email.com nie zostanie utworzone, użytkownik już istnieje.                                              |
| TC_03             | Próba zalogowania się na istniejącego w systemie użytkownika.                                            | 1. Kliknij przycisk 'Zaloguj się'.<br/>2. Wypełnij formularz poprawnymi danymi do logowania.<br/>3. Kliknij przycisk 'Zaloguj się'.                                                   | Użytkownik zostanie zalogowany na swój profil.                                                                                      |
| Tc_04             | Próba zalogowania się na nieistniejącego użytkownika w systemie                                          | 1. Kliknij przycisk 'Zaloguj się'.<br/>2. Wypełnij formularz danymi do logowania.<br/>3. Kliknij przycisk 'Zaloguj się'.                                                              | Użytkownik nie zostanie zalogowany, użytkownik nie jest zarejestrowany w systemie.                                                  |
| TC_05             | Próba rezerwacji pokoju na dni 12.12.2023-14.12.2023 przez niezalogoawnego użytkownika.                  | 1. Kliknij w wybrany pokój.<br/>2. Kliknij w przycisk 'zarezerwuj pokój'.<br/>3. Wybierz zakres dni 12.12.2023-14.12.2023.<br/>4. Potwierdź rezerwacje przyciksiem 'zarezerwuj pokój' | Użytkownik nie jest w stanie zarezerwować pokoju na termin 12.12.2023-14.12.2023, brak uprawnień.                                   |
| TC_06             | Próba rezerwacji pokoju na dni 12.12.2023-14.12.2023 przez zalogowanego użytkownikowa.                   | 1. Kliknij w wybrany pokój.<br/>2. Kliknij w przycisk 'zarezerwuj pokój'.<br/>3. Wybierz zakres 12.12.2023-14.12.2023.<br/>4. Potwierdź rezerwacje przyciksiem 'zarezerwuj pokój'     | Użytkownik rezerwuje pokój na termin 12.12.2023-14.12.2023, zalogowany użytkownik posiada uprawnienia do rezerwacji pokoju.         |
| TC_07             | Próba rezerwacji zajętego pokoju hotelowgo na dni 12.12.2023-14.12.2023  przez zalogowanego użytkownika. | 1. Kliknij w wybrany pokój.<br/>2. Kliknij w przycisk 'zarezerwuj pokój'.<br/>3. Wybierz zakres 12.12.2023-14.12.2023.<br/>4. Potwierdź rezerwacje przyciksiem 'zarezerwuj pokój'     | Uzytkownik nie jest w stanie zarezerwować pokoju na termin 12.12.2023-14.12.2023, niemożna zarezerwować zarezerwowanego już pokoju. |
| TC_08             | Dodanie nowego pokoju 309 przez Administratora.                                                          | 1. Kliknij w przycisk 'dodaj pokój' w panelu administracyjnym.<br/>2. Wypełnij formularz wymaganymi danymi.<br/>3.kliknij przycisk 'dodaj pokój'                                      | Pokój zostanie dodany do hotelu.                                                                                                    |
| TC_09             | Usunięcie pokoju 309 przez Administratora.                                                               | 1. Kliknij w przycisk przy pokoju 'usuń pokój' w panelu administracyjnym<br/> Potwierdź usunięcie pokoju w wyskakującym oknie.                                                        | Wybrany pokój zostanie usunięty.                                                                                                    |
| TC_10             | Dodanie nowego pokoju o tym samym numerze pokoju przez administratora                                    | 1. Kliknij w przycisk 'dodaj pokój' w panelu administracyjnym.<br/>2.Dodaj nowy pokój o numerze 309.<br/>3.Dodaj kolejny pokój o numerze 309.                                         | Pokój 309 nie zostanie dodany podwójnie, aplikacja nie pozwala na dublowanie sie numerów pokoi.                                     |
| TC_11             | Dodanie nowego typu pokoju.- pokój 2-osobowy, 2-łóżka                                                    | 1. Kliknij w przycisk 'dodaj nowy typ pokoju' w panelu administracyjnym<br/>Wypełnij formularz 'pokój 2-osobowy, 2-łóżka'                                                             | Dodanie nowego typu pokoju - '2-osobowy 2-łóżka'.                                                                                   |

## Autorzy

- Katarzyna Bączek
- Wojciech Schabowski 