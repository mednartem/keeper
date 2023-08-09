create table public.account (
                                id integer primary key not null default nextval('account_id_seq'::regclass),
                                name character varying,
                                value integer
);
create unique index account_id_key on account using btree (id);

