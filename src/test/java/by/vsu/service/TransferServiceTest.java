package by.vsu.service;

import by.vsu.entity.Account;
import by.vsu.entity.Transfer;
import by.vsu.repository.AccountRepository;
import by.vsu.repository.RepositoryException;
import by.vsu.repository.TransferRepository;
import by.vsu.repository.test.AccountRepositoryImpl;
import by.vsu.repository.test.TransferRepositoryImpl;
import by.vsu.service.exception.AccountNotActiveServiceException;
import by.vsu.service.exception.AccountNotExistsServiceException;
import by.vsu.service.exception.InsufficientAccountFundsServiceException;
import by.vsu.service.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferServiceTest {
	private TransferService transferService;
	private AccountRepository accountRepository;
	private TransferRepository transferRepository;

	@BeforeEach
	void init() {
		accountRepository = new AccountRepositoryImpl();
		transferRepository = new TransferRepositoryImpl();
		transferService = new TransferService();
		transferService.setAccountRepository(accountRepository);
		transferService.setTransferRepository(transferRepository);
	}

	@Test
	void transferTest1() throws ServiceException, RepositoryException {
		transferService.transfer("A001", "A003", 40_00L, "test purpose");
		Account sender = accountRepository.readByNumber("A001").orElseThrow();
		Assertions.assertEquals(60_00L, sender.getBalance());
		Account receiver = accountRepository.readByNumber("A003").orElseThrow();
		Assertions.assertEquals(240_00L, receiver.getBalance());
		Transfer transfer = transferRepository.read(1L).orElseThrow();
		Assertions.assertEquals(sender.getId(), transfer.getSender().orElseThrow().getId());
		Assertions.assertEquals(receiver.getId(), transfer.getReceiver().orElseThrow().getId());
		Assertions.assertEquals(40_00L, transfer.getSum());
		Assertions.assertEquals("test purpose", transfer.getPurpose().orElseThrow());
	}

	@Test
	void transferTest2() throws ServiceException, RepositoryException {
		transferService.transfer("A001", "A003", 40_00L, null);
		Account sender = accountRepository.readByNumber("A001").orElseThrow();
		Assertions.assertEquals(60_00L, sender.getBalance());
		Account receiver = accountRepository.readByNumber("A003").orElseThrow();
		Assertions.assertEquals(240_00L, receiver.getBalance());
		Transfer transfer = transferRepository.read(1L).orElseThrow();
		Assertions.assertEquals(sender.getId(), transfer.getSender().orElseThrow().getId());
		Assertions.assertEquals(receiver.getId(), transfer.getReceiver().orElseThrow().getId());
		Assertions.assertEquals(40_00L, transfer.getSum());
		Assertions.assertTrue(transfer.getPurpose().isEmpty());
	}

	@Test
	void transferTest3() {
		Assertions.assertThrows(
			InsufficientAccountFundsServiceException.class,
			() -> transferService.transfer("A001", "A003", 150_00L, null)
		);
	}

	@Test
	void transferTest4() {
		Assertions.assertThrows(
			AccountNotActiveServiceException.class,
			() -> transferService.transfer("A001", "A002", 50_00L, null)
		);
	}

	@Test
	void transferTest5() {
		Assertions.assertThrows(
			AccountNotActiveServiceException.class,
			() -> transferService.transfer("A002", "A003", 50_00L, null)
		);
	}

	@Test
	void transferTest6() {
		Assertions.assertThrows(
			AccountNotExistsServiceException.class,
			() -> transferService.transfer("A004", "A003", 50_00L, null)
		);
	}

	@Test
	void transferTest7() {
		Assertions.assertThrows(
			AccountNotExistsServiceException.class,
			() -> transferService.transfer("A001", "A004", 50_00L, null)
		);
	}
}
