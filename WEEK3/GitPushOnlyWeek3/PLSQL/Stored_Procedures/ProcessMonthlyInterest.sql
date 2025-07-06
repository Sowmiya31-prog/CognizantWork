CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
  FOR acct IN (SELECT account_id, balance FROM savings_accounts) LOOP
    UPDATE savings_accounts
    SET balance = balance + (balance * 0.01)
    WHERE account_id = acct.account_id;
  END LOOP;
  COMMIT;
END;