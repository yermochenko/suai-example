package by.vsu.repository;

import by.vsu.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Repository<Account> {
	List<Account> readAll() throws RepositoryException;

	List<Account> readActive() throws RepositoryException;

	Optional<Account> readByNumber(String number) throws RepositoryException;
}
