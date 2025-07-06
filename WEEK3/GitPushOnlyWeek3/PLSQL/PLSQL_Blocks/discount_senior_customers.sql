BEGIN
  FOR cust IN (SELECT customer_id, age FROM customers) LOOP
    IF cust.age > 60 THEN
      UPDATE loans
      SET interest_rate = interest_rate - 1
      WHERE customer_id = cust.customer_id;
    END IF;
  END LOOP;
  COMMIT;
END;