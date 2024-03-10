package net.guilhermejr.sistema.energia.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.guilhermejr.sistema.energia.api.mapper.AcompanhamentoMapper;
import net.guilhermejr.sistema.energia.api.request.AcompanhamentoRequest;
import net.guilhermejr.sistema.energia.api.response.AcompanhamentoResponse;
import net.guilhermejr.sistema.energia.config.security.AuthenticationCurrentUserService;
import net.guilhermejr.sistema.energia.domain.entity.Acompanhamento;
import net.guilhermejr.sistema.energia.domain.entity.Processamento;
import net.guilhermejr.sistema.energia.domain.entity.Total;
import net.guilhermejr.sistema.energia.domain.repository.AcompanhamentoRepository;
import net.guilhermejr.sistema.energia.domain.repository.ProcessamentoRepository;
import net.guilhermejr.sistema.energia.domain.repository.TotalRepository;
import net.guilhermejr.sistema.energia.exception.ExceptionDefault;
import net.guilhermejr.sistema.energia.exception.ExceptionNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@AllArgsConstructor
public class AcompanhamentoService {

    private final AcompanhamentoRepository acompanhamentoRepository;
    private final TotalRepository totalRepository;
    private final ProcessamentoRepository processamentoRepository;
    private final AcompanhamentoMapper acompanhamentoMapper;
    private final AuthenticationCurrentUserService authenticationCurrentUserService;

    // --- Retornar -----------------------------------------------------------
    public List<AcompanhamentoResponse> retornar() {

        List<Acompanhamento> acompanhamentoList = acompanhamentoRepository.findAllByOrderByInicioAsc();
        return acompanhamentoMapper.mapList(acompanhamentoList);

    }

    // --- RetornarUm ---------------------------------------------------------
    public AcompanhamentoResponse retornarUm(Long id) {

        Acompanhamento acompanhamento = acompanhamentoExiste(id);
        if (acompanhamento != null) {
            return acompanhamentoMapper.mapObject(acompanhamento);
        } else {
            log.error("Acompanhamento não retornado: {}", id);
            throw new ExceptionNotFound("Não pode retornar acompanhamento. Id não encontrado: " + id);
        }

    }

    // --- Inserir ------------------------------------------------------------
    @Transactional
    public AcompanhamentoResponse inserir(AcompanhamentoRequest acompanhamentoRequest) {

        return salvar(acompanhamentoRequest, null);

    }

    // --- Atualizar ----------------------------------------------------------
    @Transactional
    public AcompanhamentoResponse atualizar(Long id, AcompanhamentoRequest acompanhamentoRequest) {

        if (acompanhamentoExiste(id) != null) {
            return salvar(acompanhamentoRequest, id);
        } else {
            log.error("Acompanhamento não atualizado: {}", id);
            throw new ExceptionNotFound("Não pode atualizar acompanhamento. Id não encontrado: " + id);
        }

    }

    // --- Apagar -------------------------------------------------------------
    @Transactional
    public void apagar(Long id) {

        Acompanhamento acompanhamento = acompanhamentoExiste(id);

        if (acompanhamento != null) {

            // --- Subtrai valores do acompanhamento do total ---
            subtrairTotal(acompanhamento);

            // --- Remove acompanhamento ---
            acompanhamentoRepository.deleteById(id);

        } else {
            log.error("Acompanhamento não apagado: {}", id);
            throw new ExceptionNotFound("Não pode apagar acompanhamento. Id não encontrado: " + id);
        }

    }

