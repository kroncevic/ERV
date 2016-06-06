package hr.tvz.rome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.rome.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
