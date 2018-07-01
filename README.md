﻿# JavaAlgoVis

### Визуализация графа:

- [ ] Добавление и удаление новой вершины
- [ ] Добавление связей при создании вершины
- [ ] Возможность сохранять и загружать конфигурацию из файла
- [ ] Оформление в виде карты метро

Поиск кратчайшего пути между двумя вершинами(алгоритм Дейкстры)

### Основной алгоритм работы, которому мы будем придерживаться:
1. Посмоотреть вкладку "Issues", при возниконовении идеи или несогласии написать коммент
2. Взять себе на реализацию или отписаться, что начал делать (в комментах опять же)
3. Далее создаём отдельную ветку у себя в локальном репо ([ВАЖНО](https://git-scm.com/book/ru/v2/Ветвление-в-Git-Удалённые-ветки))
	- git pull (тянем изменения с ветки master, чтобы клонировать актуальный слепок ветки)
	- git checkout -b Issue_name
4. Делаем фичу, согласно взятому Issue (если возникла новая идея, которую нужно реализовать, тогда создаём свой Issue, затем идём в пункт 2)
5. Производим необходимые действия в нашем репо 
	- git add -A
	- git commit -m "Comment needed!"
6. Отправляем изменения на сервер
	- точная команду я напишу позже, когда определимся с правами доступа (см. ниже)
7. После этого создаём Pull Request (пр), в котором пишем close #(issue_number) и необходимые комментарии. Чем больше комментариев по делу, тем лучше. Строка с close автоматически закроет взятый и обработанный issue.
8. Последний пункт тоже будет дописан позже ввиду причины из пункта 6.


### Не забываем про вкладку Projects!
Некоторые задачи не являются Issue (по сути), поэтому их стоит добавлять как Notes в колонки Projects

### Права доступа и система принятия PR
Собрание по этому поводу в полночь все вместе