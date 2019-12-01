package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.Receipt;
import com.example.furniturewebdemo1.repository.ReceiptRepository;
import com.example.furniturewebdemo1.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository){
        this.receiptRepository=receiptRepository;
    }
    @Override
    public List<Receipt> findAllReceipt() {
        return receiptRepository.findAll();
    }

    @Override
    public Optional<Receipt> findReceiptById(long id) {
        return receiptRepository.findById(id);
    }

    @Override
    public void save(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    @Override
    public void delete(Receipt receipt) {
        receiptRepository.delete(receipt);
    }
}
