package closebyhome.closebyhome.repository;

import closebyhome.closebyhome.models.Data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Integer> {
}
