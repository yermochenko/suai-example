package by.vsu.service;

import by.vsu.entity.Account;
import by.vsu.entity.Transfer;
import by.vsu.repository.AccountRepository;
import by.vsu.repository.RepositoryException;
import by.vsu.repository.TransferRepository;
import by.vsu.service.exception.AccountNotActiveServiceException;
import by.vsu.service.exception.AccountNotExistsServiceException;
import by.vsu.service.exception.InsufficientAccountFundsServiceException;
import by.vsu.service.exception.ServiceException;

public class TransferService {
	private AccountRepository accountRepository;
	private TransferRepository transferRepository;

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void setTransferRepository(TransferRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

	public void withdrawCash(String accountNumber, Long sum) throws ServiceException {
		try {
			Account account = getAccount(accountNumber);
			if(sum > account.getBalance()) throw new InsufficientAccountFundsServiceException(account, sum);
			account.setBalance(account.getBalance() - sum);
			accountRepository.update(account);
			Transfer transfer = new Transfer();
			transfer.setSender(account);
			transfer.setSum(sum);
			transferRepository.create(transfer);
		} catch(RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void depositCash(String accountNumber, Long sum) throws ServiceException {
		try {
			Account account = getAccount(accountNumber);
			account.setBalance(account.getBalance() + sum);
			accountRepository.update(account);
			Transfer transfer = new Transfer();
			transfer.setReceiver(account);
			transfer.setSum(sum);
			transferRepository.create(transfer);
		} catch(RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	public void transfer(String senderNumber, String receiverNumber, Long sum, String purpose) throws ServiceException {
		try {
			Account sender = getAccount(senderNumber);
			Account receiver = getAccount(receiverNumber);
			if(sum > sender.getBalance()) throw new InsufficientAccountFundsServiceException(sender, sum);
			sender.setBalance(sender.getBalance() - sum);
			accountRepository.update(sender);
			receiver.setBalance(receiver.getBalance() + sum);
			accountRepository.update(receiver);
			Transfer transfer = new Transfer();
			transfer.setSender(sender);
			transfer.setReceiver(receiver);
			transfer.setSum(sum);
			transfer.setPurpose(purpose);
			transferRepository.create(transfer);
		} catch(RepositoryException e) {
			throw new ServiceException(e);
		}
	}

	private Account getAccount(String number) throws RepositoryException, ServiceException {
		Account account = accountRepository.readByNumber(number).orElseThrow(() -> new AccountNotExistsServiceException(number));
		if(!account.isActive()) throw new AccountNotActiveServiceException(account);
		return account;
	}
}
