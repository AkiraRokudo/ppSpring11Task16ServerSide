Серверная сторона сервиса.

Страницы для администратора

@GetMapping("/admin/allusers") - получение списка ролей

@GetMapping("/admin/allusers") - получение списка пользователей

@GetMapping("/admin/edituser/{id}") - получение информации по пользователю,данные которого планируется отредактировать. {id} - динамическая часть урла, эквивалетная id пользователя.

@PostMapping("/admin/create") - создание нового пользователя. Ожидает на вход body с информацией о новом пользователе

@PutMapping("/admin/edit") - редактирование информации по пользователю. Ожидает на вход body с информацией об отредактированном пользователе

@DeleteMapping("/admin/delete") - удаление пользователя.Ожидает на вход body с информацией об удаляемом пользователе

Страницы для пользователя(также доступны для просмотра администратору)

@PostMapping("/userInfo") - получение информации по авторизованному пользователю

@PostMapping("/user

Модель данных

roles:
Long id; PK
String name;

user:
Long id; PK
String login; - уникальный
String firstName; - ограничение на пустоту
String lastName; - ограничение на пустоту
String password; - ограничение на пустоту
Long money; - ограничение на значение: может иметь значение только больше или равно 0

user_role:
user_id - FK на user.id
role_id - FK на role.id
