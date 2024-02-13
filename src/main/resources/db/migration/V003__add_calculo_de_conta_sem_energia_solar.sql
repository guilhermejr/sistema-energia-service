ALTER TABLE public.acompanhamentos ADD COLUMN tusd_real numeric(19,2);
ALTER TABLE public.acompanhamentos ADD COLUMN te_real numeric(19,2);
ALTER TABLE public.acompanhamentos ADD COLUMN iluminacao_publica_real numeric(19,2);
ALTER TABLE public.acompanhamentos ADD COLUMN valor_total_real numeric(19,2);

ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN tusd_real numeric(19,2);
ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN te_real numeric(19,2);
ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN iluminacao_publica_real numeric(19,2);
ALTER TABLE auditoria.acompanhamentos_auditoria ADD COLUMN valor_total_real numeric(19,2);

ALTER TABLE public.totais ADD COLUMN tusd_real numeric(19,2);
ALTER TABLE public.totais ADD COLUMN te_real numeric(19,2);
ALTER TABLE public.totais ADD COLUMN iluminacao_publica_real numeric(19,2);
ALTER TABLE public.totais ADD COLUMN valor_total_real numeric(19,2);

ALTER TABLE auditoria.totais_auditoria ADD COLUMN tusd_real numeric(19,2);
ALTER TABLE auditoria.totais_auditoria ADD COLUMN te_real numeric(19,2);
ALTER TABLE auditoria.totais_auditoria ADD COLUMN iluminacao_publica_real numeric(19,2);
ALTER TABLE auditoria.totais_auditoria ADD COLUMN valor_total_real numeric(19,2);

UPDATE public.acompanhamentos SET tusd_real = 0, te_real = 0, iluminacao_publica_real = 0, valor_total_real = 0;