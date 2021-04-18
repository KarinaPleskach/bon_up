create table language (
    id bigserial primary key,
    lang character varying(3) not null,
    name character varying(45) not null,
    constraint languages_lang_uq unique (lang),
    constraint languages_name_uq unique (name)
);

insert into language (lang, name) values
    ('en', 'English'),
    ('ru', 'Русский');

create table language_key (
    id bigserial primary key,
    key character varying(255) not null,
    constraint language_keys_key_uq unique (key)
);

create table language_translation (
    id bigserial primary key,
    value character varying(255) not null,
    key_id bigint not null,
    language_id bigint not null,
    constraint language_translations_ids_uq unique (key_id, language_id),
    constraint language_translations_language_keys_fk foreign key (key_id) references language_key (id),
    constraint language_translations_languages_fk foreign key (language_id) references language (id)
);
