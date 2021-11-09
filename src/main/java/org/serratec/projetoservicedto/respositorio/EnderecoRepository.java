package org.serratec.projetoservicedto.respositorio;

import org.serratec.projetoservicedto.dominio.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	Endereco findByCep(String cep);
}
