package by.vsu.repository;

import by.vsu.entity.Transfer;

import java.util.List;

public interface TransferRepository extends Repository<Transfer> {
	List<Transfer> readByAccount(Long accountId) throws RepositoryException;
}