    // --- SubtrairTotal ------------------------------------------------------
    private void subtrairTotal(Acompanhamento acompanhamento) {

        // --- Recupera total ---
        Total total = recuperarTotal();

        // --- Atualiza totais ---
        total.setEnergiaGerada(total.getEnergiaGerada().subtract(acompanhamento.getEnergiaGerada()));
        total.setEnergiaInjetada(total.getEnergiaInjetada() - acompanhamento.getEnergiaInjetada());
        total.setEnergiaConsumidaConcessionaria(total.getEnergiaConsumidaConcessionaria() - acompanhamento.getEnergiaConsumidaConcessionaria());
        total.setEnergiaConsumidaTotal(total.getEnergiaConsumidaTotal().subtract(acompanhamento.getEnergiaConsumidaTotal()));
        total.setSaldoMes(total.getSaldoMes() - acompanhamento.getSaldoMes());
        total.setTusd(total.getTusd().subtract(acompanhamento.getTusd()));
        total.setTusdReal(total.getTusdReal().subtract(acompanhamento.getTusdReal()));
        total.setTe(total.getTe().subtract(acompanhamento.getTe()));
        total.setTeReal(total.getTeReal().subtract(acompanhamento.getTeReal()));
        total.setBandeira(total.getBandeira().subtract(acompanhamento.getBandeira()));
        total.setIluminacaoPublica(total.getIluminacaoPublica().subtract(acompanhamento.getIluminacaoPublica()));
        total.setIluminacaoPublicaReal(total.getIluminacaoPublicaReal().subtract(acompanhamento.getIluminacaoPublicaReal()));
        total.setDesconto(total.getDesconto().subtract(acompanhamento.getDesconto()));
        total.setMulta(total.getMulta().subtract(acompanhamento.getMulta()));
        total.setMultaReal(total.getMultaReal().subtract(acompanhamento.getMultaReal()));
        total.setJuros(total.getJuros().subtract(acompanhamento.getJuros()));
        total.setJurosReal(total.getJurosReal().subtract(acompanhamento.getJurosReal()));
        total.setValorTotal(total.getValorTotal().subtract(acompanhamento.getValorTotal()));
        total.setValorTotalReal(total.getValorTotalReal().subtract(acompanhamento.getValorTotalReal()));

        // --- Atualiza total ---
        totalRepository.save(total);

    }

    // --- AcompanhamentoExiste -----------------------------------------------
    private Acompanhamento acompanhamentoExiste(Long id) {

        Optional<Acompanhamento> acompanhamento = acompanhamentoRepository.findById(id);
        return acompanhamento.orElse(null);

    }

