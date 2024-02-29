BEGIN;


CREATE TABLE IF NOT EXISTS public.address
(
    address_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    address_lines character varying(255) COLLATE pg_catalog."default",
    landmark character varying(255) COLLATE pg_catalog."default",
    pincode character varying(255) COLLATE pg_catalog."default",
    city_id bigint,
    primary_phone character varying(255) COLLATE pg_catalog."default",
    is_deleted integer,
    update_time timestamp without time zone,
    create_time timestamp without time zone,
    CONSTRAINT pk_address PRIMARY KEY (address_id)
);

CREATE TABLE IF NOT EXISTS public.app_user
(
    user_id bigint NOT NULL,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    username character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    mobile_number character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    enabled integer NOT NULL,
    account_expired integer NOT NULL,
    account_locked integer NOT NULL,
    CONSTRAINT pk_appuser PRIMARY KEY (user_id),
    CONSTRAINT user_mobile_uidx UNIQUE (mobile_number)
);

CREATE TABLE IF NOT EXISTS public.city
(
    city_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    city_name character varying(255) COLLATE pg_catalog."default",
    state_name character varying(255) COLLATE pg_catalog."default",
    country_name character varying(255) COLLATE pg_catalog."default",
    is_deleted integer,
    create_time time without time zone,
    update_time time without time zone,
    CONSTRAINT pk_city PRIMARY KEY (city_id)
);

CREATE TABLE IF NOT EXISTS public.booking_reservation
(
    booking_reservation_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    booking_id bigint,
    movie_show_id bigint,
    seat_id bigint,
    booking_date date,
    version integer NOT NULL,
    cost numeric,
    discount_amount numeric,
    ticket_index integer,
    is_deleted smallint DEFAULT 0,
    CONSTRAINT pk_booking_reservation PRIMARY KEY (booking_reservation_id),
    CONSTRAINT booking_reservation_movie_show_id_is_deleted_seat_id_key UNIQUE (movie_show_id, is_deleted, seat_id)
);

CREATE TABLE IF NOT EXISTS public.booking
(
    booking_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    movie_show_id bigint NOT NULL,
    user_id bigint,
    booking_date date,
    is_deleted smallint DEFAULT 0,
    CONSTRAINT pk_booking PRIMARY KEY (booking_id)
);

CREATE TABLE IF NOT EXISTS public.movie
(
    movie_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    movie_name character varying(255) COLLATE pg_catalog."default",
    release_date timestamp without time zone,
    is_deleted integer,
    update_time timestamp without time zone,
    create_time timestamp without time zone,
    CONSTRAINT pk_movie PRIMARY KEY (movie_id)
);

CREATE TABLE IF NOT EXISTS public.movie_details
(
    movie_details_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    movie_details_attribute_id bigint,
    movie_id bigint,
    key_text character varying(255) COLLATE pg_catalog."default",
    value_text character varying(255) COLLATE pg_catalog."default",
    is_deleted integer,
    update_time timestamp without time zone,
    create_time timestamp without time zone,
    CONSTRAINT pk_moviedetails PRIMARY KEY (movie_details_id)
);

CREATE TABLE IF NOT EXISTS public.movie_show
(
    movie_show_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    movie_id bigint NOT NULL,
    screen_id bigint NOT NULL,
    show_timing_from time without time zone NOT NULL,
    show_timing_to time without time zone NOT NULL,
    show_date date NOT NULL,
    is_deleted integer DEFAULT 0,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    CONSTRAINT pk_movieshow PRIMARY KEY (movie_show_id)
);

CREATE TABLE IF NOT EXISTS public.movie_show_seat_lock
(
    movie_show_id bigint NOT NULL,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    seat_id bigint NOT NULL,
    lock_expiration_time timestamp without time zone,
    version bigint,
    CONSTRAINT pk_movie_show_seat_lock PRIMARY KEY (movie_show_id, seat_id)
);

CREATE TABLE IF NOT EXISTS public.offer
(
    offer_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    offer_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    offer_description character varying(255) COLLATE pg_catalog."default",
    offer_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    conditions jsonb,
    is_active boolean,
    amount numeric,
    CONSTRAINT pk_offer PRIMARY KEY (offer_id)
);

CREATE TABLE IF NOT EXISTS public.screen
(
    screen_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    screen_name character varying(255) COLLATE pg_catalog."default",
    theater_id bigint,
    is_deleted integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    CONSTRAINT pk_screen PRIMARY KEY (screen_id)
);

CREATE TABLE IF NOT EXISTS public.seat
(
    seat_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    seat_name character varying(255) COLLATE pg_catalog."default",
    seat_category_id bigint,
    create_time time without time zone,
    update_time timestamp without time zone,
    CONSTRAINT pk_seat PRIMARY KEY (seat_id)
);

CREATE TABLE IF NOT EXISTS public.seat_category
(
    seat_category_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    seat_category_name character varying(255) COLLATE pg_catalog."default",
    screen_id bigint,
    is_deleted integer,
    create_time timestamp without time zone,
    update_time timestamp without time zone,
    CONSTRAINT pk_seatcategory PRIMARY KEY (seat_category_id)
);

CREATE TABLE IF NOT EXISTS public.seat_price
(
    seat_price_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    movie_show_id bigint NOT NULL,
    seat_category_id bigint NOT NULL,
    price numeric NOT NULL,
    CONSTRAINT pk_seat_price PRIMARY KEY (seat_price_id),
    CONSTRAINT seat_price_movie_show_id_seat_category_id_key UNIQUE (movie_show_id, seat_category_id)
);

CREATE TABLE IF NOT EXISTS public.theater
(
    theater_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    theater_name character varying(255) COLLATE pg_catalog."default",
    address_id bigint,
    is_deleted integer,
    create_time timestamp with time zone,
    update_time timestamp with time zone,
    CONSTRAINT pk_theater PRIMARY KEY (theater_id)
);

ALTER TABLE IF EXISTS public.address
    ADD FOREIGN KEY (city_id)
    REFERENCES public.city (city_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.booking_reservation
    ADD FOREIGN KEY (booking_id)
    REFERENCES public.booking (booking_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.booking_reservation
    ADD FOREIGN KEY (movie_show_id)
    REFERENCES public.movie_show (movie_show_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.booking_reservation
    ADD FOREIGN KEY (seat_id)
    REFERENCES public.seat (seat_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.booking
    ADD FOREIGN KEY (user_id)
    REFERENCES public.app_user (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.movie_details
    ADD FOREIGN KEY (movie_id)
    REFERENCES public.movie (movie_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.movie_show
    ADD FOREIGN KEY (movie_id)
    REFERENCES public.movie (movie_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.movie_show
    ADD FOREIGN KEY (screen_id)
    REFERENCES public.screen (screen_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.screen
    ADD FOREIGN KEY (theater_id)
    REFERENCES public.theater (theater_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.seat
    ADD FOREIGN KEY (seat_category_id)
    REFERENCES public.seat_category (seat_category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.seat_category
    ADD FOREIGN KEY (screen_id)
    REFERENCES public.screen (screen_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.seat_price
    ADD FOREIGN KEY (seat_category_id)
    REFERENCES public.seat_category (seat_category_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;