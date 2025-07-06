BEGIN
  FOR ln IN (SELECT loan_id, customer_id, due_date FROM loans WHERE due_date <= SYSDATE + 30) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || ln.loan_id || ' for customer ' || ln.customer_id || ' is due on ' || ln.due_date);
  END LOOP;
END;