    // --- Salvar -------------------------------------------------------------
    private AcompanhamentoResponse salvar(AcompanhamentoRequest acompanhamentoRequest, Long id) {

        // --- Converte acompanhamentoRequest em Acompanhamento ---
        Acompanhamento acompanhamento = acompanhamentoMapper.mapObject(acompanhamentoRequest);

        // --- Adiciona usuário que está fazendo a operação ---
        UUID usuario = authenticationCurrentUserService.getCurrentUser().getId();
        acompanhamento.setUsuario(usuario);

        // --- Verifica se inicio é menor que fim ---
        if (acompanhamento.getInicio().isAfter(acompanhamento.getFim())) {
            log.error("A data início deve ser antes da data fim");
            throw new ExceptionDefault("A data início deve ser antes da data fim");
        }

        // --- Verifica se os dados da geração foram processados até a data fim ---
        Processamento processamento = recuperarProcessamento();
        if (acompanhamento.getFim().isAfter(processamento.getData())) {
            log.error("A data do último processamento da geração de energia solar é menor que a data fim");
            throw new ExceptionDefault("A data do último processamento da geração de energia solar é menor que a data fim");
        }

        // --- Calcula energia gerada ---
        acompanhamento.setEnergiaGerada(acompanhamentoRepository.energiaGerada(acompanhamento.getInicio(), acompanhamento.getFim()));

        // --- Insere id se for atualização ---
        if (id != null) {
            acompanhamento.setId(id);
        }

        // --- Calcula quantidade de dias entre as datas ---
        Long dias = ChronoUnit.DAYS.between(acompanhamento.getInicio(), acompanhamento.getFim());
        acompanhamento.setDias(dias.intValue());

        // --- Calcula consumo total ---
        BigDecimal consumoTotal = BigDecimal.valueOf(acompanhamento.getEnergiaConsumidaConcessionaria()).add(acompanhamento.getEnergiaGerada().subtract(BigDecimal.valueOf(acompanhamento.getEnergiaInjetada())));
        acompanhamento.setEnergiaConsumidaTotal(consumoTotal);

        // --- Calcula saldoMes ---
        Integer saldoMes = acompanhamento.getEnergiaInjetada() - acompanhamento.getEnergiaConsumidaConcessionaria();
        acompanhamento.setSaldoMes(saldoMes);

        // --- Calcula valorTotal ---
        BigDecimal valorTotal = acompanhamento.getTusd().add(acompanhamento.getTe().add(acompanhamento.getBandeira().add(acompanhamento.getIluminacaoPublica().add(acompanhamento.getMulta().add(acompanhamento.getJuros()))))).subtract(acompanhamento.getDesconto());
        acompanhamento.setValorTotal(valorTotal);

        // --- Calcula TUDS real ---
        BigDecimal tudsReal = acompanhamento.getTusd().multiply(consumoTotal).divide(BigDecimal.valueOf(50.0), 2, RoundingMode.CEILING);
        acompanhamento.setTusdReal(tudsReal);

        // --- Calcula TE real ---
        BigDecimal teReal = acompanhamento.getTe().multiply(consumoTotal).divide(BigDecimal.valueOf(50.0), 2, RoundingMode.CEILING);
        acompanhamento.setTeReal(teReal);

        // --- Calcula Iluminação Pública real ---
        BigDecimal somaDeTudsRealComTeReal = tudsReal.add(teReal);
        BigDecimal iluminacaoPublicaReal = calculoIluminacaoPublicaReal(consumoTotal).multiply(somaDeTudsRealComTeReal).divide(BigDecimal.valueOf(100.0), 2, RoundingMode.CEILING);
        acompanhamento.setIluminacaoPublicaReal(iluminacaoPublicaReal);

        // --- Calcula valorTotalReal ---
        BigDecimal valorTotalReal = somaDeTudsRealComTeReal.add(iluminacaoPublicaReal);

        // --- Calcula Multa real ---
        BigDecimal totalSemMultaEJuros = acompanhamento.getTusd().add(acompanhamento.getTe().add(acompanhamento.getBandeira().add(acompanhamento.getIluminacaoPublica())));
        BigDecimal multaReal = BigDecimal.ZERO;
        if (acompanhamento.getMulta().compareTo(BigDecimal.ZERO) != 0) {
            multaReal = totalSemMultaEJuros.divide(valorTotalReal.multiply(acompanhamento.getMulta()), 2, RoundingMode.CEILING);
        }
        acompanhamento.setMultaReal(multaReal);

        // --- Calcula Juros real ---
        BigDecimal jurosReal = BigDecimal.ZERO;
        if (acompanhamento.getJuros().compareTo(BigDecimal.ZERO) != 0) {
            jurosReal = totalSemMultaEJuros.divide(valorTotalReal.multiply(acompanhamento.getJuros()), 2, RoundingMode.CEILING);
        }
        acompanhamento.setJurosReal(jurosReal);

        // --- Calcula valorTotalReal com multa e juros ---
        acompanhamento.setValorTotalReal(valorTotalReal.add(multaReal.add(jurosReal.add(acompanhamento.getBandeira()))).subtract(acompanhamento.getDesconto()));

        // --- Inclui as datas de criação e atualização ---
        if (id == null) {
            acompanhamento.setCriado(LocalDateTime.now(ZoneId.of("UTC")));
        }
        acompanhamento.setAtualizado(LocalDateTime.now(ZoneId.of("UTC")));

        // --- Recupera total ---
        Total total = recuperarTotal();

        // --- Se for atualização subtrai os valores antigos ---
        if (id != null) {

            Acompanhamento acompanhamentoAntigo = acompanhamentoExiste(id);
            subtrairTotal(acompanhamentoAntigo);

        }

        // --- Atualiza totais ---
        total.setEnergiaGerada(total.getEnergiaGerada().add(acompanhamento.getEnergiaGerada()));
        total.setEnergiaInjetada(total.getEnergiaInjetada() + acompanhamento.getEnergiaInjetada());
        total.setEnergiaConsumidaConcessionaria(total.getEnergiaConsumidaConcessionaria() + acompanhamento.getEnergiaConsumidaConcessionaria());
        total.setEnergiaConsumidaTotal(total.getEnergiaConsumidaTotal().add(acompanhamento.getEnergiaConsumidaTotal()));
        total.setSaldoMes(total.getSaldoMes() + acompanhamento.getSaldoMes());
        total.setTusd(total.getTusd().add(acompanhamento.getTusd()));
        total.setTusdReal(total.getTusdReal().add(acompanhamento.getTusdReal()));
        total.setTe(total.getTe().add(acompanhamento.getTe()));
        total.setTeReal(total.getTeReal().add(acompanhamento.getTeReal()));
        total.setBandeira(total.getBandeira().add(acompanhamento.getBandeira()));
        total.setIluminacaoPublica(total.getIluminacaoPublica().add(acompanhamento.getIluminacaoPublica()));
        total.setIluminacaoPublicaReal(total.getIluminacaoPublicaReal().add(acompanhamento.getIluminacaoPublicaReal()));
        total.setDesconto(total.getDesconto().add(acompanhamento.getDesconto()));
        total.setMulta(total.getMulta().add(acompanhamento.getMulta()));
        total.setMultaReal(total.getMultaReal().add(acompanhamento.getMultaReal()));
        total.setJuros(total.getJuros().add(acompanhamento.getJuros()));
        total.setJurosReal(total.getJurosReal().add(acompanhamento.getJurosReal()));
        total.setValorTotal(total.getValorTotal().add(acompanhamento.getValorTotal()));
        total.setValorTotalReal(total.getValorTotalReal().add(acompanhamento.getValorTotalReal()));

        // --- Atualiza saldoMesAcumulado ---
        acompanhamento.setSaldoMesAcumulado(total.getSaldoMes());

        // --- Atualiza total ---
        totalRepository.save(total);

        // --- Salva acompanhamento ---
        Acompanhamento acompanhamentoSave = acompanhamentoRepository.save(acompanhamento);

        // --- Retorno ---
        return acompanhamentoMapper.mapObject(acompanhamentoSave);

    }

