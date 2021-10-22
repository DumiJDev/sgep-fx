package org.github.dumijdev.sgepfx.repository;

import org.github.dumijdev.sgepfx.model.Propina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropinaRepository extends CrudRepository<Propina, Long> {
}