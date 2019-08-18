package com.demo.repository;

import com.demo.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByRequestId(String requestId);

}