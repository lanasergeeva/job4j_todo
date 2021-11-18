

[![codecov](https://codecov.io/gh/lanasergeeva/job4j_todo/branch/master/graph/badge.svg?token=NVW23CLG6I)](https://codecov.io/gh/lanasergeeva/job4j_todo)

[![Build Status](https://app.travis-ci.com/lanasergeeva/job4j_todo.svg?branch=master)](https://app.travis-ci.com/lanasergeeva/job4j_todo)

# Приложение TODO LIST
+ [Описание](#Описание-проекта)
+ [Технологии](#Используемые-технологии)
+ [Функционал](#Функционал-приложения)


## Описание проекта
ToDo List это web-проект в котором реализовано ведение списка дел. 
Доступ к приложению имеют только авторизированные пользователи. Каждый пользователь видит только свой список дел.
Также есть возможность выбора категории или нескольких категорий для задачи.
Вся информация хранится в базе данных.

## Используемые технологии
+ **Maven**
+ **HTML**, **CSS**, **AJAX**, **Jquery**, **Javascript**
+ **Java 14**, **Hibernate**, **Servlet**

## Функционал приложения

Общий вид

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/items.jpg)

Работа с приложение начинается с процесса авторизации пользователя

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/log.jpg)

Если у пользователя еще нет аккаунта, то он может перейти по ссылке, где ему нужно заполнить форму регистрации

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/registr.jpg)

Для всех полей при аваторизации и регистрации стоит валидация.

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/validreg.jpg)

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/log_val.jpg)

Вход нового пользователя в аккаунт.

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/views.jpg)

При добавлении задачи нужно ввести нвазание и выбрать категорию. Категории не ограничены в выборе.

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/items.jpg)

Выполнить задание можно щелчком на указатель. После этого вид задачи будет такой.

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/compitems.jpg)

В меню мы можем выбрать вывод всех задач, либо только активных или только выполненных.

Активные

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/active.jpg)

Выполненные

![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/comp.jpg)

Пример работы программы
![alt text](https://github.com/lanasergeeva/job4j_todo/blob/master/src/main/webapp/image/worktodobig.gif)

Выйти из приложения можно щечком лкм по Log Out в верхнем правом углу.


