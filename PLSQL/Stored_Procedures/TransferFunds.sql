CREATE OR REPLACE PROCEDURE TransferFunds(
  from_account IN NUMBER,
  to_account IN NUMBER,
  amount IN NUMBER
) IS
  from_balance NUMBER;
BEGIN
  SELECT balance INTO from_balance FROM accounts WHERE account_id = from_account FOR UPDATE;
  IF from_balance < amount THEN
    RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance.');
  END IF;

  UPDATE accounts SET balance = balance - amount WHERE account_id = from_account;
  UPDATE accounts SET balance = balance + amount WHERE account_id = to_account;
  COMMIT;
END;