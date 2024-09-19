-- DROP SCHEMA leilao;

CREATE SCHEMA leilao AUTHORIZATION postgres;

-- DROP SEQUENCE leilao.seq_comprador;

CREATE SEQUENCE leilao.seq_comprador
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE leilao.seq_empresa;

CREATE SEQUENCE leilao.seq_empresa
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE leilao.seq_leilao;

CREATE SEQUENCE leilao.seq_leilao
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE leilao.seq_lote;

CREATE SEQUENCE leilao.seq_lote
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE leilao.seq_unidade;

CREATE SEQUENCE leilao.seq_unidade
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9999999
    START 1
	CACHE 1
	NO CYCLE;-- leilao.tb_empresa definition

-- Drop table

-- DROP TABLE leilao.tb_empresa;

CREATE TABLE leilao.tb_empresa (
    id int4 NOT NULL,
    tx_razao_social varchar(64) COLLATE "pg_c_utf8" NOT NULL,
    tx_cnpj varchar(32) COLLATE "pg_c_utf8" NOT NULL,
    tx_logradouro varchar(64) COLLATE "pg_c_utf8" NULL,
    tx_municipio varchar(64) COLLATE "pg_c_utf8" NULL,
    tx_complemento varchar(64) COLLATE "pg_c_utf8" NULL,
    tx_bairro varchar(64) COLLATE "pg_c_utf8" NULL,
    tx_cep varchar(16) COLLATE "pg_c_utf8" NULL,
    tx_telefone varchar(32) COLLATE "pg_c_utf8" NULL,
    tx_email varchar(254) COLLATE "pg_c_utf8" NOT NULL,
    tx_site varchar(254) COLLATE "pg_c_utf8" NULL,
    tx_usuario varchar(20) COLLATE "pg_c_utf8" NOT NULL,
    tx_senha varchar(128) COLLATE "pg_c_utf8" NULL,
    dt_created_at timestamp NOT NULL,
    dt_updated_at timestamp NOT NULL,
    tx_numero varchar(10) NULL,
    CONSTRAINT empresa_cnpj_uk UNIQUE (tx_cnpj),
    CONSTRAINT empresa_pk PRIMARY KEY (id),
    CONSTRAINT empresa_usuario UNIQUE (tx_usuario)
);

-- leilao.tb_unidade definition

-- Drop table

-- DROP TABLE leilao.tb_unidade;

CREATE TABLE leilao.tb_unidade (
    id int4 NOT NULL,
    tx_nome varchar(128) COLLATE "pg_c_utf8" NOT NULL,
    dt_created_at timestamp NOT NULL,
    dt_updated_at timestamp NOT NULL,
    CONSTRAINT unidade_pk PRIMARY KEY (id)
);

-- leilao.tb_leilao definition

-- Drop table

-- DROP TABLE leilao.tb_leilao;

CREATE TABLE leilao.tb_leilao (
    id int4 NOT NULL,
    nu_codigo int4 NULL,
    tx_descricao varchar(60) COLLATE "pg_c_utf8" NOT NULL,
    vendedor_id int4 NOT NULL,
    dt_inicio_previsto timestamp NOT NULL,
    dt_created_at timestamp NOT NULL,
    dt_updated_at timestamp NOT NULL,
    CONSTRAINT leilao_pk PRIMARY KEY (id),
    CONSTRAINT leilao_vendedor_fk FOREIGN KEY (vendedor_id) REFERENCES leilao.tb_empresa(id)
);

-- leilao.tb_lote definition

-- Drop table

-- DROP TABLE leilao.tb_lote;

CREATE TABLE leilao.tb_lote (
    id int4 NOT NULL,
    nu_numero_lote int4 NULL,
    nu_quantidade numeric NOT NULL,
    nu_valor_inicial numeric NULL,
    unidade_id int4 NOT NULL,
    dt_created_at timestamp NOT NULL,
    dt_updated_at timestamp NOT NULL,
    leilao_id int4 NULL,
    tx_descricao varchar(60) COLLATE "pg_c_utf8" NOT NULL,
    CONSTRAINT lote_pk PRIMARY KEY (id),
    CONSTRAINT leilao_lote_fk FOREIGN KEY (leilao_id) REFERENCES leilao.tb_leilao(id),
    CONSTRAINT lote_unidade_fk FOREIGN KEY (unidade_id) REFERENCES leilao.tb_unidade(id)
);

-- leilao.tb_comprador definition

-- Drop table

-- DROP TABLE leilao.tb_comprador;

CREATE TABLE leilao.tb_comprador (
    empresa_id int4 NOT NULL,
    leilao_id int4 NOT NULL,
    CONSTRAINT comprador_pk PRIMARY KEY (empresa_id, leilao_id),
    CONSTRAINT empresa_comp_fk FOREIGN KEY (empresa_id) REFERENCES leilao.tb_empresa(id),
    CONSTRAINT leilao_comp_fk FOREIGN KEY (leilao_id) REFERENCES leilao.tb_leilao(id)
);
