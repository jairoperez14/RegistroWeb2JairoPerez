PGDMP     4                    y            REGISTER    12.1    12.1     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    24658    REGISTER    DATABASE     �   CREATE DATABASE "REGISTER" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_United States.1252' LC_CTYPE = 'Spanish_United States.1252';
    DROP DATABASE "REGISTER";
                postgres    false            �            1259    24665    registeruser    TABLE     �   CREATE TABLE public.registeruser (
    name character varying,
    pass character varying,
    email character varying,
    country character varying,
    ci character varying,
    tlf character varying
);
     DROP TABLE public.registeruser;
       public         heap    postgres    false            �
          0    24665    registeruser 
   TABLE DATA           K   COPY public.registeruser (name, pass, email, country, ci, tlf) FROM stdin;
    public          postgres    false    202   �       �
      x������ � �     