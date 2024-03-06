CREATE TABLE public.processamentos (
    id bigserial NOT NULL,
    data DATE NOT NULL,
    atualizado TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT processamentos_pkey PRIMARY KEY (id)
);