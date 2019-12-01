package com.example.furniturewebdemo1.service;



import com.example.furniturewebdemo1.model.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {
    List<Receipt> findAllReceipt();
    Optional<Receipt> findReceiptById(long id);
    void save (Receipt receipt);
    void delete (Receipt receipt);
}
