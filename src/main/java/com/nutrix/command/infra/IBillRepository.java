package com.nutrix.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillRepository extends JpaRepository<Bill, String> {
}