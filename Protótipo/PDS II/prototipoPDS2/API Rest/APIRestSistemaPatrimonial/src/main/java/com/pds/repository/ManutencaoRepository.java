package com.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pds.model.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{

}