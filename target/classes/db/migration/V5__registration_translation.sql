drop table if exists temp_translation;

create table temp_translation (
    lang varchar(3),
    language_key text,
    value text
);

insert into temp_translation (lang, language_key, value)
values ('en', 'message.noSuchLanguage', 'No such language available. Please choice ru or en');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.noSuchLanguage', 'Данный язык не поддерживается. Пожалуйста, выберите ru или en');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.nullValue', 'This field cannot be empty');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.nullValue', 'Данное поле не может быть пустым');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.userExist', 'User with such login already exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.userExist', 'Пользователь с таким логином уже существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.noUserExist', 'User with such mail not exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.noUserExist', 'Пользователь с такой почтой не существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'reg.email.subject', 'Private code');

insert into temp_translation (lang, language_key, value)
values ('ru', 'reg.email.subject', 'Личный код');


insert into temp_translation (lang, language_key, value)
values ('en', 'reg.email.message', 'Your private code is: ');

insert into temp_translation (lang, language_key, value)
values ('ru', 'reg.email.message', 'Ваш личный код: ');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.sendCode', 'Code for registrations has sent to your email');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.sendCode', 'Код для подтверждения регистрации отправлен на ваш email');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.noValid', 'Not valid request');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.noValid', 'Невалидный запрос');



insert into temp_translation (lang, language_key, value)
values ('en', 'message.invalid.email.code', 'You enter wrong code');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.invalid.email.code', 'Вы ввели неверный код');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.valid.email.code', 'Code confirmed');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.valid.email.code', 'Код подтвержден');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.access.error', 'Access error');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.access.error', 'Ошибка доступа');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.password.changed', 'New password set');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.password.changed', 'Новый пароль установлен');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.incorrect.login', 'Incorrect login, password or name');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.incorrect.login', 'Неправильный логин, пароль или имя');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.correct.login', 'Log in successfully');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.correct.login', 'Авторизация прошла успешно');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.not.validate.mail', 'At first you must verify mail');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.not.validate.mail', 'Для начала вы должны подтвердить пароль');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.register.success', 'Register successfully');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.register.success', 'Регистрация прошла успешно');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.dateTo.before', '"Date To" can not be before "Date From"');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.dateTo.before', '"Дата До" не может быть меньше "Дата С"');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.organization.name.exist', 'Organization with such name already exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.organization.name.exist', 'Организация с таким именем уже существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.organization.contract.exist', 'Organization with such contract already exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.organization.contract.exist', 'Организация с таким контрактом уже существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.organization.add', 'Organization add successfully');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.organization.add', 'Организация добавлена успешно');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.setted', 'Setted successfully');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.setted', 'Выставлено успешно');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.success', 'Success');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.success', 'Успешно');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.incorrect.mail.address', 'The entered email address is incorrect');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.incorrect.mail.address', 'Введенный адрес электронной почты некорректен');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.currency.exist', 'Currency already exist');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.currency.exist', 'Данная валюта уже существует');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.data.notfound', 'Data not found');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.data.notfound', 'Данные не найдены');


insert into temp_translation (lang, language_key, value)
values ('en', 'message.country.not.exist', 'Country not found');

insert into temp_translation (lang, language_key, value)
values ('ru', 'message.country.not.exist', 'Страна не найдена');


insert into language_key (key)
select language_key
from temp_translation
where lang = 'en';

insert into language_translation (value, key_id, language_id)
select
    value,
    language_key.id,
    (select id
     from language
     where lang = temp_translation.lang)
from temp_translation
         join language_key on temp_translation.language_key = language_key.key;

drop table temp_translation;