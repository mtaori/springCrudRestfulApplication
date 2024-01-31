package springbootcurdrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springbootcurdrestfulwebservices.entity.Employee;

@Repository
public interface EmployeeReposistory extends JpaRepository<Employee, Long>
{

}
