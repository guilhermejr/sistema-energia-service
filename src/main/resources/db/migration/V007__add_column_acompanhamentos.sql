ALTER TABLE public.acompanhamentos ADD COLUMN multa numeric(19,2);
ALTER TABLE public.acompanhamentos ADD COLUMN juros numeric(19,2);
ALTER TABLE public.acompanhamentos ADD COLUMN multa_real numeric(19,2);
ALTER TABLE public.acompanhamentos ADD COLUMN juros_real numeric(19,2);

UPDATE public.acompanhamentos SET multa = 0, juros = 0, multa_real = 0, juros_real = 0;

ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN multa numeric(19,2);
ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN juros numeric(19,2);
ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN multa_real numeric(19,2);
ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN juros_real numeric(19,2);

ALTER TABLE public.totais ADD COLUMN multa numeric(19,2);
ALTER TABLE public.totais ADD COLUMN juros numeric(19,2);
ALTER TABLE public.totais ADD COLUMN multa_real numeric(19,2);
ALTER TABLE public.totais ADD COLUMN juros_real numeric(19,2);

ALTER TABLE auditoria.totais_auditoria ADD COLUMN multa numeric(19,2);
ALTER TABLE auditoria.totais_auditoria ADD COLUMN juros numeric(19,2);
ALTER TABLE auditoria.totais_auditoria ADD COLUMN multa_real numeric(19,2);
ALTER TABLE auditoria.totais_auditoria ADD COLUMN juros_real numeric(19,2);

UPDATE public.totais SET multa = 0, juros = 0, multa_real = 0, juros_real = 0;