    // --- RecuperarProcessamento ---------------------------------------------
    private Processamento recuperarProcessamento() {
        Optional<Processamento> processamentoOptional = processamentoRepository.findById(1L);
        if (processamentoOptional.isEmpty()) {
            log.error("Não existe registro na tabela PROCESSAMENTOS");
            throw new ExceptionDefault("Não existe registro na tabela PROCESSAMENTOS");
        }

        return processamentoOptional.get();
    }

    // --- RecuperarTotal -----------------------------------------------------
    private Total recuperarTotal() {

        Optional<Total> totalOptional = totalRepository.findById(1L);
        if (totalOptional.isEmpty()) {
            log.error("Não existe registro na tabela TOTAIS");
            throw new ExceptionDefault("Não existe registro na tabela TOTAIS");
        }

        return totalOptional.get();
    }

    // --- CalculoIluminacaoPublicaReal ---------------------------------------
    private BigDecimal calculoIluminacaoPublicaReal(BigDecimal kwReal) {

        if (kwReal.doubleValue() < 30.0) {
            return BigDecimal.valueOf(0.11);
        }

        if (kwReal.doubleValue() > 30.1 && kwReal.doubleValue() < 50.0) {
            return BigDecimal.valueOf(0.43);
        }

        if (kwReal.doubleValue() > 50.1 && kwReal.doubleValue() < 100.0) {
            return BigDecimal.valueOf(1.43);
        }

        if (kwReal.doubleValue() > 100.1 && kwReal.doubleValue() < 200.0) {
            return BigDecimal.valueOf(4.3);
        }

        if (kwReal.doubleValue() > 200.1 && kwReal.doubleValue() < 300.0) {
            return BigDecimal.valueOf(11.6);
        }

        if (kwReal.doubleValue() > 300.1 && kwReal.doubleValue() < 650.0) {
            return BigDecimal.valueOf(22.33);
        }

        if (kwReal.doubleValue() > 650.1 && kwReal.doubleValue() < 2000.0) {
            return BigDecimal.valueOf(57.97);
        }

        return BigDecimal.valueOf(60.91);

    }

}
