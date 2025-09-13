package com.ilpmum.hotel.catalog_service.repo;
 
import com.ilpmum.hotel.catalog_service.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}